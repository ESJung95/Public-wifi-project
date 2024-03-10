package com.Dto;

import java.util.List;

import lombok.Data;

@Data
public class MainDto {
    private String totaCount;
    private List<WifiDto> row;
}