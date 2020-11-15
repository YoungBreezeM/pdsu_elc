package com.tgroup.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


/**
 * @author yqf
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Base  {


    @ExcelProperty(value = {"指标名称"},index = 1)
    private String indexName;

    @ExcelProperty(value = {"牵头部门"},index = 2)
    private String department;

    @ExcelProperty(value = {"权重"},index = 3)
    private String weight;

    @ExcelProperty(value = {"周期"},index = 4)
    private String circle;

    @ExcelProperty(value = {"单位"},index = 5)
    private String unit;

    @ExcelProperty(index = 8)
    private String time;

    @ExcelProperty(index = 9,converter = ClearErrorCellConverter.class)
    private Double c1;

    @ExcelProperty(index = 10,converter = ClearErrorCellConverter.class)
    private Double c2;

    @ExcelProperty(index = 11,converter = ClearErrorCellConverter.class)
    private Double c3;

    @ExcelProperty(index = 12,converter = ClearErrorCellConverter.class)
    private Double c4;

    @ExcelProperty(index = 13,converter = ClearErrorCellConverter.class)
    private Double c5;

    @ExcelProperty(index = 14,converter = ClearErrorCellConverter.class)
    private Double c6;

    @ExcelProperty(index = 15,converter = ClearErrorCellConverter.class)
    private Double c7;

    @ExcelProperty(index = 16,converter = ClearErrorCellConverter.class)
    private Double c8;

    @ExcelProperty(index = 17,converter = ClearErrorCellConverter.class)
    private Double c9;

    @ExcelProperty(index = 18,converter = ClearErrorCellConverter.class)
    private Double c10;

    @ExcelProperty(index = 19,converter = ClearErrorCellConverter.class)
    private Double c11;

    @ExcelProperty(index = 20,converter = ClearErrorCellConverter.class)
    private Double c12;

    @ExcelProperty(index = 21,converter = ClearErrorCellConverter.class)
    private Double c13;

    @ExcelProperty(index = 22,converter = ClearErrorCellConverter.class)
    private Double c14;

    @ExcelProperty(index = 23,converter = ClearErrorCellConverter.class)
    private Double c15;

    @ExcelProperty(index = 24,converter = ClearErrorCellConverter.class)
    private Double c16;

    @ExcelProperty(index = 25,converter = ClearErrorCellConverter.class)
    private Double c17;

    @ExcelProperty(index = 26,converter = ClearErrorCellConverter.class)
    private Double c18;


    public List<Double> getValues(){
        List<Double> values = new ArrayList<>();

        values.add(c1);
        values.add(c2);
        values.add(c3);
        values.add(c4);
        values.add(c5);
        values.add(c6);
        values.add(c7);
        values.add(c8);
        values.add(c9);
        values.add(c10);
        values.add(c11);
        values.add(c12);
        values.add(c13);
        values.add(c14);
        values.add(c15);
        values.add(c16);
        values.add(c17);
        values.add(c18);


        return values;

    }

}
