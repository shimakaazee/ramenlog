package com.jec.ramenlog.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * data handler
 */
@Component
@Slf4j
public class MyMetaObjecthandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("createUser", 1);
        metaObject.setValue("updateUser", 1);
    }


    @Override
    public void updateFill(MetaObject metaObject) {

        long id = Thread.currentThread().getId();

        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser", 1);
    }
}
