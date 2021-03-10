package com.mc.mall.service.impl;/**
 * @author mc
 * @create 2021-01-30 23:44
 **/

import com.google.gson.Gson;
import com.mc.mall.Enum.ProductStatusEnum;
import com.mc.mall.Enum.ResponseEnum;
import com.mc.mall.from.CartAddFrom;
import com.mc.mall.from.CartUpdateFarm;
import com.mc.mall.mapper.MallProductMapper;
import com.mc.mall.pojo.Cart;
import com.mc.mall.pojo.MallProduct;
import com.mc.mall.service.ICartService;
import com.mc.mall.vo.CartProductVo;
import com.mc.mall.vo.CartVo;
import com.mc.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author wangyi
 * Create 2021-01-30 23:44
 * ClassName ICartServiceImpl
 * Version 1.0
 */
@Service
public class ICartServiceImpl implements ICartService {
    public static final String CART_REDIS_KEY_TEMPLATE = "cart_%s";
    @Autowired
    private MallProductMapper mallProductMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private Gson gson = new Gson();

    @Override
    public ResponseVo<CartVo> add(Integer uid, CartAddFrom cartAddFrom) {
        Integer quantity = 1;
        MallProduct mallProduct = mallProductMapper.selectByPrimaryKey(cartAddFrom.getProductid());
        //判断商品是否存在
        if (mallProduct == null) {
            return ResponseVo.error(ResponseEnum.PRODUCT_NOT_EXIST);
        }
        //判断商品状态
        if (!mallProduct.getStatus().equals(ProductStatusEnum.ON_SALE.getCode())) {
            return ResponseVo.error(ResponseEnum.PRODUCT_OFF_SALE_OR_dELETE);
        }
        //判断商品库存
        if (mallProduct.getStock() <= 0) {
            return ResponseVo.error(ResponseEnum.PRODUCT_STOCK_ERROR);
        }

        String rediskey = String.format(CART_REDIS_KEY_TEMPLATE, uid);

        //写入redis
        //key : cart_1
        HashOperations<String, String, String> operations =
                redisTemplate.opsForHash();
        Cart cart;
        String value = operations.get(rediskey, String.valueOf(mallProduct.getId()));
        if (StringUtils.isEmpty(value)) {
            //没有商品则新增商品
            cart = new Cart(mallProduct.getId(), quantity, cartAddFrom.getSelectAll());
        } else {
            //已经有了则数量+1
            cart = gson.fromJson(value, Cart.class);
            cart.setQuantity(cart.getQuantity() + quantity);

        }
        operations.put(rediskey, String.valueOf(mallProduct.getId()),
                gson.toJson(cart));
        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> list(Integer uid) {
        HashOperations<String, String, String> operations =
                redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        Map<String, String> entries = operations.entries(redisKey);
        CartVo cartVo = new CartVo();
        List<CartProductVo> cartProductVoList = new ArrayList<>();
        boolean selltall = true;
        Integer cartTotalQuantity = 0;
        BigDecimal cartTotalPrice = BigDecimal.ZERO;
        for (Map.Entry<String, String> entry : entries.entrySet()) {
            Integer productId = Integer.valueOf(entry.getKey());
            Cart cart = gson.fromJson(entry.getValue(), Cart.class);
            //TODO 需要优化，使用mysql里的in
            MallProduct mallProduct = mallProductMapper.selectByPrimaryKey(productId);
            if (mallProduct != null) {
                CartProductVo cartProductVo = new CartProductVo(
                        productId,
                        cart.getQuantity(),
                        mallProduct.getName(),
                        mallProduct.getSubImages(),
                        mallProduct.getMainImage(),
                        mallProduct.getPrice(),
                        mallProduct.getStatus(),
                        mallProduct.getPrice().multiply(BigDecimal.valueOf(cart.getQuantity())),
                        mallProduct.getStock(),
                        cart.getProductSelected()
                );
                cartProductVoList.add(cartProductVo);
                //判断是都全选
                if (!cart.getProductSelected()) {
                    selltall = false;
                }
                //计算购物总价,只计算选中的商品
                if (cart.getProductSelected()) {
                    cartTotalPrice = cartTotalPrice.add(cartProductVo.getProductTotalPrice());
                }
                //计算购物车商品总数
//                if (cart.getProductSelected()) {
                cartTotalQuantity += cart.getQuantity();
//                }
            }

        }
        cartVo.setSelectAll(selltall);
        cartVo.setCartTotalQuantity(cartTotalQuantity);
        cartVo.setCartTotalPrice(cartTotalPrice);

        cartVo.setCartProductVoList(cartProductVoList);
        return ResponseVo.success(cartVo);
    }

    @Override
    public ResponseVo<CartVo> update(Integer uid, Integer productId, CartUpdateFarm farm) {
        HashOperations<String, String, String> operations =
                redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);

        String value = operations.get(redisKey, String.valueOf(productId));
        if (StringUtils.isEmpty(value)) {
            //没有商品,报错
            return ResponseVo.error(ResponseEnum.CART_PRODUCT_NOT_EXIST);
        }
        //已经有了则修改数量
        Cart cart = gson.fromJson(value, Cart.class);
        if (farm.getQuantity() != null && farm.getQuantity() >= 0) {
            cart.setQuantity(farm.getQuantity());
        }
        if (farm.getSelected() != null) {
            cart.setProductSelected(farm.getSelected());
        }
        operations.put(redisKey, String.valueOf(productId), gson.toJson(cart));

        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> delete(Integer uid, Integer productId) {
        HashOperations<String, String, String> operations =
                redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);

        String value = operations.get(redisKey, String.valueOf(productId));
        if (StringUtils.isEmpty(value)) {
            //没有商品,报错
            return ResponseVo.error(ResponseEnum.CART_PRODUCT_NOT_EXIST);
        }

        operations.delete(redisKey, String.valueOf(productId));

        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> selectAll(Integer uid) {
        HashOperations<String, String, String> operations = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        Map<String, String> entries = operations.entries(redisKey);
        for (Cart cart : listForCart(uid)) {
            cart.setProductSelected(true);
            operations.put(redisKey, String.valueOf(cart.getProductId()),
                    gson.toJson(cart));
        }
        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> unselectAll(Integer uid) {
        HashOperations<String, String, String> operations = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        Map<String, String> entries = operations.entries(redisKey);
        for (Cart cart : listForCart(uid)) {
            cart.setProductSelected(false);
            operations.put(redisKey, String.valueOf(cart.getProductId()),
                    gson.toJson(cart));
        }
        return list(uid);
    }

    @Override
    public ResponseVo<Integer> sum(Integer uid) {
        Integer sum = listForCart(uid).stream()
                .map(Cart::getQuantity)
                .reduce(0, Integer::sum);

        return ResponseVo.success(sum);
    }

    private List<Cart> listForCart(Integer uid) {
        HashOperations<String, String, String> operations = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        Map<String, String> entries = operations.entries(redisKey);
        List<Cart> cartList = new ArrayList<>();
        for (Map.Entry<String, String> entry : entries.entrySet()) {
            cartList.add(gson.fromJson(entry.getValue(), Cart.class));
        }
        return cartList;
    }
}
