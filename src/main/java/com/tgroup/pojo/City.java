package com.tgroup.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author yqf
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City implements Serializable {

    private int id;
    private String cityName;
    private List<QuotaData> quotaDataList;

}
