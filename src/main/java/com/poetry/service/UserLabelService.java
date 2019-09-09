package com.poetry.service;

import com.poetry.dto.LabelDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserLabelService {
    List<LabelDTO> listLabelsByUserId(String userId);
    List<LabelDTO> listLabelsByUserIdAndContentId(String userId, String contentId);
    Boolean getIsPoemLabelAdded(String userId, String contentId, String labelId);
    Boolean savePoemLabel(String userId, String contentId, String labelId);
    Boolean saveLabel(String userId, String labelName);
    Boolean poemLabelUpdate(String userId, String contentId, String labelId, Boolean isRemoved);
    Boolean labelUpdate(String userId, String labelId, String labelName);
    Boolean labelDelete(String userId, String labelId);
    String getLabelId(String userId, String labelName);
}
