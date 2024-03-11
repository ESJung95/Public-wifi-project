package com.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DistanceDto {
    private String location;
    private double distance;

    public DistanceDto() {
    }

    public DistanceDto(String location, double distance) {
        this.location = location;
        this.distance = distance;
    }
}