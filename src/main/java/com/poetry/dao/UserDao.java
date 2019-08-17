package com.poetry.dao;

import com.poetry.entity.UserDO;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    UserDO getUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    boolean saveUser(@Param("userId") String userId, @Param("username") String username, @Param("password") String password,
                     @Param("nickname") String nickname, @Param("createTime") String createTime);

    boolean updateUserTokenByUserId(@Param("userId") String userId, @Param("token") String token);
}
