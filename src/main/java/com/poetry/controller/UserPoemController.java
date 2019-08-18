package com.poetry.controller;

import com.poetry.common.Response;
import com.poetry.dto.PoemDTO;
import com.poetry.dto.UserPoemDTO;
import com.poetry.service.UserPoemService;
import com.poetry.utils.DozerUtil;
import com.poetry.vo.PoemListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("userPoem")
public class UserPoemController {
    @Autowired
    private UserPoemService userPoemService;

    @RequestMapping(value="userPoemCollect", method={RequestMethod.GET, RequestMethod.POST})
    public Response<Void> userPoemCollect(UserPoemDTO userPoemDTO) {

        Boolean result = userPoemService.userPoemCollect(userPoemDTO.getUserId(), userPoemDTO.getContentId(),
                userPoemDTO.getIsCollect());

        if(result == true) {
            return Response.ok(null);
        } else {
            return Response.error();
        }
    }

    @RequestMapping(value="userCollectPoemList", method={RequestMethod.GET, RequestMethod.POST})
    public Response<List<PoemListVO>> listPoemsUserCollect(@PathParam("userId") String userId) {
        List<PoemDTO> poemDtoList = userPoemService.listPoemsUserCollect(userId);

        if(poemDtoList != null) {
            return Response.ok(DozerUtil.mapList(poemDtoList, PoemListVO.class));
        } else {
            return Response.error();
        }
    }

    @PostMapping(value = "userMemorizePoemList")
    public Response<List<PoemListVO>> listPoemsUserMemorize(@PathParam("userId") String userId) {
        List<PoemDTO> poemDtoList = userPoemService.listPoemsUserCollect(userId);

        if(poemDtoList != null) {
            return Response.ok(DozerUtil.mapList(poemDtoList, PoemListVO.class));
        } else {
            return Response.error();
        }
    }

    @PostMapping(value = "userPreparPoemList")
    public Response<List<PoemListVO>> listPoemsUserPrepare(@PathParam("userId") String userId) {
        List<PoemDTO> poemDtoList = userPoemService.listPoemsUserCollect(userId);

        if(poemDtoList != null) {
            return Response.ok(DozerUtil.mapList(poemDtoList, PoemListVO.class));
        } else {
            return Response.error();
        }
    }
}
