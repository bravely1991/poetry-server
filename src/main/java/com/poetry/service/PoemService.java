package com.poetry.service;

import com.poetry.dto.PoemDTO;
import com.poetry.dto.UserDTO;

import java.util.List;

public interface PoemService {

    //诗列表
    List<PoemDTO> listPoems();

    //诗列表，指定writer则返回writer下的诗列表
    List<PoemDTO> listPoemsByWriter(String writer);

    //模糊查询，诗列表
    List<PoemDTO> listPoemsByKeyword(String keyword);

    //诗详情
    PoemDTO getPoemDetail(String contentId);

}
