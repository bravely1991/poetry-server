package com.poetry.controller;

import com.github.pagehelper.util.StringUtil;
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

    // 创建新的标签
    @RequestMapping(value="labelCreate", method={RequestMethod.GET, RequestMethod.POST})
    public Response<String> labelCreate(LabelRequestDTO labelRequestDTO) {

        Boolean isSuccess = userLabelService.saveLabel(labelRequestDTO.getUserId(), labelRequestDTO.getLabelName());

        if (isSuccess == true) {
            return Response.ok("Success");
        } else {
            return Response.error();
        }
    }

    // 将指定标签添加到指定唐诗
    @RequestMapping(value="labelAdd", method={RequestMethod.GET, RequestMethod.POST})
    public Response<String> labelAdd(LabelRequestDTO labelRequestDTO) {

        Boolean isSuccess = userLabelService.savePoemLabel(labelRequestDTO.getUserId(), labelRequestDTO.getContentId(), labelRequestDTO.getLabelId());

        if (isSuccess == true) {
            return Response.ok("Success");
        } else {
            return Response.error();
        }
    }

    // 创建新的标签并添加到指定唐诗
    @RequestMapping(value="labelCreateAndAdd", method={RequestMethod.GET, RequestMethod.POST})
    public Response<String> labelCreateAndAdd(LabelRequestDTO labelRequestDTO) {

        Boolean isCreateSuccess = userLabelService.saveLabel(labelRequestDTO.getUserId(), labelRequestDTO.getLabelName());

        if (isCreateSuccess == true) {

            String labelId = userLabelService.getLabelId(labelRequestDTO.getUserId(), labelRequestDTO.getLabelName());
            if (StringUtil.isNotEmpty(labelId)) {
                Boolean isAddSuccess = userLabelService.savePoemLabel(labelRequestDTO.getUserId(), labelRequestDTO.getContentId(), labelId);
                if (isAddSuccess == true) {
                    return Response.ok("Success");
                } else {
                    // 删除新增的标签 事务统一
                    userLabelService.labelDelete(labelRequestDTO.getUserId(), labelId);
                    return Response.error();
                }
            } else {
                return Response.error();
            }

        } else {
            return Response.error();
        }
    }

    // 修改指定标签ID的标签名称
    @RequestMapping(value="labelUpdate", method={RequestMethod.GET, RequestMethod.POST})
    public Response<String> labelUpdate(LabelRequestDTO labelRequestDTO) {

        Boolean isSuccess = userLabelService.labelUpdate(labelRequestDTO.getUserId(), labelRequestDTO.getLabelId(), labelRequestDTO.getLabelName());

        if (isSuccess == true) {
            return Response.ok("Success");
        } else {
            return Response.error();
        }
    }

    // 从唐诗中添加、移除已存在的指定标签
    @RequestMapping(value="labelRemove", method={RequestMethod.GET, RequestMethod.POST})
    public Response<String> labelRemove(LabelRequestDTO labelRequestDTO) {

        Boolean isSuccess = userLabelService.poemLabelUpdate(labelRequestDTO.getUserId(), labelRequestDTO.getContentId(), labelRequestDTO.getLabelId(), labelRequestDTO.getIsRemoved());

        if (isSuccess == true) {
            return Response.ok("Success");
        } else {
            return Response.error();
        }
    }

    // 从我的标签中删除指定标签
    @RequestMapping(value="labelDelete", method={RequestMethod.GET, RequestMethod.POST})
    public Response<String> labelDelete(LabelRequestDTO labelRequestDTO) {

        Boolean isSuccess = userLabelService.labelDelete(labelRequestDTO.getUserId(), labelRequestDTO.getLabelId());

        if (isSuccess == true) {
            return Response.ok("Success");
        } else {
            return Response.error();
        }
    }

}
