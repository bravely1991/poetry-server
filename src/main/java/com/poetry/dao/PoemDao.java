package com.poetry.dao;

import com.poetry.entity.PoemDO;
import java.util.List;

public interface PoemDao {
    List<PoemDO> listPoems();
    List<PoemDO> listPoemsByWriter(String writer);
    List<PoemDO> listPoemsByKeyword(String keyword);
    PoemDO getPoemDetail(String contentId);
}
