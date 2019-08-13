package com.poetry.controller;


import com.poetry.dto.PoemDTO;
import com.poetry.common.Response;
import com.poetry.service.PoemService;
import com.poetry.service.UserService;
import com.poetry.utils.DozerUtil;
import com.poetry.vo.PoemDetailVO;
import com.poetry.vo.PoemListVO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("poem")
public class PoemController {
    @Autowired
    private PoemService poemService;

    @RequestMapping(value = "listPoems")
    public Response<List<PoemListVO>> listPoems() {
        List<PoemDTO> poemDtoList = poemService.listPoems();
        if(poemDtoList == null) {
            return Response.ok(DozerUtil.mapList(poemDtoList, PoemListVO.class));
        } else {
            return Response.error();
        }
    }

    @RequestMapping(value = "listPoemsByWriter")
    public Response<List<PoemListVO>> listPoemsByWriter(@PathParam("writer") String writer) {
        List<PoemDTO> poemDtoList = poemService.listPoemsByWriter(writer);
        if(poemDtoList == null) {
            return Response.ok(DozerUtil.mapList(poemDtoList, PoemListVO.class));
        } else {
            return Response.error();
        }
    }

    @RequestMapping(value = "listPoemsByKeyword")
    public Response<List<PoemListVO>> listPoemsByKeyword(@PathParam("keyword") String keyword) {
        List<PoemDTO> poemDtoList = poemService.listPoemsByKeyword(keyword);
        if(poemDtoList == null) {
            return Response.ok(DozerUtil.mapList(poemDtoList, PoemListVO.class));
        } else {
            return Response.error();
        }
    }

    @RequestMapping(value = "getPoemDetail")
    public Response<PoemDetailVO> getPoemDetail(@PathParam("contentId") String contentId) {
        PoemDTO poemDto = poemService.getPoemDetail(contentId);
        if(poemDto != null) {
            return Response.ok(DozerUtil.map(poemDto, PoemDetailVO.class));
        } else {
            return Response.error();
        }
    }

}

