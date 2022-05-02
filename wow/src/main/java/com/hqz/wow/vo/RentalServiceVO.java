package com.hqz.wow.vo;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigInteger;
import java.util.Date;

public class RentalServiceVO {

    private int serviceId;

    private float eOdometer;

    private String serviceStatus;

}
