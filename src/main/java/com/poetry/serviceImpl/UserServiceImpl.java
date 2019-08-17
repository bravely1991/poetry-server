package com.poetry.serviceImpl;

import com.github.pagehelper.util.StringUtil;
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
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserPoemDao userPoemDao;

    // 用户是否存在
    @Override
    public Boolean getIsExitUser(String username) {
        return userDao.getIsExitUser(username);
    }

    @Override
    public Boolean saveUser(UserDTO userDto) {
        //生成userID
        userDto.setUserId(UUID.randomUUID().toString().replace("-", ""));
        //生成时间戳
        userDto.setCreateTime(Long.toString(System.currentTimeMillis()));

        Boolean result = userDao.saveUser(userDto.getUserId(), userDto.getUsername(), userDto.getPassword(),
                userDto.getNickname(), userDto.getCreateTime());
        return result;
    }

    //更新用户token
    @Override
    public Boolean updateUserTokenByUserId(UserDTO userDto) {
        Boolean result = userDao.updateUserTokenByUserId(userDto.getUserId(),userDto.getToken());
        return result;
    }

    @Override
    public UserDTO getUserbyUsernameAndPassword(String username, String password) {
        UserDO userDOLoginInfo = userDao.getUserByUsernameAndPassword(username, password);
        if (userDOLoginInfo != null) {
            // 更新Token
            String token = UUID.randomUUID().toString().replace("-", "");
            userDOLoginInfo.setToken(token);

            if (StringUtil.isEmpty(userDOLoginInfo.getNickname())) {
                String nickname = "唐诗家园" + userDOLoginInfo.getUserIndex() +"号成员";
                userDOLoginInfo.setNickname(nickname);
            }

            boolean updateResult = userDao.updateUserTokenByUserId(userDOLoginInfo.getUserId(), token);
            if (updateResult == true) {
                return DozerUtil.map(userDOLoginInfo, UserDTO.class);
            } else {
                return null;
            }

        } else {
            return null;
        }
    }

}
