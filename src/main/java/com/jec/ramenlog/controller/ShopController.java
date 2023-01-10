package com.jec.ramenlog.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jec.ramenlog.common.R;
import com.jec.ramenlog.dto.ShopDto;
import com.jec.ramenlog.entity.Category;
import com.jec.ramenlog.entity.Shop;
import com.jec.ramenlog.service.CategoryService;
import com.jec.ramenlog.service.ShopDescriptionService;
import com.jec.ramenlog.service.ShopService;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * shop manage
 */
@RestController
@RequestMapping("/shop")
@Slf4j
@CrossOrigin

public class ShopController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopDescriptionService shopDescriptionService;
    @Autowired
    private CategoryService categoryService;


    /**
     * add
     *
     * @param
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody ShopDto shopDto) {
        log.info(shopDto.toString());
        shopService.saveWithDescription(shopDto);

        return R.success("add shop success");
    }

    /**
     * shop paging
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {

        Page<Shop> pageInfo = new Page<>(page, pageSize);
        Page<ShopDto> shopDtoPage = new Page();

        LambdaQueryWrapper<Shop> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, Shop::getName, name);
        queryWrapper.orderByDesc(Shop::getUpdateTime);


        shopService.page(pageInfo, queryWrapper);

        BeanUtils.copyProperties(pageInfo, shopDtoPage, "records");

        List<Shop> records = pageInfo.getRecords();

        List<ShopDto> list = records.stream().map((item) -> {
            ShopDto shopDto = new ShopDto();

            BeanUtils.copyProperties(item, shopDto);

            int categoryId = item.getCategoryId();

            Category category = categoryService.getById(categoryId);

            if (category != null) {
                String categoryName = category.getName();
                shopDto.setCategoryName(categoryName);
            }
            return shopDto;
        }).collect(Collectors.toList());

        shopDtoPage.setRecords(list);

        return R.success(shopDtoPage);
    }

    /**
     * search
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Shop> getById(@PathVariable Long id) {

        Shop shop = shopService.getByIdWithDescription(id);
        return R.success(shop);
    }

    @PostMapping("/list")
    public R<List<Shop>> list(@RequestBody Shop shop) {


        LambdaQueryWrapper<Shop> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(shop.getCategoryId() != 0, Shop::getCategoryId, shop.getCategoryId());
        queryWrapper.eq(shop.getTypeId() != 0, Shop::getTypeId, shop.getTypeId());
        queryWrapper.eq(Shop::getStatus, 1);


        List<Shop> list = shopService.list(queryWrapper);

        return R.success(list);
    }


    /**
     * edit
     *
     * @param
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody ShopDto shopDto) {
        log.info(shopDto.toString());

        shopService.updateWithDescription(shopDto);
        return R.success("edit success");
    }

    @DeleteMapping("/{ids}")
    public R<String> delete(@PathVariable Long ids) {
        System.out.println(ids);
        shopService.removeById(ids);
        return R.success("delete success");
    }

    @GetMapping ("/status/")
    public R<String> changeStatus(@RequestParam List ids, @RequestParam int status) {

        System.out.println(ids);
        System.out.println(status);
        return null;
    }

}
