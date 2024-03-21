package com.example.exe;

import com.example.Main;
import com.example.query.UserProfileGetQryExe;
import com.example.vo.UserProfileGetQuery;
import com.example.vo.UserProfileVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
@SpringBootTest(classes = Main.class)
public class UserProfileGetQryExeTest {

    @Resource
    private UserProfileGetQryExe userProfileGetQryExe;

    @Test
    public void test() {
        UserProfileGetQuery qry = new UserProfileGetQuery();
        qry.setId(1L);

        UserProfileVO userProfileVO = userProfileGetQryExe.getUserProfileBy(qry);
        assert userProfileVO != null;
    }
}
