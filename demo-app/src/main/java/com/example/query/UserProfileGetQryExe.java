package com.example.query;

import com.example.repository.UserProfileRepo;
import com.example.vo.UserProfileGetQuery;
import com.example.vo.UserProfileVO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserProfileGetQryExe {

    @Resource
    private UserProfileRepo userProfileRepo;

    public UserProfileVO getUserProfileBy(UserProfileGetQuery qry) {
        return userProfileRepo.getByQry(qry).orElse(null);
    }

}
