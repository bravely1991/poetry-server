package com.poetry.dao;

import com.poetry.entity.LabelDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserLabelDao {
    List<LabelDO> listLabelsByUserId(String userId);
    List<LabelDO> listLabelsByUserIdAndContentId(@Param("userId") String userId, @Param("contentId") String contentId);
    Boolean saveLabel(@Param("userId") String userId, @Param("labelName") String labelName);
    Boolean labelUpdate(@Param("userId") String userId,  @Param("labelId") String labelId, @Param("labelName") String labelName);
    Boolean labelDelete(@Param("userId") String userId,  @Param("labelId") String labelId);
    String getLabelId(@Param("userId") String userId, @Param("labelName") String labelName);
}
