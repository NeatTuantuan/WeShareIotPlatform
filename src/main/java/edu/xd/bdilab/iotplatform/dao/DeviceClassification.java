package edu.xd.bdilab.iotplatform.dao;

import lombok.Data;

@Data
public class DeviceClassification {

    private int id;

    private String fkDeviceId;

    private int fkCategoryId;

}
