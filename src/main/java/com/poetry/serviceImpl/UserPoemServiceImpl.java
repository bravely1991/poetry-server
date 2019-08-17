package com.poetry.serviceImpl;

import com.poetry.dao.UserPoemDao;
import com.poetry.dto.PoemDTO;
import com.poetry.entity.PoemDO;
import com.poetry.service.UserPoemService;
import com.poetry.utils.DozerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserPoemServiceImpl implements UserPoemService {

    @Autowired
    private UserPoemDao userPoemDao;

    @Override
    public Boolean userPoemCollect(String userId, String contentId, Boolean isCollect) {
        int result = userPoemDao.poemCollect(userId, contentId, isCollect);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    //用户收藏诗列表
    @Override
    @Transactional
    public List<PoemDTO> listPoemsUserCollect(String userId) {
        List<PoemDO> poemDOList = userPoemDao.listPoemsUserCollectByUserId(userId);
        return DozerUtil.mapList(poemDOList, PoemDTO.class);
    }

    @Override
    @Transactional
    public List<PoemDTO> listPoemsUserMemorize(String userId) {
        List<PoemDO> poemDOList = userPoemDao.listPoemsUserMemorizedByUserId(userId);
        return DozerUtil.mapList(poemDOList, PoemDTO.class);
    }

    @Override
    @Transactional
    public List<PoemDTO> listPoemsUserPreparing(String userId) {
        List<PoemDO> poemDOList = userPoemDao.listPoemsUserPreparingByUserId(userId);
        return DozerUtil.mapList(poemDOList, PoemDTO.class);
    }
}
