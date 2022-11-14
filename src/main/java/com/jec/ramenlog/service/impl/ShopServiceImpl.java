package com.jec.ramenlog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jec.ramenlog.dto.ShopDto;
import com.jec.ramenlog.entity.Shop;
import com.jec.ramenlog.entity.ShopDescription;
import com.jec.ramenlog.mapper.ShopMapper;
import com.jec.ramenlog.service.ShopDescriptionService;
import com.jec.ramenlog.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ShopService {

    @Autowired
    private ShopDescriptionService shopDescriptionService;

    /**
     * 新增菜品，同时保存对应的口味数据
     *
     * @param
     */
    @Transactional
    public void saveWithDescription(ShopDto shopDto) {

        this.save(shopDto);

        Long shopId = shopDto.getId();
        System.out.println(shopDto);
        System.out.println(shopDto.getId());

        List<ShopDescription> descriptions = shopDto.getDescriptions();
        descriptions = descriptions.stream().map((item) -> {
            item.setShopId(shopId);
            return item;
        }).collect(Collectors.toList());

        shopDescriptionService.saveBatch(descriptions);

    }


    public ShopDto getByIdWithDescription(Long id) {

        Shop shop = this.getById(id);

        ShopDto shopDto = new ShopDto();
        BeanUtils.copyProperties(shop, shopDto);

        LambdaQueryWrapper<ShopDescription> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShopDescription::getShopId, shop.getId());
        List<ShopDescription> descriptions = shopDescriptionService.list(queryWrapper);
        shopDto.setDescriptions(descriptions);

        return shopDto;
    }

    @Override
    @Transactional
    public void updateWithDescription(ShopDto shopDto) {

        this.updateById(shopDto);

        LambdaQueryWrapper<ShopDescription> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(ShopDescription::getShopId, shopDto.getId());

        shopDescriptionService.remove(queryWrapper);

        List<ShopDescription> descriptions = shopDto.getDescriptions();

        descriptions = descriptions.stream().map((item) -> {
            item.setShopId(shopDto.getId());
            return item;
        }).collect(Collectors.toList());

        shopDescriptionService.saveBatch(descriptions);
    }


}
