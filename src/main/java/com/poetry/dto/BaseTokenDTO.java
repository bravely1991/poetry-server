package com.poetry.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseTokenDTO implements Serializable {

    private String token;

    public static class UserPoemDTO {

    }
}
