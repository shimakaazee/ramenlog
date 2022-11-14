package com.jec.ramenlog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jec.ramenlog.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by @author 卞凌志
 * on 2022/10/25 10:42
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
