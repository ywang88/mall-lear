package com.mc.mall.controller;/**
 * @author mc
 * @create 2021-01-30 22:42
 **/

import com.mc.mall.consts.MallConst;
import com.mc.mall.from.CartAddFrom;
import com.mc.mall.from.CartUpdateFarm;
import com.mc.mall.pojo.MallUser;
import com.mc.mall.service.ICartService;
import com.mc.mall.vo.CartVo;
import com.mc.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Author wangyi
 * Create 2021-01-30 22:42
 * ClassName CartController
 * Version 1.0
 */
@RestController
public class CartController {
    @Autowired
    private ICartService iCartService;

    @PostMapping("/carts")
    public ResponseVo<CartVo> add(HttpSession httpSession,
                                  @Valid @RequestBody CartAddFrom cartAddFrom) {
        MallUser user = (MallUser) httpSession.getAttribute(MallConst.CURRE_USER);
        return iCartService.add(user.getId(), cartAddFrom);
    }

    @GetMapping("/carts")
    public ResponseVo<CartVo> list(HttpSession httpSession) {
        MallUser user = (MallUser) httpSession.getAttribute(MallConst.CURRE_USER);
        return iCartService.list(user.getId());
    }

    @PutMapping("/carts/{productId}")
    public ResponseVo<CartVo> update(HttpSession httpSession,
                                     @Valid @RequestBody CartUpdateFarm farm,
                                     @PathVariable Integer productId) {
        MallUser user = (MallUser) httpSession.getAttribute(MallConst.CURRE_USER);
        return iCartService.update(user.getId(), productId, farm);
    }

    @DeleteMapping("/carts/{productId}")
    public ResponseVo<CartVo> delete(HttpSession httpSession,
                                     @PathVariable Integer productId) {
        MallUser user = (MallUser) httpSession.getAttribute(MallConst.CURRE_USER);
        return iCartService.delete(user.getId(), productId);
    }

    @PutMapping("/carts/selectaAll")
    public ResponseVo<CartVo> selectAll(HttpSession httpSession) {
        MallUser user = (MallUser) httpSession.getAttribute(MallConst.CURRE_USER);
        return iCartService.selectAll(user.getId());
    }

    @PutMapping("/carts/unSelectaAll")
    public ResponseVo<CartVo> unselectAll(HttpSession httpSession) {
        MallUser user = (MallUser) httpSession.getAttribute(MallConst.CURRE_USER);
        return iCartService.unselectAll(user.getId());
    }

    @GetMapping("/carts/sum")
    public ResponseVo<Integer> sum(HttpSession httpSession) {
        MallUser user = (MallUser) httpSession.getAttribute(MallConst.CURRE_USER);
        return iCartService.sum(user.getId());
    }


}
