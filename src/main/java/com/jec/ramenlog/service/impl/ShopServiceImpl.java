package com.jec.ramenlog.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jec.ramenlog.dto.ShopDto;
import com.jec.ramenlog.entity.Shop;
import com.jec.ramenlog.entity.ShopDescription;
import com.jec.ramenlog.mapper.ShopMapper;
import com.jec.ramenlog.service.ShopDescriptionService;
import com.jec.ramenlog.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ShopService {

    @Autowired
    private ShopDescriptionService shopDescriptionService;
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
}
