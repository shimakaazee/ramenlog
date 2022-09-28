package com.jec.ramenlog.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jec.ramenlog.common.R;
import com.jec.ramenlog.dto.ShopDto;
import com.jec.ramenlog.entity.Category;
import com.jec.ramenlog.entity.Shop;
import com.jec.ramenlog.entity.ShopDescription;
import com.jec.ramenlog.mapper.ShopMapper;
import com.jec.ramenlog.service.CategoryService;
import com.jec.ramenlog.service.ShopDescriptionService;
import com.jec.ramenlog.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ShopService {

    @Autowired
    private ShopDescriptionService shopDescriptionService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ShopService shopService;
    @Transactional
    public void saveWithDescription(ShopDto shopDto) {

        this.save(shopDto);

        Integer shopId = shopDto.getId();

        //descriptions
        List<ShopDescription> descriptions = shopDto.getDescriptions() ;
        descriptions= descriptions.stream().map((item) -> {
            item.setShopId(shopId);
            return item;
        }).collect(Collectors.toList());

        // save descriptions
        shopDescriptionService.saveBatch(descriptions);
    }

    public ShopDto getByIdWithFlavor(Integer id) {
        //查询菜品基本信息，从Shop表查询
        Shop shop= this.getById(id);

        ShopDto shopDto = new ShopDto();
        BeanUtils.copyProperties(shop, shopDto);

        //查询当前菜品对应的口味信息，从Shop_flavor表查询
        LambdaQueryWrapper<ShopDescription> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShopDescription::getShopId,shop.getId());
        List<ShopDescription> shopDescriptions = shopDescriptionService.list(queryWrapper);
        shopDto.setDescriptions(shopDescriptions);

        return shopDto;
    }

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){

        //构造分页构造器对象
        Page<Shop> pageInfo = new Page<>(page,pageSize);
        Page<ShopDto> ShopDtoPage = new Page<>();

        //条件构造器
        LambdaQueryWrapper<Shop> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.like(name != null,Shop::getName,name);
        //添加排序条件
        queryWrapper.orderByDesc(Shop::getUpdateTime);

        //执行分页查询
        shopService.page(pageInfo,queryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(pageInfo,ShopDtoPage,"records");

        List<Shop> records = pageInfo.getRecords();

        List<ShopDto> list = records.stream().map((item) -> {
            ShopDto ShopDto = new ShopDto();

            BeanUtils.copyProperties(item,ShopDto);

            Integer categoryId = item.getCategoryId();//分类id
            //根据id查询分类对象
            Category category = categoryService.getById(categoryId);

            if(category != null){
                String categoryName = category.getName();
                ShopDto.setCategoryName(categoryName);
            }
            return ShopDto;
        }).collect(Collectors.toList());

        ShopDtoPage.setRecords(list);

        return R.success(ShopDtoPage);
    }
}
