package com.mc.mall.mapper;

import com.mc.mall.pojo.MallCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MallCategoryMapper {
    @Select("select * from  mall_category where id=#{id}")
    MallCategory deleteByPrimaryKey(@Param("id") Integer id);

    MallCategory selectByPrimaryKey(Integer id);



    //查询所有分类
    List<MallCategory> selectAll();

}