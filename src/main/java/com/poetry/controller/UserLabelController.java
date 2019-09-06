package com.poetry.controller;

import com.poetry.common.Response;
import com.poetry.dto.LabelDTO;
import com.poetry.service.UserLabelService;
import com.poetry.utils.DozerUtil;
import com.poetry.vo.LabelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("userLabel")
public class UserLabelController {
    @Autowired
    private UserLabelService userLabelService;

    @RequestMapping(value="userLabelList", method={RequestMethod.GET, RequestMethod.POST})
    public Response<List<LabelVO>> userLabelList(@PathParam("userId") String userId) {
        List<LabelDTO> labelDTOList = userLabelService.listLabelsByUserId(userId);

        if(labelDTOList != null) {
            return Response.ok(DozerUtil.mapList(labelDTOList, LabelVO.class));
        } else {
            return Response.error();
        }
    }
}
