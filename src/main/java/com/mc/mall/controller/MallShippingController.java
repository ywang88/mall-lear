package com.mc.mall.controller;


import com.mc.mall.consts.MallConst;
import com.mc.mall.from.ShippingForm;
import com.mc.mall.pojo.MallUser;
import com.mc.mall.service.IMallShippingService;
import com.mc.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

/**
 * <p>
 * 地址控制器
 * </p>
 *
 * @author wangyi
 * @since 2021-03-10
 */
@RestController
public class MallShippingController {
    @Autowired
    private IMallShippingService mallShippingService;

    @PostMapping("/shippings")
    public ResponseVo add(HttpSession session,
                          @Valid @RequestBody ShippingForm form) {
        MallUser user = (MallUser) session.getAttribute(MallConst.CURRE_USER);
        return mallShippingService.add(user.getId(), form);
    }

    @DeleteMapping("/shippings/{shippingId}")
    public ResponseVo delete(@PathVariable Integer shippingId,
                             HttpSession session) {
        MallUser user = (MallUser) session.getAttribute(MallConst.CURRE_USER);
        return mallShippingService.delete(user.getId(), shippingId);
    }


    @PutMapping("/shippings/{shippingId}")
    public ResponseVo update(@PathVariable Integer shippingId,
                             @Valid @PathVariable ShippingForm form,
                             HttpSession session) {
        MallUser user = (MallUser) session.getAttribute(MallConst.CURRE_USER);
        return mallShippingService.update(user.getId(), shippingId, form);
    }

    @GetMapping("shippings")
    public ResponseVo list(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                           HttpSession session) {
        MallUser user = (MallUser) session.getAttribute(MallConst.CURRE_USER);
        return mallShippingService.list(user.getId(), pageNum, pageSize);
    }
}
