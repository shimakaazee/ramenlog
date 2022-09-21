package com.jec.ramenlog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jec.ramenlog.entity.Category;
import com.jec.ramenlog.mapper.CategoryMapper;
import com.jec.ramenlog.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
