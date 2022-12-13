package com.jec.ramenlog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jec.ramenlog.entity.Application;
import com.jec.ramenlog.mapper.ApplicationMapper;
import com.jec.ramenlog.service.ApplicationService;
import org.springframework.stereotype.Service;

/**
 * Created by @author 卞凌志
 * on 2022/12/13 13:38
 */
@Service
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, Application> implements ApplicationService {
}
