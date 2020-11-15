package com.tgroup.domain;

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
public class CityValue implements Serializable {

    private String cityName;

    private Double value;
}
