package com.jec.ramenlog.entity;

import lombok.Data;

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
    private String accessCode;


    private LocalDateTime createTime;


    private LocalDateTime UpdateTime;


    private int createUser;


    private int updateUser;
}
