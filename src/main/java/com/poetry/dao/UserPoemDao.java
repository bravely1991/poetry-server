package com.poetry.dao;

import com.poetry.entity.PoemDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserPoemDao {
    List<PoemDO> listPoemsUserCollectByUserId(String userId);
    List<PoemDO> listPoemsUserMemorizedByUserId(String userId);
    List<PoemDO> listPoemsUserPreparingByUserId(String userId);


    int poemCollect(@Param("userId") String userId, @Param("contentId") String contentId,
                        @Param("isCollect") Boolean isCollect);

}
