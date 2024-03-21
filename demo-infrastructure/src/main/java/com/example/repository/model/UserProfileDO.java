package com.example.repository.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_profile")
public class UserProfileDO extends BaseDO{

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 域账号
     */
    private String userId;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 逻辑删除
     */
    private Integer isDeleted;
}
