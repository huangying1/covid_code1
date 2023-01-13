package com.tyut.covid.bean;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Supplies implements Serializable {
    private String username;
    private String name;
    private int price;
    private int count;
    private Date addtime;
    private int money;
    private String provinceCode;
    private String provinceName;
    private String cityCode;
    private String cityName;
    private String areaCode;
    private String areaName;
    private String zip;
    private String address;
    private String phone;
    private String tel;
    private String tag;

}
