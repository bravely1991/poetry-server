package com.poetry.service;

import com.poetry.dto.PoemDTO;
import com.poetry.dto.UserDTO;

import java.util.List;

public interface UserService {

    Boolean saveUser(UserDTO userDto);
    UserDTO getUserbyUsernameAndPassword(UserDTO userDto);

    //更新用户token
    Boolean updateUserTokenByUserId(UserDTO userDto);

    //用户收藏诗列表
    List<PoemDTO> listPoemsUserCollect(UserDTO userDto);

    //用户已背诗列表
    List<PoemDTO> listPoemsUserMemorize(UserDTO userDto);

    //用户准备诗列表
    List<PoemDTO> listPoemsUserPreparing(UserDTO userDto);
}
