package com.poetry.dao;

import com.poetry.entity.PoemDO;

import java.util.List;

public interface UserPoemDao {
    List<PoemDO> listPoemsUserCollectByUserId(String userId);
    List<PoemDO> listPoemsUserMemorizedByUserId(String userId);
    List<PoemDO> listPoemsUserPreparingByUserId(String userId);
}
