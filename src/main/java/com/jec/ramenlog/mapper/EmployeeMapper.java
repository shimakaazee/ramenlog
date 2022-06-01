package com.jec.ramenlog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jec.ramenlog.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}
