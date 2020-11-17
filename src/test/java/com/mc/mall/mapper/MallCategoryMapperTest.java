package com.mc.mall.mapper;

import com.mc.mall.pojo.MallCategory;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MallCategoryMapperTest extends TestCase {

    @Autowired
    private MallCategoryMapper mallCategoryMapper;


    @Test
    public void deleteByPrimaryKey() {
        MallCategory mallCategory=mallCategoryMapper.deleteByPrimaryKey(100001);
        System.out.println(mallCategory.toString());
    }

    @Test
    public void selectByPrimaryKey() {
        MallCategory mallCategory=mallCategoryMapper.selectByPrimaryKey(100001);
        System.out.println(mallCategory);
    }
}