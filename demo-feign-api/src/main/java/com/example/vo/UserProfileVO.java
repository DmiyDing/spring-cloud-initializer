package com.example.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserProfileVO extends VO {

    /**
     * 用户ID
     */
    @NotEmpty
    private String userId;

    /**
     * 用户名称
     */
    private String userName;


}
