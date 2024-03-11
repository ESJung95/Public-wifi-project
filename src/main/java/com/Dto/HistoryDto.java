package com.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// 히스토리 필요한 정보

@Getter
@Setter
@AllArgsConstructor
public class HistoryDto {
	private Integer id;
    private String latitude;
    private String longitude;
    private String searchDate;
}