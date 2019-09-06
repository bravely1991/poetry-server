package com.poetry.service;

import com.poetry.dto.LabelDTO;

import java.util.List;

public interface UserLabelService {
    List<LabelDTO> listLabelsByUserId(String userId);

}
