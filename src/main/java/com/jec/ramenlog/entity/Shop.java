package com.jec.ramenlog.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 菜品
 */
@Data
public class Shop implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private int categoryId;
    private int typeId;

    private String info1;
    private String info2;
    private double score;
    private String area;
    private String location1;
    private String location2;
    private Integer price;
    private String closeStation;
    private String phone;
    private int status;
    private String image;
    private double longitude;
    private double latitude;


    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    @TableField(fill = FieldFill.INSERT)
    private int createUser;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private int updateUser;

}
