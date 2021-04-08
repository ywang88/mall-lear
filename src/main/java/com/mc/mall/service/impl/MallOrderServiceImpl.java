package com.mc.mall.service.impl;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mc.mall.Enum.OrderStatusEnum;
import com.mc.mall.Enum.PaymentTypeEnum;
import com.mc.mall.Enum.ProductStatusEnum;
import com.mc.mall.Enum.ResponseEnum;
import com.mc.mall.mapper.MallOrderItemMapper;
import com.mc.mall.mapper.MallOrderMapper;
import com.mc.mall.mapper.MallProductMapper;
import com.mc.mall.mapper.MallShippingMapper;
import com.mc.mall.pojo.*;
import com.mc.mall.service.ICartService;
import com.mc.mall.service.IMallOrderService;
import com.mc.mall.vo.OrderItemVo;
import com.mc.mall.vo.OrderVo;
import com.mc.mall.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wangyi
 * @since 2021-03-11
 */
@Service
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class MallOrderServiceImpl implements IMallOrderService {


    private final MallShippingMapper mallShippingMapper;
    private final ICartService iCartService;
    private final MallProductMapper mallProductMapper;
    private final MallOrderMapper mallOrderMapper;
    private final MallOrderItemMapper mallOrderItemMapper;


    @Override
    @Transactional
    public ResponseVo<OrderVo> create(Integer uid, Integer shippingId) {
        //1.收货地址校验
        MallShipping shipping = mallShippingMapper.selectByUidAndShippingId(uid, shippingId);
        if (shipping == null) {
            return ResponseVo.error(ResponseEnum.SHIPPING_NOT_EXIST);
        }
        //2.通过用户ID查出购物车，校验(是否有商品、库存)
        List<Cart> cartList = iCartService.listForCart(uid).stream()
                //筛选出用户选中的商品
                .filter(Cart::getProductSelected)
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(cartList)) {
            return ResponseVo.error(ResponseEnum.CART_SELECT_IS_EXIST);
        }
        //获取cartList里的productIds
        Set<Integer> productIdSet = cartList.stream().
                map(Cart::getProductId)
                .collect(Collectors.toSet());
        List<MallProduct> productList = mallProductMapper.selectByProductIdSet(productIdSet);
        Map<Integer, MallProduct> map = productList.stream().
                collect(Collectors.toMap(MallProduct::getId, mallProduct -> mallProduct));

        List<MallOrderItem> mallOrderItemList = new ArrayList<>();
        //生成订单号方法
        Long orderNo = generateOrderNo();
        for (Cart cart : cartList) {
            //根据productID查数据库
            MallProduct mallProduct = map.get(cart.getProductId());
            //判断是否有商品
            if (mallProduct == null) {
                return ResponseVo.error(ResponseEnum.PRODUCT_NOT_EXIST,
                        "商品不存在，productId=" + cart.getProductId());
            }
            //判断商品上下架状态
            if (ProductStatusEnum.ON_SALE.equals(mallProduct.getStock())) {
                return ResponseVo.error(ResponseEnum.PRODUCT_OFF_SALE_OR_dELETE,
                        "商品不是在售状态" + mallProduct.getName());
            }
            //判断库存是否充足
            if (mallProduct.getStock() < cart.getQuantity()) {
                return ResponseVo.error(ResponseEnum.PRODUCT_STOCK_ERROR,
                        "库存不正确" + mallProduct.getName());
            }

            MallOrderItem mallOrderItem = buildOrderItem(uid, orderNo, cart.getQuantity(), mallProduct);
            mallOrderItemList.add(mallOrderItem);

            //5.减库存（商品库存-购物车商品数量）
            mallProduct.setStock(mallProduct.getStock() - cart.getQuantity());
            int row = mallProductMapper.updateByPrimaryKeySelective(mallProduct);
            if (row <= 0) {
                return ResponseVo.error(ResponseEnum.ERROR);
            }
        }

        //3.计算总价（选中的商品）
        //4.生成订单，入库：order与order_item,需要添加事务
        MallOrder mallOrder = buildOrder(uid, orderNo, shippingId, mallOrderItemList);
        int rowOrder = mallOrderMapper.insert(mallOrder);
        if (rowOrder <= 0) {
            return ResponseVo.error(ResponseEnum.ERROR);
        }

        int rowOrderItem = mallOrderItemMapper.batchInsert(mallOrderItemList);
        if (rowOrderItem <= 0) {
            return ResponseVo.error(ResponseEnum.ERROR);
        }

        //6.更新购物车(删除已经购买的商品)
        //redis事务(打包命令，)不能回滚
        for (Cart cart : cartList) {
            iCartService.delete(uid, cart.getProductId());
        }

        //7.构造OrderVo
        OrderVo orderVo = buildOrderVo(mallOrder, mallOrderItemList, shipping);
        return ResponseVo.success(orderVo);
    }

    @Override
    public ResponseVo<PageInfo> list(Integer uid, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<MallOrder> mallOrders = mallOrderMapper.selectByUid(uid);

        //查出订单编号
        Set<Long> orderNoSet = mallOrders.stream()
                .map(MallOrder::getOrderNo)
                .collect(Collectors.toSet());
        List<MallOrderItem> mallOrderItemList = mallOrderItemMapper.selectOrderNoSet(orderNoSet);
        //list转map
        Map<Long, List<MallOrderItem>> orderItemMap = mallOrderItemList.stream()
                .collect(Collectors.groupingBy(MallOrderItem::getOrderNo));

        //查出收货地址
        Set<Integer> shippingIdSet = mallOrders.stream()
                .map(MallOrder::getShippingId)
                .collect(Collectors.toSet());
        List<MallShipping> mallShippings = mallShippingMapper.selectByIdSet(shippingIdSet);
        Map<Integer, MallShipping> shippingMap = mallShippings.stream()
                .collect(Collectors.toMap(MallShipping::getId, mallShipping -> mallShipping));

        List<OrderVo> orderVoList = new ArrayList<>();
        for (MallOrder mallOrder : mallOrders) {
            OrderVo orderVo = buildOrderVo(mallOrder,
                    orderItemMap.get(mallOrder.getOrderNo()),
                    shippingMap.get(mallOrder.getShippingId()));
            orderVoList.add(orderVo);
        }
        PageInfo pageInfo = new PageInfo(orderVoList);
        pageInfo.setList(orderVoList);
        return ResponseVo.success(pageInfo);
    }

    @Override
    public ResponseVo<OrderVo> detail(Integer uid, Long orderNo) {
        MallOrder mallOrder = mallOrderMapper.selectByOrderNo(orderNo);
        //判断订单是否存在并订单是否属于他
        if (mallOrder == null || !mallOrder.getUserId().equals(uid)) {
            return ResponseVo.error(ResponseEnum.ORDER_NOT_EXIST);
        }
        Set<Long> orderNoSet = new HashSet<>();
        orderNoSet.add(mallOrder.getOrderNo());
        List<MallOrderItem> mallOrderItemList = mallOrderItemMapper.selectOrderNoSet(orderNoSet);
        MallShipping mallShippings = mallShippingMapper.selectByPrimaryKeys(mallOrder.getShippingId());
        OrderVo orderVo = buildOrderVo(mallOrder, mallOrderItemList, mallShippings);

        return ResponseVo.success(orderVo);
    }

    @Override
    public ResponseVo<OrderVo> cancel(Integer uid, Long orderNo) {
        MallOrder mallOrder = mallOrderMapper.selectByOrderNo(orderNo);
        if (mallOrder == null || !mallOrder.getUserId().equals(uid)) {
            return ResponseVo.error(ResponseEnum.ORDER_NOT_EXIST);
        }
        //只取消未付款订单
        if (!mallOrder.getStatus().equals(OrderStatusEnum.NO_PAY.getCode())) {
            return ResponseVo.error(ResponseEnum.ORDER_STATUS_ERROR);
        }
        //更新订单状态与关闭时间
        mallOrder.setStatus(OrderStatusEnum.CANCELED.getCode());
        mallOrder.setCloseTime(new Date());
        int row = mallOrderMapper.updateByPrimaryKeySelective(mallOrder);
        if (row <= 0) {
            return ResponseVo.error(ResponseEnum.ERROR);
        }

        return ResponseVo.success();
    }

    private OrderVo buildOrderVo(MallOrder mallOrder,
                                 List<MallOrderItem> mallOrderItemList,
                                 MallShipping shipping) {
        OrderVo orderVo = new OrderVo();
        BeanUtils.copyProperties(mallOrder, orderVo);

        List<OrderItemVo> orderItemVoList = mallOrderItemList.stream()
                .map(e -> {
                    OrderItemVo orderItemVo = new OrderItemVo();
                    BeanUtils.copyProperties(e, orderItemVo);
                    return orderItemVo;
                }).collect(Collectors.toList());
        orderVo.setOrderItemVoList(orderItemVoList);

        if (shipping != null) {
            orderVo.setShippingId(shipping.getId());
            orderVo.setShippingVo(shipping);
        }


        return orderVo;
    }


    private MallOrder buildOrder(Integer uid,
                                 Long orderNo,
                                 Integer shipping,
                                 List<MallOrderItem> mallOrderItemList) {
        MallOrder mallOrder = new MallOrder();
        mallOrder.setUserId(uid);
        mallOrder.setShippingId(shipping);
        mallOrder.setOrderNo(orderNo);
        //计算总价
        BigDecimal payment = mallOrderItemList.stream()
                .map(MallOrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        mallOrder.setPayment(payment);
        mallOrder.setPaymentType(PaymentTypeEnum.PAY_ONLINE.getCode());
        mallOrder.setPostage(0);
        mallOrder.setStatus(OrderStatusEnum.NO_PAY.getCode());
        return mallOrder;
    }

    //生成订单号方法（时间戳毫秒+三位随机数）
    private Long generateOrderNo() {
        return System.currentTimeMillis() + new Random().nextInt(999);
    }

    private MallOrderItem buildOrderItem(Integer uid, Long orderNo, Integer quantity, MallProduct mallProduct
    ) {
        MallOrderItem mallOrderItem = new MallOrderItem();
        mallOrderItem.setUserId(uid);
        mallOrderItem.setOrderNo(orderNo);
        mallOrderItem.setProductId(mallProduct.getId());
        mallOrderItem.setProductName(mallProduct.getName());
        mallOrderItem.setProductImage(mallProduct.getMainImage());
        mallOrderItem.setCurrentUnitPrice(mallProduct.getPrice());
        mallOrderItem.setQuantity(quantity);
        mallOrderItem.setTotalPrice(mallProduct.getPrice().multiply(BigDecimal.valueOf(quantity)));

        return mallOrderItem;
    }
}
