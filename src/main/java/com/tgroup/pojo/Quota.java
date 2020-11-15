package com.tgroup.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yqf
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quota implements Serializable {
    private Integer id;
    private String indexName;
    private String department;
    private float weight;
    private String circle;
    private String unit;
    private Double allScore;
    private Integer moduleId;
    private Double value;
}
