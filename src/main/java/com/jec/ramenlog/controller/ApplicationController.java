package com.jec.ramenlog.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jec.ramenlog.common.R;
import com.jec.ramenlog.entity.Application;
import com.jec.ramenlog.entity.Category;
import com.jec.ramenlog.entity.Employee;
import com.jec.ramenlog.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by @author 卞凌志
 * on 2022/12/13 13:25
 */
@RestController
@RequestMapping("/application")
@Slf4j
@CrossOrigin
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping
    public R<String> save(@RequestBody Application application) {
        log.info("category:{}", application);
        applicationService.save(application);
        return R.success("add application success");
    }

    @PutMapping
    public R<String> update(@RequestBody Application application) {

        applicationService.updateById(application);

        return R.success("application edit success!");
    }

    @CrossOrigin
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {

        Page<Application> pageInfo = new Page<>(page, pageSize);
        //condition
        LambdaQueryWrapper<Application> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(name), Application::getName, name);

        applicationService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }


}
