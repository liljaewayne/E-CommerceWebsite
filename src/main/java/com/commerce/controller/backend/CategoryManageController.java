package com.commerce.controller.backend;

import com.commerce.common.ServerResponse;
import com.commerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/manage/category")
public class CategoryManageController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("add_category.do")
    @ResponseBody
    public ServerResponse addCategory(String categoryName, @RequestParam(value = "parentId", defaultValue = "0") int parentId) {
        return categoryService.addCategory(categoryName, parentId);
    }

    @RequestMapping("set_category_name.do")
    @ResponseBody
    public ServerResponse setCategoryName(Integer categoryId, String categoryName) {
        return categoryService.updateCategoryName(categoryId, categoryName);
    }

    @RequestMapping("get_category.do")
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(@RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
        return categoryService.getChildrenParallelCategory(categoryId);
    }

    @RequestMapping("get_deep_category.do")
    @ResponseBody
    public ServerResponse getCategoryAndDeepChildrenCategory(@RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
        return categoryService.selectCategoryAndChildrenById(categoryId);
    }


}
