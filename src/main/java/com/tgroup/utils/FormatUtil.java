package com.tgroup.utils;

import java.text.DecimalFormat;

/**
 * @author yqf
 */
public class FormatUtil {
    private static DecimalFormat decimalFormat;


    public static Double toDFormat(Double data){
        if(decimalFormat==null){
            decimalFormat = new DecimalFormat("#.000");
        }
        String format = decimalFormat.format(data);

        return Double.parseDouble(format);
    }
}
