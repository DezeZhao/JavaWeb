package com.login.test;


import com.login.dao.UserDao;
import com.login.domain.User;
import org.junit.Test;

public class UserDaoTest {


    @Test
    public void testLogin() {
        User loginUser = new User();
        loginUser.setUsername("zq");
        loginUser.setPassword("1234567");


        UserDao dao = new UserDao();
        User user = dao.login(loginUser);

        System.out.println(user);
    }
}

