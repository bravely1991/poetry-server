package com.poetry.controller;

import com.poetry.dto.LabelDTO;
import com.poetry.dto.PoemDTO;
import com.poetry.common.Response;
import com.poetry.dto.PoemRequestDTO;
import com.poetry.service.PoemService;
import com.poetry.service.UserLabelService;
import com.poetry.utils.DozerUtil;
import com.poetry.vo.LabelVO;
import com.poetry.vo.PoemDetailVO;
import com.poetry.vo.PoemListVO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("poem")
public class PoemController {
    @Autowired
    private PoemService poemService;
    @Autowired
    private UserLabelService userLabelService;

    @RequestMapping(value="listPoems", method={RequestMethod.GET, RequestMethod.POST})
    public Response<List<PoemListVO>> listPoems(PoemRequestDTO poemRequestDTO) {
        List<PoemDTO> poemDtoList = poemService.listPoems();
        if(poemDtoList != null) {
            List<PoemListVO> poemListVOList = DozerUtil.mapList(poemDtoList, PoemListVO.class);

            for (PoemListVO poemListVO : poemListVOList) {
                List<LabelDTO> poemLabelDTOList = userLabelService.listLabelsByUserIdAndContentId(poemRequestDTO.getUserId(), poemListVO.getContentId());

                if (poemLabelDTOList != null) {
                    List<LabelVO> poemLabelVOList = DozerUtil.mapList(poemLabelDTOList, LabelVO.class);
                    poemListVO.setLabelList(poemLabelVOList);
                }
            }
            return Response.ok(poemListVOList);
        } else {
            return Response.error();
        }
    }

    @RequestMapping(value="listPoemsByWriter", method={RequestMethod.GET, RequestMethod.POST})
    public Response<List<PoemListVO>> listPoemsByWriter(PoemRequestDTO poemRequestDTO) {
        List<PoemDTO> poemDtoList = poemService.listPoemsByWriter(poemRequestDTO.getWriter());
        if(poemDtoList != null) {
            List<PoemListVO> poemListVOList = DozerUtil.mapList(poemDtoList, PoemListVO.class);

            for (PoemListVO poemListVO : poemListVOList) {
                List<LabelDTO> poemLabelDTOList = userLabelService.listLabelsByUserIdAndContentId(poemRequestDTO.getUserId(), poemListVO.getContentId());

                if (poemLabelDTOList != null) {
                    List<LabelVO> poemLabelVOList = DozerUtil.mapList(poemLabelDTOList, LabelVO.class);
                    poemListVO.setLabelList(poemLabelVOList);
                }
            }
            return Response.ok(poemListVOList);
        } else {
            return Response.error();
        }
    }

    @RequestMapping(value="listPoemsByKeyword", method={RequestMethod.GET, RequestMethod.POST})
    public Response<List<PoemListVO>> listPoemsByKeyword(PoemRequestDTO poemRequestDTO) {
        List<PoemDTO> poemDtoList = poemService.listPoemsByKeyword(poemRequestDTO.getKeyword());
        if(poemDtoList != null) {
            List<PoemListVO> poemListVOList = DozerUtil.mapList(poemDtoList, PoemListVO.class);

            for (PoemListVO poemListVO : poemListVOList) {
                List<LabelDTO> poemLabelDTOList = userLabelService.listLabelsByUserIdAndContentId(poemRequestDTO.getUserId(), poemListVO.getContentId());

                if (poemLabelDTOList != null) {
                    List<LabelVO> poemLabelVOList = DozerUtil.mapList(poemLabelDTOList, LabelVO.class);
                    poemListVO.setLabelList(poemLabelVOList);
                }
            }
            return Response.ok(poemListVOList);
        } else {
            return Response.error();
        }
    }

    @RequestMapping(value="getPoemDetail", method={RequestMethod.GET, RequestMethod.POST})
    public Response<PoemDetailVO> getPoemDetail(PoemRequestDTO poemRequestDTO) {
        PoemDTO poemDto = poemService.getPoemDetail(poemRequestDTO.getContentId());


        if(poemDto != null) {
            PoemDetailVO poemDetailVO = DozerUtil.map(poemDto, PoemDetailVO.class);



            List<LabelDTO> labelDTOList = userLabelService.listLabelsByUserIdAndContentId(poemRequestDTO.getUserId(), poemRequestDTO.getContentId());
            if(labelDTOList != null) {
                List<LabelVO> labelVOList = DozerUtil.mapList(labelDTOList, LabelVO.class);

                for (LabelVO labelVO : labelVOList) {
                    Boolean isAdded = userLabelService.getIsPoemLabelAdded(poemRequestDTO.getUserId(), poemRequestDTO.getContentId(), labelVO.getLabelId());
                    labelVO.setIsAdded(isAdded);
                }

                poemDetailVO.setLabelList(labelVOList);
            }

            return Response.ok(poemDetailVO);
        } else {
            return Response.error();
        }
    }

}
