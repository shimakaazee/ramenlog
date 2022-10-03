package com.jec.ramenlog.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.jec.ramenlog.dto.ShopDto;
import com.jec.ramenlog.entity.Shop;

public interface ShopService extends IService<Shop> {

    public void saveWithDescription(ShopDto shopDto);

    //根据id查询菜品信息和对应的口味信息
    public ShopDto getByIdWithDescription(Integer id);

    //更新菜品信息，同时更新对应的口味信息
    public void updateWithDescription(ShopDto shopDto);
}
