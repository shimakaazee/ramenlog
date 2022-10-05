package com.jec.ramenlog.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 菜品
 */
@Data
public class Shop implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private int categoryId;

    private String info_1;
    private String info_2;
    private double score;
    private String area;
    private String location_1;
    private String location_2;
    private Integer price;
    private String close_station;
    private String phone;
    private int status;
    private String image;



    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    @TableField(fill = FieldFill.INSERT)
    private int createUser;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private int updateUser;

}
