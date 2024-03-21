package com.example.repository.service.impl;

import com.example.repository.mapper.UserProfileMapper;
import com.example.repository.model.UserProfileDO;
import com.example.repository.service.IUserProfileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author ding
 */
@Service
public class UserProfileServiceImpl extends ServiceImpl<UserProfileMapper, UserProfileDO> implements IUserProfileService {

}
