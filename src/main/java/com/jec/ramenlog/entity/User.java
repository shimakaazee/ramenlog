package com.jec.ramenlog.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by @author 卞凌志
 * on 2022/10/13 11:34
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String account;
    private String password;
    private int point;
    private String mail;
    private Long shop_id;




    private LocalDateTime createTime;


    private LocalDateTime UpdateTime;


    private int createUser;



    private int updateUser;
}
