package com.mc.mall.controller;


import com.github.pagehelper.PageInfo;
import com.mc.mall.consts.MallConst;
import com.mc.mall.from.OrderCreateForm;
import com.mc.mall.pojo.MallUser;
import com.mc.mall.service.IMallOrderService;
import com.mc.mall.vo.OrderVo;
import com.mc.mall.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wangyi
 * @since 2021-03-11
 */
@RestController
//@RequestMapping("/mall-order")
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class MallOrderController {
    private final IMallOrderService iMallOrderService;

    @PostMapping("/orders")
    public ResponseVo<OrderVo> create(@Valid @RequestBody OrderCreateForm form
            , HttpSession session) {
        MallUser user = (MallUser) session.getAttribute(MallConst.CURRE_USER);
        return iMallOrderService.create(user.getId(), form.getShippingId());
    }

    @GetMapping("/orders")
    public ResponseVo<PageInfo> list(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                     @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                     HttpSession session) {
        MallUser user = (MallUser) session.getAttribute(MallConst.CURRE_USER);
        return iMallOrderService.list(user.getId(), pageNum, pageSize);
    }

    @GetMapping("/orders/{orderNo}")
    public ResponseVo<OrderVo> detail(@PathVariable Long orderNo,
                                      HttpSession session) {
        MallUser user = (MallUser) session.getAttribute(MallConst.CURRE_USER);
        return iMallOrderService.detail(user.getId(), orderNo);
    }

    @PutMapping("/orders/{orderNo}")
    public ResponseVo<OrderVo> cancel(@PathVariable Long orderNo, HttpSession session) {
        MallUser user = (MallUser) session.getAttribute(MallConst.CURRE_USER);
        return iMallOrderService.cancel(user.getId(), orderNo);
    }

}
