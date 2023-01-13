package com.tyut.covid.bean;

import lombok.*;
import java.io.Serializable;

public class CityCovidBean extends BaseBean implements Serializable {
    private static final long serialVersionUID = 2774017934785603121L;
    private Integer confirmedCount;
    private Integer confirmedIncr;
    private Integer curedCount;
    private Integer curedIncr;
    private Integer deadCount;
    private Integer deadIncr;
    private String dateId;
}
