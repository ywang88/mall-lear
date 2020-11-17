package com.mc.mall.mapper;

import com.mc.mall.pojo.MallCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MallCategoryMapper {
    @Select("select * from  mall_category where id=#{id}")
    MallCategory deleteByPrimaryKey(@Param("id") Integer id);

    int insert(MallCategory record);

    int insertSelective(MallCategory record);

    MallCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MallCategory record);

    int updateByPrimaryKey(MallCategory record);
}