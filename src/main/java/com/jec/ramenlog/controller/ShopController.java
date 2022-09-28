package com.jec.ramenlog.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jec.ramenlog.common.R;
import com.jec.ramenlog.entity.Category;
import com.jec.ramenlog.entity.Shop;
import com.jec.ramenlog.service.ShopDescriptionService;
import com.jec.ramenlog.service.ShopService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜品管理
 */
@RestController
@RequestMapping("/shop")
@Slf4j
public class ShopController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopDescriptionService shopDescriptionService;


    /**
     * 新增菜品
     * @param
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody Shop shop){
        log.info(shop.toString());

        shopService.save(shop);

        return R.success("add shop success");
    }

    /**
     * shop paging
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){

        //构造分页构造器对象
        Page<Shop> pageInfo = new Page<>(page,pageSize);

        //条件构造器
        LambdaQueryWrapper<Shop> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.like(name != null,Shop::getName,name);
        //添加排序条件
        queryWrapper.orderByDesc(Shop::getUpdateTime);

        //执行分页查询
        shopService.page(pageInfo,queryWrapper);



        return R.success(pageInfo);
    }

    /**
     * 根据id查询菜品信息和对应的口味信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Shop> getById(@PathVariable Long id){

        Shop shop= shopService.getById(id);
        return R.success(shop);
    }

    /**
     * 修改菜品
     * @param
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody Shop shop){
        log.info(shop.toString());

        shopService.updateById(shop);
        return R.success("edit success");
    }

}
