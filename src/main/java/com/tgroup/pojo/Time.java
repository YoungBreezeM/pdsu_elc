package com.tgroup.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yqf
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Time implements Serializable {
    private Integer id;
    private String time;
}
