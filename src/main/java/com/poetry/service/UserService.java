package com.poetry.service;

import com.poetry.dto.PoemDTO;
import com.poetry.dto.UserDTO;

import java.util.List;

public interface UserService {

    Boolean getIsExitUser(String username);

    Boolean saveUser(UserDTO userDto);
    UserDTO getUserByUsernameAndPassword(String username, String password);
    UserDTO getUserByUserId(String userId);


    //更新用户token
    Boolean updateUserTokenByUserId(UserDTO userDto);

}
