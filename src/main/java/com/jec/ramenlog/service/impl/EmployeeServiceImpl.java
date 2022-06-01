package com.jec.ramenlog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jec.ramenlog.entity.Employee;
import com.jec.ramenlog.mapper.EmployeeMapper;
import com.jec.ramenlog.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
