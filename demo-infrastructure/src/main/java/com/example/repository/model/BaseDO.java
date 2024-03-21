package com.example.repository.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;


    private Date gmtCreate;

    private Date gmtModified;


}
