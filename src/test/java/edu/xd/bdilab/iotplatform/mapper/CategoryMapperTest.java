package edu.xd.bdilab.iotplatform.mapper;

import edu.xd.bdilab.iotplatform.dao.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryMapperTest {
    @Autowired
    CategoryMapper categoryMapper;

    @Test
    public void insertSelective() {

        Category category = new Category();
        category.setMainTitle(1);
        category.setSubTitle("园区2");

        System.out.println(categoryMapper.insertSelective(category));

    }

    @Test
    public void update(){
        Category category = categoryMapper.selectBySubTitle("项目1");
        category.setSubTitle("项目2");

        System.out.println(categoryMapper.updateByPrimaryKeySelective(category));
    }

    @Test
    public void selectByMainTilte(){
        List<Category> categoryList = categoryMapper.selectByMainTitle(0);
        System.out.println(categoryList);
    }

    @Test
    public void selectBySubTilte(){
        System.out.println(categoryMapper.selectBySubTitle("项目1"));
    }

    @Test
    public void selectMainTitle(){
        System.out.println(categoryMapper.selectMainTitle());
    }

   @Test
    public void selectSub(){
        System.out.println(categoryMapper.selectByMainTitle(0));
    }

    @Test
    public void selectByid(){
        System.out.println(categoryMapper.selectById(11));
    }

    @Test
    public void delete(){
        System.out.println(categoryMapper.deleteById(13));
    }

    @Test
    public void selectAll(){
        System.out.println(categoryMapper.selectAll());
    }

}