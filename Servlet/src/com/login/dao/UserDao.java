package com.login.dao;

import com.login.domain.User;
import com.login.util.JdbcUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/*
    操作数据库中的user表及 User类
 */
public class UserDao {
    private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());

    public User login(User loginUSer) {
        try {
            String sql = "select * from user where username = ? and password = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class),
                    loginUSer.getUsername(), loginUSer.getPassword());
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

}
