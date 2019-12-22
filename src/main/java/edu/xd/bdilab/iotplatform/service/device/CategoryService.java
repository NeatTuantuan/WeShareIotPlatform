package edu.xd.bdilab.iotplatform.service.device;

import edu.xd.bdilab.iotplatform.dao.Category;

import java.util.List;

public interface CategoryService {
    Category selectCategoryById(int categoryId);

    int addCategory(Category category);

    int updateCategory(Category category);

    int delete(int categoryId);

    Category selectBySubTitle(String subTitle);

    List<Category> selectAll();

    List<Integer> getAllMainTitle();

    List<Category> selectByMainTitle(int mainTitle);
}
