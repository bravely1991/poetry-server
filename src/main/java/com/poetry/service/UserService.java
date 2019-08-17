package com.poetry.service;

import com.poetry.dto.PoemDTO;
import com.poetry.dto.UserDTO;

import java.util.List;

public interface UserService {

    Boolean getIsExitUser(String username);

    Boolean saveUser(UserDTO userDto);
    UserDTO getUserbyUsernameAndPassword(String username, String password);

    //更新用户token
    Boolean updateUserTokenByUserId(UserDTO userDto);

}
