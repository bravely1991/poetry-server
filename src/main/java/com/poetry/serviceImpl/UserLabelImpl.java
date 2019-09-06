package com.poetry.serviceImpl;

import com.poetry.dao.UserLabelDao;
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

    @Override
    public List<LabelDTO> listLabelsByUserId(String userId) {
        List<LabelDO> labelDOList = userLabelDao.listLabelsByUserId(userId);
        return DozerUtil.mapList(labelDOList, LabelDTO.class);
    }

}
