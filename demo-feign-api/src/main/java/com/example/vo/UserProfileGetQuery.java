package com.example.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户信息
 *
 * @author ding
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "UserProfileGetQry对象", description = "用户信息")
public class UserProfileGetQuery extends VO {

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "主键ID")
    private Long id;


}