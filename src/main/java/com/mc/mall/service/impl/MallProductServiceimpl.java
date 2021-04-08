package com.mc.mall.service.impl;/**
 * @author mc
 * @create 2020-12-03 22:05
 **/

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mc.mall.Enum.ProductStatusEnum;
import com.mc.mall.Enum.ResponseEnum;
import com.mc.mall.mapper.MallProductMapper;
import com.mc.mall.pojo.MallProduct;
import com.mc.mall.service.MallCategoryService;
import com.mc.mall.service.MallProductService;
import com.mc.mall.vo.ProductDetailVo;
import com.mc.mall.vo.ProductVo;
import com.mc.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.omg.PortableServer.POAHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.mc.mall.Enum.ProductStatusEnum.DELETE;
import static com.mc.mall.Enum.ProductStatusEnum.OFF_SALE;

/**
 * Author wangyi
 * Create 2020-12-03 22:05
 * ClassName MallProductServiceimpl
 * Version 1.0
 */
@Service
@Slf4j
public class MallProductServiceimpl implements MallProductService {

    @Autowired
    private MallCategoryService mallCategoryService;
    @Autowired
    private MallProductMapper mallProductMapper;

    @Override
    public ResponseVo<PageInfo> list(Integer categoryId, Integer pageNum, Integer pageSize) {
        Set<Integer> categoryIdSet = new HashSet<>();
        if (categoryId != null) {
            mallCategoryService.findSubCategoryId(categoryId, categoryIdSet);
            categoryIdSet.add(categoryId);
        }
        //常规方法
//        List<MallProduct> products = mallProductMapper.selectByCategoryIdSet(categoryIdSet);
//        log.info("products={}", products);

        //lambda方法
        PageHelper.startPage(pageNum, pageSize);
        List<MallProduct> productList = mallProductMapper.selectByCategoryIdSet(categoryIdSet);
        List<ProductVo> productVoList = productList.stream()
                .map(e -> {
                    ProductVo productVo = new ProductVo();
                    BeanUtils.copyProperties(e, productVo);
                    return productVo;
                })
                .collect(Collectors.toList());
        PageInfo pageInfo = new PageInfo<>(productList);
        pageInfo.setList(productVoList);
        return ResponseVo.success(pageInfo);
    }

    @Override
    public ResponseVo<ProductDetailVo> detail(Integer productId) {
        MallProduct mallProduct = mallProductMapper.selectByPrimaryKey(productId);
        //判断商品是否已下架或删除
        if (mallProduct.getStatus().equals(OFF_SALE.getCode()) || mallProduct.getStatus().equals(DELETE.getCode())) {
            return ResponseVo.error(ResponseEnum.PRODUCT_OFF_SALE_OR_dELETE);
        }
        ProductDetailVo productDetailVo = new ProductDetailVo();
        BeanUtils.copyProperties(mallProduct, productDetailVo);
        //敏感数据处理
        productDetailVo.setStock(mallProduct.getStock() > 100 ? 100 : mallProduct.getStock());
        return ResponseVo.success(productDetailVo);
    }
}
