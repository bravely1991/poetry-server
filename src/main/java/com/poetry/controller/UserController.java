package com.poetry.controller;

import com.github.pagehelper.util.StringUtil;
import com.poetry.dto.PoemDTO;
import com.poetry.dto.UserDTO;
import com.poetry.common.Response;
import com.poetry.service.UserService;
import com.poetry.utils.DozerUtil;
import com.poetry.vo.PoemListVO;
import com.poetry.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "userLogin", method = RequestMethod.POST)
    public Response<UserVO> getUserbyUsernameAndPassword(@RequestBody UserDTO userDto) {
        UserDTO userDTO = userService.getUserbyUsernameAndPassword(userDto.getUsername(), userDto.getPassword());
        if(userDTO != null) {
            return Response.ok(DozerUtil.map(userDTO, UserVO.class));
        } else {
            return Response.error();
        }
    }

    @RequestMapping(value = "userRegister", method = RequestMethod.POST)
    public Response<UserVO> saveUser(@RequestBody UserDTO userDto) {
        // 1.保存用户信息
        boolean saveResult = userService.saveUser(userDto);
        if(saveResult == true) {
            // 2.查询用户登录信息
            UserDTO userDTO = userService.getUserbyUsernameAndPassword(userDto.getUsername(), userDto.getPassword());
            if(userDTO != null) {
                return Response.ok(DozerUtil.map(userDTO, UserVO.class));
            } else {
                return Response.error();
            }
        } else {
            return Response.errorWithMsg("注册失败，用户名已被注册");
        }

    }

    @PostMapping(value = "userCollectPoemList")
    public Response<List<PoemListVO>> listPoemsUserCollect(@PathParam("userId") String userId) {
        List<PoemDTO> poemDtoList = userService.listPoemsUserCollect(userId);

        if(poemDtoList != null) {
            return Response.ok(DozerUtil.mapList(poemDtoList, PoemListVO.class));
        } else {
            return Response.error();
        }
    }

    @PostMapping(value = "userMemorizePoemList")
    public Response<List<PoemListVO>> listPoemsUserMemorize(@PathParam("userId") String userId) {
        List<PoemDTO> poemDtoList = userService.listPoemsUserCollect(userId);

        if(poemDtoList != null) {
            return Response.ok(DozerUtil.mapList(poemDtoList, PoemListVO.class));
        } else {
            return Response.error();
        }
    }

    @PostMapping(value = "userPreparPoemList")
    public Response<List<PoemListVO>> listPoemsUserPrepare(@PathParam("userId") String userId) {
        List<PoemDTO> poemDtoList = userService.listPoemsUserCollect(userId);

        if(poemDtoList != null) {
            return Response.ok(DozerUtil.mapList(poemDtoList, PoemListVO.class));
        } else {
            return Response.error();
        }
    }
}
