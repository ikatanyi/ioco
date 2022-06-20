package com.ioco.apocalypse.robot.data;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RobotDto {
    private String model;
    private String serialNumber;
    private String manufacturedDate;
    private String category;
}
