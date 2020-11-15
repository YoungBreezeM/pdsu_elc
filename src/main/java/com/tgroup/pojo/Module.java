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
@AllArgsConstructor
@NoArgsConstructor
public class Module implements Serializable {
    private Integer id;
    private String moduleName;
    private Integer allScore;
    private Integer weight;
    private Double value;
    private List<Quota> quotas;
}
