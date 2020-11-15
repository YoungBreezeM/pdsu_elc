package com.tgroup.utils;

import com.tgroup.domain.CityValue;

import java.util.Comparator;

class Ascending2 implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Double first = ((CityValue)o1).getValue();
        Double second = ((CityValue)o2).getValue();

        Double dif = first - second;
        if (dif > 0){
            return 1;
        }else if (dif < 0){
            return -1;
        }else {
            return 0;
        }
    }
}