package com.tyut.covid.bean;

import lombok.*;

import java.io.Serializable;

public class CovidBean extends BaseBean implements Serializable {

    private static final long serialVersionUID = 8920130882849659118L;
    private String provinceName;
    private String provinceShortName;
    private String cityName;
    private Integer currentConfirmedCount;
    private Integer confirmedCount;
    private Integer suspectedCount;
    private Integer curedCount;
    private Integer deadCount;
    private Integer locationId;
    private Integer pid;
    private String statisticsData;
    private String cities;
    private String datetime;

}

