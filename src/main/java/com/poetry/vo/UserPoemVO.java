package com.poetry.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserPoemVO {
    private List<LabelVO> labelList;
    private List<PoemListVO> poemList;
}
