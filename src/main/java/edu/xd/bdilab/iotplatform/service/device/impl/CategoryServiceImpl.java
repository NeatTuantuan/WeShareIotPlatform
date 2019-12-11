package edu.xd.bdilab.iotplatform.service.device.impl;

import edu.xd.bdilab.iotplatform.dao.Category;
import edu.xd.bdilab.iotplatform.mapper.CategoryMapper;
import edu.xd.bdilab.iotplatform.service.device.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Category selectCategoryById(int categoryId) {
        return categoryMapper.selectById(categoryId);
    }

    @Override
    public int addCategory(Category category) {
        return categoryMapper.insertSelective(category);
    }

    @Override
    public int updateCategory(Category category) {
        return categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public int delete(int categoryId) {
        return categoryMapper.deleteById(categoryId);
    }



    @Override
    public Category selectBySubTitle(String subTitle) {
        return categoryMapper.selectBySubTitle(subTitle);
    }

    @Override
    public List<Category> selectAll() {
        return categoryMapper.selectAll();
    }

    @Override
    public List<Integer> getAllMainTitle() {
        return categoryMapper.selectMainTitle();
    }

    @Override
    public List<Category> selectByMainTitle(int mainTitle) {
        return categoryMapper.selectByMainTitle(mainTitle);
    }
}
