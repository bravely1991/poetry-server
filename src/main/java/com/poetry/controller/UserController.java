package com.poetry.controller;

import com.poetry.dto.PoemDTO;
import com.poetry.dto.UserDTO;
import com.poetry.common.Response;
import com.poetry.service.UserService;
import com.poetry.utils.DozerUtil;
import com.poetry.vo.PoemListVO;
import com.poetry.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "userLogin")
    public Response<UserVO> getUserbyUsernameAndPassword(@RequestBody UserDTO userDto) {
        UserDTO userDtoReturn = userService.getUserbyUsernameAndPassword(userDto);
        if(userDtoReturn != null) {

            //生成token
            String token = UUID.randomUUID().toString().replace("-", "");
            userDtoReturn.setToken(token);
            boolean updateResult = userService.updateUserTokenByUserId(userDtoReturn);

            if(updateResult == true) {
                return Response.ok(DozerUtil.map(userDtoReturn, UserVO.class));
            } else {
                return Response.errorWithMsg("更新用户token失败，请重新登录");
            }

        } else {
            return Response.error();
        }
    }

    @RequestMapping(value = "userRegister")
    public Response<UserVO> saveUser(@RequestBody UserDTO userDto) {
        boolean saveResult = userService.saveUser(userDto);
        if(saveResult == true) {
            UserDTO userDtoLoginInfo = userService.getUserbyUsernameAndPassword(userDto);
            if(userDtoLoginInfo != null) {
                return Response.ok(DozerUtil.map(userDtoLoginInfo, UserVO.class));
            } else {
                return Response.error();
            }
        } else {
            return Response.errorWithMsg("注册失败，用户名已被注册");
        }

    }

    @PostMapping(value = "userCollectPoemList")
    public Response<List<PoemListVO>> listPoemsUserCollect(@RequestBody UserDTO userDto) {
        List<PoemDTO> poemDtoList = userService.listPoemsUserCollect(userDto);

        if(poemDtoList != null) {
            return Response.ok(DozerUtil.mapList(poemDtoList, PoemListVO.class));
        } else {
            return Response.error();
        }
    }

    @PostMapping(value = "userMemorizePoemList")
    public Response<List<PoemListVO>> listPoemsUserMemorize(@RequestBody UserDTO userDto) {
        List<PoemDTO> poemDtoList = userService.listPoemsUserCollect(userDto);

        if(poemDtoList != null) {
            return Response.ok(DozerUtil.mapList(poemDtoList, PoemListVO.class));
        } else {
            return Response.error();
        }
    }

    @PostMapping(value = "userPreparPoemList")
    public Response<List<PoemListVO>> listPoemsUserPrepare(@RequestBody UserDTO userDto) {
        List<PoemDTO> poemDtoList = userService.listPoemsUserCollect(userDto);

        if(poemDtoList != null) {
            return Response.ok(DozerUtil.mapList(poemDtoList, PoemListVO.class));
        } else {
            return Response.error();
        }
    }
}
