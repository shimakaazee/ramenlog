package com.jec.ramenlog.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jec.ramenlog.common.R;
import com.jec.ramenlog.entity.Category;
import com.jec.ramenlog.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * category management
 */
@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * add category
     * @param category
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody Category category){
        log.info("category:{}",category);
        categoryService.save(category);
        return R.success("add category success");
    }

    /**
     * search by page
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize){

        Page<Category> pageInfo = new Page<>(page,pageSize);
        //condition
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //添加排序条件，根据sort进行排序
        queryWrapper.orderByAsc(Category::getSort);

        //search
        categoryService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    /**
     * delete by id
     * @param id
     * @return
     */
    @DeleteMapping
    public R<String> delete(Long id){
        log.info("delete category，id:{}",id);
        categoryService.removeById(id);


        return R.success("delete success");
    }

    /**
     * update by id
     * @param category
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody Category category){
        log.info("update msg：{}",category);

        categoryService.updateById(category);

        return R.success("update success");
    }
}
