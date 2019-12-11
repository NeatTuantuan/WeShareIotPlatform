package edu.xd.bdilab.iotplatform.mapper;

import edu.xd.bdilab.iotplatform.dao.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CategoryMapper {
    int insertSelective(Category category);

    int updateByPrimaryKeySelective(Category category);

    List<Category> selectByMainTitle(int mainTitle);

    Category selectBySubTitle(String subTitle);

    List<Integer> selectMainTitle();

    List<String> selectSubTitleByMainTitle(String mainTitle);

    Category selectById(int id);

    int deleteById(int id);

    List<Category> selectAll();




}
