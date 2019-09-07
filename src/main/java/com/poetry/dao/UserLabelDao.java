package com.poetry.dao;

import com.poetry.entity.LabelDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserLabelDao {
    List<LabelDO> listLabelsByUserId(String userId);
    List<LabelDO> listLabelsByUserIdAndContentId(@Param("userId") String userId, @Param("contentId") String contentId);
}
