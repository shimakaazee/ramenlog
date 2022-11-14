package com.jec.ramenlog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jec.ramenlog.entity.User;
import com.jec.ramenlog.mapper.UserMapper;
import com.jec.ramenlog.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by @author 卞凌志
 * on 2022/10/25 10:40
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
