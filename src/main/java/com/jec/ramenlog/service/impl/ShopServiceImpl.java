package com.jec.ramenlog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jec.ramenlog.entity.Shop;
import com.jec.ramenlog.mapper.ShopMapper;
import com.jec.ramenlog.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ShopService {


}
