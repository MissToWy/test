package com.example.springboot.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Book {
    @TableId(value = "bh")
    private int bh;
    private String bookname;
    private String zz;
    private Date time;
    private int guishu;
    private int money;
    private String fx;

}
