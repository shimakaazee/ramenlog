package com.jec.ramenlog.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.jec.ramenlog.dto.ShopDto;
import com.jec.ramenlog.entity.Shop;

public interface ShopService extends IService<Shop> {

    public void saveWithDescription(ShopDto shopDto);

    public ShopDto getByIdWithDescription(Long id);

    public void updateWithDescription(ShopDto shopDto);
}
