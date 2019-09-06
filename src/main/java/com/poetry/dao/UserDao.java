package com.poetry.dao;

import com.poetry.entity.UserDO;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

    boolean getIsExitUser(@Param("username") String username);

    UserDO getUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
    UserDO getUserByUserId(@Param("userId") String userId);

    boolean saveUser(@Param("userId") String userId, @Param("username") String username, @Param("password") String password,
                     @Param("nickname") String nickname, @Param("createTime") String createTime);

    boolean updateUserTokenByUserId(@Param("userId") String userId, @Param("token") String token);
}
