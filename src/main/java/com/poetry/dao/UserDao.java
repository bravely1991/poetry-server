package com.poetry.dao;

import com.poetry.entity.UserDO;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    UserDO getUserbyUsernameAndPassword(@Param("username") String username, @Param("password") String password);
    boolean saveUser(@Param("userId") String userId, @Param("username") String username, @Param("password") String password);
    boolean updateUserTokenByUserId(@Param("userId") String userId, @Param("token") String token);
}
