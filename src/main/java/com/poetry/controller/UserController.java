package com.poetry.controller;

import com.github.pagehelper.util.StringUtil;
import com.poetry.dto.LabelDTO;
import com.poetry.dto.PoemDTO;
import com.poetry.dto.UserDTO;
import com.poetry.common.Response;
import com.poetry.service.UserLabelService;
import com.poetry.service.UserPoemService;
import com.poetry.service.UserService;
import com.poetry.utils.DozerUtil;
import com.poetry.vo.LabelVO;
import com.poetry.vo.PoemListVO;
import com.poetry.vo.UserHomePageVO;
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
    @Autowired
    private UserPoemService userPoemService;
    @Autowired
    private UserLabelService userLabelService;

    @RequestMapping(value="userLoginOrRegister", method={RequestMethod.GET, RequestMethod.POST})
//    @RequestMapping(value = "userLoginOrRegister", produces = {"application/json;charset=UTF-8;"}, method = RequestMethod.POST)
    public Response<UserVO> userLoginOrRegister(@RequestBody UserDTO userDto) {
        // 1.查询用户是否存在
        Boolean isExitUser = userService.getIsExitUser(userDto.getUsername());
        if (isExitUser == true) {
            // 2.1 存在，则登录
            UserDTO userDTO = userService.getUserByUsernameAndPassword(userDto.getUsername(), userDto.getPassword());
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
                UserDTO userDTO = userService.getUserByUsernameAndPassword(userDto.getUsername(), userDto.getPassword());
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

    @RequestMapping(value="userhomepage", method={RequestMethod.GET, RequestMethod.POST})
    public Response<UserHomePageVO> userhomepage(@PathParam("userId") String userId) {
        UserHomePageVO userHomePageVO = new UserHomePageVO();

        UserDTO userDTO = userService.getUserByUserId(userId);
        userHomePageVO.setNickname(userDTO.getNickname());
        userHomePageVO.setHeaderImage("https://b-ssl.duitang.com/uploads/item/201901/22/20190122210534_qkmxb.jpg");


        List<LabelDTO> labelDTOList = userLabelService.listLabelsByUserId(userId);
        if(labelDTOList != null) {
            List<LabelVO> labelVOList = DozerUtil.mapList(labelDTOList, LabelVO.class);
            userHomePageVO.setLabelList(labelVOList);
        }

        List<PoemDTO> poemDtoList = userPoemService.listPoemsUserCollect(userId);
        if(poemDtoList != null) {
            List<PoemListVO> poemListVOList = DozerUtil.mapList(poemDtoList, PoemListVO.class);

            for (PoemListVO poemListVO : poemListVOList) {
                List<LabelDTO> poemLabelDTOList = userLabelService.listLabelsByUserIdAndContentId(userId, poemListVO.getContentId());

                if (poemLabelDTOList != null) {
                    List<LabelVO> poemLabelVOList = DozerUtil.mapList(poemLabelDTOList, LabelVO.class);
                    poemListVO.setLabelList(poemLabelVOList);
                }
            }

            userHomePageVO.setPoemListList(poemListVOList);
        }

        return Response.ok(userHomePageVO);

    }

}
