package com.mc.mall.mapper;

import com.mc.mall.pojo.MallOrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wangyi
 * @since 2021-03-15
 */
public interface MallOrderItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MallOrderItem record);

    int insertSelective(MallOrderItem record);

    MallOrderItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MallOrderItem record);

    int updateByPrimaryKey(MallOrderItem record);

    //批量写入数据
    int batchInsert(@Param("mallOrderItemList") List<MallOrderItem> mallOrderItemList);

    List<MallOrderItem> selectOrderNoSet(@Param("orderNoSet") Set orderNoSet);
}
