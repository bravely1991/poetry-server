package com.poetry.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserHomePageVO {
    private String nickname;
    private String headerImage;

    private List<LabelVO> labelList;
    private List<PoemListVO> poemListList;
}
