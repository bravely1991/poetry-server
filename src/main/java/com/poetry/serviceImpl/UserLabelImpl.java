package com.poetry.serviceImpl;

import com.poetry.dao.UserLabelDao;
import com.poetry.dao.UserPoemLabelDao;
import com.poetry.dto.LabelDTO;
import com.poetry.dto.PoemDTO;
import com.poetry.entity.LabelDO;
import com.poetry.service.UserLabelService;
import com.poetry.utils.DozerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLabelImpl implements UserLabelService {
    @Autowired
    private UserLabelDao userLabelDao;
    @Autowired
    private UserPoemLabelDao userPoemLabelDao;

    @Override
    public List<LabelDTO> listLabelsByUserId(String userId) {
        List<LabelDO> labelDOList = userLabelDao.listLabelsByUserId(userId);
        return DozerUtil.mapList(labelDOList, LabelDTO.class);
    }

    @Override
    public List<LabelDTO> listLabelsByUserIdAndContentId(String userId, String contentId) {
        List<LabelDO> labelDOList = userLabelDao.listLabelsByUserIdAndContentId(userId, contentId);
        return DozerUtil.mapList(labelDOList, LabelDTO.class);
    }

    @Override
    public Boolean getIsPoemLabelAdded(String userId, String contentId, String labelId) {
        return userPoemLabelDao.getIsPoemLabelAdded(userId, contentId, labelId);
    }

    @Override
    public Boolean savePoemLabel(String userId, String contentId, String labelId) {
        return userPoemLabelDao.savePoemLabel(userId, contentId, labelId);
    }

    @Override
    public Boolean saveLabel(String userId, String labelName) {
        return userLabelDao.saveLabel(userId, labelName);
    }

    @Override
    public Boolean poemLabelUpdate(String userId, String contentId, String labelId, Boolean isRemoved) {
        return userPoemLabelDao.poemLabelUpdate(userId, contentId, labelId, isRemoved);
    }

    @Override
    public Boolean labelUpdate(String userId, String labelId, String labelName) {
        return userLabelDao.labelUpdate(userId, labelId, labelName);
    }

    @Override
    public Boolean labelDelete(String userId, String labelId) {
        return userLabelDao.labelDelete(userId, labelId);
    }

    @Override
    public String getLabelId(String userId, String labelName) {
        return userLabelDao.getLabelId(userId, labelName);
    }



}
