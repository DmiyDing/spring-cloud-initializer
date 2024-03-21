package com.example.controller;

import com.example.query.UserProfileGetQryExe;
import com.example.vo.UserProfileVO;
import com.example.vo.UserProfileGetQuery;
import com.example.common.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.example.utils.CommonExecutor;

import javax.annotation.Resource;

@Api(tags = "用户查询")
@RestController
@RequestMapping("/user")
public class UserProfileController {

    @Resource
    private UserProfileGetQryExe userProfileGetQryExe;

    @GetMapping({"/get"})
    @ApiOperation("获取用户信息")
    public ApiResponse<UserProfileVO> getUserProfileBy(@RequestBody UserProfileGetQuery query) {
        return CommonExecutor.execute(() -> userProfileGetQryExe.getUserProfileBy(query), "user-getUserProfileBy", query);
    }
}
