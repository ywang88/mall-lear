package com.mc.mall.service;

import com.mc.mall.from.CartAddFrom;
import com.mc.mall.from.CartUpdateFarm;
import com.mc.mall.vo.CartVo;
import com.mc.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 购物车接口
 *
 * @author mc
 * @create 2021-01-30 23:42
 **/
public interface ICartService {
    //添加购物车
    ResponseVo<CartVo> add(Integer uid, CartAddFrom cartAddFrom);

    //查询购物车
    ResponseVo<CartVo> list(Integer uid);

    //更新购物车
    ResponseVo<CartVo> update(Integer uid, Integer productId, CartUpdateFarm farm);

    //删除购物车商品
    ResponseVo<CartVo> delete(Integer uid, Integer productId);

    //购物车商品全部选中
    ResponseVo<CartVo> selectAll(Integer uid);

    //购物车商品全部不选中
    ResponseVo<CartVo> unselectAll(Integer uid);

    //购物车商品总和
    ResponseVo<Integer> sum(Integer uid);
}
