package com.tgroup.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yqf
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuotaData implements Serializable {
    private Integer id;
    private Double value;
    private Integer cityId;
    private Integer timeId;
    private Integer indexId;
    private Date uploadDate;
}
