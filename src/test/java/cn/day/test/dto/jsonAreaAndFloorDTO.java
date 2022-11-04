package cn.day.test.dto;

import lombok.Data;

/**
 * @author ZhengChangBing
 * @Date 2022/10/28 17:22
 * @Description
 */
@Data
public class jsonAreaAndFloorDTO {
    private String floorId;

    private String floorName;

    private String areaId;

    private String areaName;

    private String freeSpaceNum;

    private String totalSpaceNum;
}
