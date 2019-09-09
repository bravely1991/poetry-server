package com.poetry.dao;

import org.apache.ibatis.annotations.Param;

public interface UserPoemLabelDao {
    Boolean getIsPoemLabelAdded(@Param("userId") String userId, @Param("contentId") String contentId, @Param("labelId") String labelId);
    Boolean savePoemLabel(@Param("userId") String userId, @Param("contentId") String contentId, @Param("labelId") String labelId);
    Boolean poemLabelUpdate(@Param("userId") String userId, @Param("contentId") String contentId, @Param("labelId") String labelId, @Param("isRemoved") Boolean isRemoved);
}
