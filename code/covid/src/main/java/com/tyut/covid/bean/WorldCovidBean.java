package com.tyut.covid.bean;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class WorldCovidBean extends BaseBean implements Serializable {
    private static final long serialVersionUID = -6440320810340752136L;
    private String id;
    private String continents;
    private String provinceId;
    private String provinceName;
    private Integer currentConfirmedCount;
    private Integer confirmedCount;
    private Integer suspectedCount;
    private Integer curedCount;
    private Integer deadCount;
    private Integer yesterdayConfirmedCount;
    private String datetime;


}
