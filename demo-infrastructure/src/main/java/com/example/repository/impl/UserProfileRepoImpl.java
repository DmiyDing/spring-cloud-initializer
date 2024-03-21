package com.example.repository.impl;

import com.example.repository.mapper.UserProfileMapper;
import com.example.repository.model.UserProfileDO;
import com.example.repository.service.IUserProfileService;
import com.example.utils.Converters;
import com.example.vo.UserProfileVO;
import com.example.vo.UserProfileGetQuery;
import com.example.repository.UserProfileRepo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Optional;

@Repository
public class UserProfileRepoImpl implements UserProfileRepo {

    @Resource
    private IUserProfileService iUserProfileService;


    @Override
    public Optional<UserProfileVO> getByQry(UserProfileGetQuery qry) {
        UserProfileDO userProfileDO = iUserProfileService.getById(qry.getId());
        return Optional.of(Converters.convert(userProfileDO, UserProfileVO.class));
    }
}
