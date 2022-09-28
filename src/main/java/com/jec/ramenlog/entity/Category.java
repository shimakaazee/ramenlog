package com.jec.ramenlog.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;


    private Integer type;

    private String name;


    private Integer sort;


    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private int createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private int updateUser;

}