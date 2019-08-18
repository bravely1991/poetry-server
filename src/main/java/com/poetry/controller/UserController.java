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

    @RequestMapping(value="userLoginOrRegister", method={RequestMethod.GET, RequestMethod.POST})
//    @RequestMapping(value = "userLoginOrRegister", produces = {"application/json;charset=UTF-8;"}, method = RequestMethod.POST)
    public Response<UserVO> userLoginOrRegister(@RequestBody UserDTO userDto) {
        // 1.查询用户是否存在
        Boolean isExitUser = userService.getIsExitUser(userDto.getUsername());
        if (isExitUser == true) {
            // 2.1 存在，则登录
            UserDTO userDTO = userService.getUserbyUsernameAndPassword(userDto.getUsername(), userDto.getPassword());
            if(userDTO != null) {
                return Response.ok(DozerUtil.map(userDTO, UserVO.class));
            } else {
                return Response.errorWithMsg("登录失败，请检查用户名和密码是否一致");
            }
        } else {
            // 2.2 不存在，则注册
            Boolean saveResult = userService.saveUser(userDto);
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

    }
}
