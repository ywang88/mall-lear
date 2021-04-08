package com.mc.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mc.mall.Enum.ResponseEnum;
import com.mc.mall.from.ShippingForm;
import com.mc.mall.pojo.MallShipping;
import com.mc.mall.mapper.MallShippingMapper;
import com.mc.mall.service.IMallShippingService;
import com.mc.mall.vo.ResponseVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wangyi
 * @since 2021-03-10
 */
@Service
public class MallShippingServiceImpl implements IMallShippingService {

    @Autowired
    private MallShippingMapper mallShippingMapper;


    @Override
    public ResponseVo<Map<String, Integer>> add(Integer uid, ShippingForm form) {
        MallShipping mallShipping = new MallShipping();
        BeanUtils.copyProperties(form, mallShipping);
        mallShipping.setUserId(uid);
        int row = mallShippingMapper.insertSelective(mallShipping);

        //判断是否成功写入数据库
        if (row == 0) {
            ResponseVo.error(ResponseEnum.ERROR);
        }
        //获取shipingID
        Map<String, Integer> map = new HashMap<>();
        map.put("shippingId", mallShipping.getId());

        return ResponseVo.success(map);
    }

    @Override
    public ResponseVo update(Integer uid, Integer shippingId, ShippingForm form) {
        MallShipping mallShipping = new MallShipping();
        BeanUtils.copyProperties(form, mallShipping);
        mallShipping.setUserId(uid);
        mallShipping.setId(shippingId);
        mallShipping.setUpdateTime(new Date());
        int row = mallShippingMapper.updateByPrimaryKeySelective(mallShipping);
        if (row == 0) {
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        return ResponseVo.success();
    }

    @Override
    public ResponseVo delete(Integer uid, Integer shippingId) {
        int row = mallShippingMapper.deleteByPrimaryKey(uid, shippingId);
        if (row == 0) {
            return ResponseVo.error(ResponseEnum.DELETE_SHIPPING_FAIL);
        }
        return ResponseVo.success();
    }

    @Override
    public ResponseVo<PageInfo> list(Integer uid, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<MallShipping> mallShippings = mallShippingMapper.selectByPrimaryKey(uid);
        PageInfo pageInfo = new PageInfo(mallShippings);
        return ResponseVo.success(pageInfo);
    }
}
