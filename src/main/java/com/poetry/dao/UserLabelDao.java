package com.poetry.dao;

import com.poetry.entity.LabelDO;

import java.util.List;

public interface UserLabelDao {
    List<LabelDO> listLabelsByUserId(String userId);
}
