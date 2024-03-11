package com.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HistoryDto {
	private Integer id;
    private String latitude;
    private String longitude;
    private String searchDate;
}