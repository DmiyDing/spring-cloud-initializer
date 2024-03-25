package com.example.api;

import com.example.common.ApiResponse;
import com.example.vo.UserProfileVO;
import com.example.vo.UserProfileGetQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * 用户信息
 *
 * @author dingmingyang
 */
@FeignClient(value = "demo", path = "/user")
public interface UserProfileFeignApi {

    @GetMapping({"/get"})
    ApiResponse<UserProfileVO> getUserProfileBy(UserProfileGetQuery qry);
}
