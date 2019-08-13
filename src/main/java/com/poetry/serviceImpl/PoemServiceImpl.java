package com.poetry.serviceImpl;

import com.poetry.dao.PoemDao;
import com.poetry.dto.PoemDTO;
import com.poetry.dto.UserDTO;
import com.poetry.entity.PoemDO;
import com.poetry.service.PoemService;
import com.poetry.utils.DozerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoemServiceImpl implements PoemService {

    @Autowired
    private PoemDao poemDao;

    @Override
    //全部诗列表
    public List<PoemDTO> listPoems() {
        List<PoemDO> poemDOList = poemDao.listPoems();
//        DozerUtil.map(user, UserDTO.class);
        return DozerUtil.mapList(poemDOList, PoemDTO.class);
    }

    //诗列表，指定writer则返回writer下的诗列表
    @Override
    public List<PoemDTO> listPoemsByWriter(String writer) {
        List<PoemDO> poemDOList = poemDao.listPoemsByWriter(writer);
        return DozerUtil.mapList(poemDOList, PoemDTO.class);
    }

    //诗详情
    @Override
    public PoemDTO getPoemDetail(String contentId) {
        PoemDO poemDO = poemDao.getPoemDetail(contentId);
        return DozerUtil.map(poemDO, PoemDTO.class);
    }

    //模糊查询，诗列表
    @Override
    public List<PoemDTO> listPoemsByKeyword(String keyword) {
        List<PoemDO> poemDOList = poemDao.listPoemsByKeyword(keyword);
        return DozerUtil.mapList(poemDOList, PoemDTO.class);
    }

}
