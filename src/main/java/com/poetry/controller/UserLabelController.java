package com.poetry.controller;

import com.poetry.common.Response;
import com.poetry.dto.LabelDTO;
import com.poetry.dto.LabelRequestDTO;
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

    @RequestMapping(value="labelCreate", method={RequestMethod.GET, RequestMethod.POST})
    public Response<String> labelCreate(LabelRequestDTO labelRequestDTO) {

        Boolean isSuccess = userLabelService.saveLabel(labelRequestDTO.getUserId(), labelRequestDTO.getLabelName());

        if (isSuccess == true) {
            return Response.ok("Success");
        } else {
            return Response.error();
        }
    }

    @RequestMapping(value="LabelAdd", method={RequestMethod.GET, RequestMethod.POST})
    public Response<String> LabelAdd(LabelRequestDTO labelRequestDTO) {

        Boolean isSuccess = userLabelService.savePoemLabel(labelRequestDTO.getUserId(), labelRequestDTO.getContentId(), labelRequestDTO.getLabelId());

        if (isSuccess == true) {
            return Response.ok("Success");
        } else {
            return Response.error();
        }
    }

    @RequestMapping(value="LabelCreateAndAdd", method={RequestMethod.GET, RequestMethod.POST})
    public Response<String> LabelCreateAndAdd(LabelRequestDTO labelRequestDTO) {

        Boolean isCreateSuccess = userLabelService.saveLabel(labelRequestDTO.getUserId(), labelRequestDTO.getLabelName());

        if (isCreateSuccess == true) {
            Boolean isAddSuccess = userLabelService.savePoemLabel(labelRequestDTO.getUserId(), labelRequestDTO.getContentId(), labelRequestDTO.getLabelId());

            if (isAddSuccess == true) {
                return Response.ok("Success");
            } else {
                // 删除新增的标签 事务统一
                return Response.error();
            }
        } else {
            return Response.error();
        }
    }

    // 未完成
    @RequestMapping(value="LabelRemove", method={RequestMethod.GET, RequestMethod.POST})
    public Response<String> LabelRemove(LabelRequestDTO labelRequestDTO) {

        Boolean isSuccess = userLabelService.savePoemLabel(labelRequestDTO.getUserId(), labelRequestDTO.getContentId(), labelRequestDTO.getLabelId());

        if (isSuccess == true) {
            return Response.ok("Success");
        } else {
            return Response.error();
        }
    }

}
