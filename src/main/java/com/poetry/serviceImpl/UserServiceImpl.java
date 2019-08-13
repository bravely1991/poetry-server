package com.poetry.serviceImpl;

import com.poetry.dao.PoemDao;
import com.poetry.dao.UserDao;
import com.poetry.dao.UserPoemDao;
import com.poetry.dto.PoemDTO;
import com.poetry.dto.UserDTO;
import com.poetry.entity.PoemDO;
import com.poetry.entity.UserDO;
import com.poetry.service.PoemService;
import com.poetry.service.UserService;
import com.poetry.utils.DozerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserPoemDao userPoemDao;

    @Override
    public Boolean saveUser(UserDTO userDto) {
        Boolean result = userDao.saveUser(userDto.getUserId(), userDto.getUsername(), userDto.getPassword());
        return result;
    }

    //更新用户token
    @Override
    public Boolean updateUserTokenByUserId(UserDTO userDto) {
        Boolean result = userDao.updateUserTokenByUserId(userDto.getUserId(),userDto.getToken());
        return result;
    }

    @Override
    public UserDTO getUserbyUsernameAndPassword(UserDTO userDto) {
        UserDO userDOLoginInfo = userDao.getUserbyUsernameAndPassword(userDto.getUsername(), userDto.getPassword());
        return (userDOLoginInfo != null) ? DozerUtil.map(userDOLoginInfo, UserDTO.class) : null;
    }

    //用户收藏诗列表
    @Override
    @Transactional
    public List<PoemDTO> listPoemsUserCollect(UserDTO userDto) {
        List<PoemDO> poemDOList = userPoemDao.listPoemsUserCollectByUserId(userDto.getUserId());
        return DozerUtil.mapList(poemDOList, PoemDTO.class);
    }

    @Override
    @Transactional
    public List<PoemDTO> listPoemsUserMemorize(UserDTO userDto) {
        List<PoemDO> poemDOList = userPoemDao.listPoemsUserMemorizedByUserId(userDto.getUserId());
        return DozerUtil.mapList(poemDOList, PoemDTO.class);
    }

    @Override
    @Transactional
    public List<PoemDTO> listPoemsUserPreparing(UserDTO userDto) {
        List<PoemDO> poemDOList = userPoemDao.listPoemsUserPreparingByUserId(userDto.getUserId());
        return DozerUtil.mapList(poemDOList, PoemDTO.class);
    }

}
