package com.jec.ramenlog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jec.ramenlog.entity.ShopDescription;
import com.jec.ramenlog.mapper.ShopDescriptionMapper;
import com.jec.ramenlog.service.ShopDescriptionService;
import org.springframework.stereotype.Service;

@Service
public class ShopDescriptionImpl extends
        ServiceImpl<ShopDescriptionMapper, ShopDescription> implements ShopDescriptionService {
}
