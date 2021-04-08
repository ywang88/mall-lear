package com.mc.mall.mapper;

import com.mc.mall.pojo.MallProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface MallProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MallProduct record);

    int insertSelective(MallProduct record);

    MallProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MallProduct record);

    int updateByPrimaryKey(MallProduct record);

    List<MallProduct> selectByCategoryIdSet(@Param("categoryIdSet") Set<Integer> categoryIdSet);

    List<MallProduct> selectByProductIdSet(@Param("productIdSet") Set<Integer> productIdSet);
}
