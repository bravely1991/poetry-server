package com.poetry.service;

import com.poetry.dto.PoemDTO;

import java.util.List;

public interface UserPoemService {
    // 用户收藏/取消收藏诗
    Boolean userPoemCollect(String userId, String collect, Boolean isCollect);

    //用户收藏诗列表
    List<PoemDTO> listPoemsUserCollect(String userId);

    //用户已背诗列表
    List<PoemDTO> listPoemsUserMemorize(String userId);

    //用户准备诗列表
    List<PoemDTO> listPoemsUserPreparing(String userId);
}
