package com.example.repository;

import com.example.vo.UserProfileVO;
import com.example.vo.UserProfileGetQuery;

import java.util.Optional;

/**
 * 用户信息数据仓储
 *
 * @author ding
 */
public interface UserProfileRepo {

    /**
     * 根据条件查询用户信息
     *
     * @param qry
     * @return
     */
    Optional<UserProfileVO> getByQry(UserProfileGetQuery qry);

}
