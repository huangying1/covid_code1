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
public class ResultVO implements Serializable {
    private static final long serialVersionUID = -7563260260996030003L;
    private String key;
    private Integer value;
}
