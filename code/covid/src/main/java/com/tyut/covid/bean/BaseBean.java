package com.tyut.covid.bean;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
public class BaseBean implements Serializable {
    private static final long serialVersionUID = 5643311589889679879L;
    private String createdUser;
    private Date createdTime;
    private String modifiedUser;
    private Date modifiedTime;
}
