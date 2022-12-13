package com.jec.ramenlog.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by @author 卞凌志
 * on 2022/12/13 11:20
 */
@Data
public class Application implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private int type;

    private String content;

    private Long userId;

    private String userName;

    private String ShopName;

    private Long ShopId;

    private String status;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private int createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private int updateUser;
}
