package com.jec.ramenlog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jec.ramenlog.common.CustomException;
import com.jec.ramenlog.entity.Category;
import com.jec.ramenlog.entity.Shop;
import com.jec.ramenlog.mapper.CategoryMapper;
import com.jec.ramenlog.service.CategoryService;
import com.jec.ramenlog.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ShopService shopService;
    @Override
    public void remove(int id) {

        LambdaQueryWrapper<Shop> shopLambdaQueryWrapper = new LambdaQueryWrapper<>();

        shopLambdaQueryWrapper.eq(Shop::getCategoryId, id);

        int count = shopService.count(shopLambdaQueryWrapper);

        if (count > 0 ) {
            throw new CustomException("can not be deleted of related shop");
        }

        super.removeById(id);

    }
}
