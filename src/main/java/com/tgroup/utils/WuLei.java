package com.tgroup.utils;

import com.tgroup.domain.CityValue;
import com.tgroup.pojo.City;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

public class WuLei {
    public static List<Set<String>> five5(List<CityValue> list){
        Double max1, max2, max3, max4, max5, min;
        int index1=0, index2=0, index3=0, index4 = 0;
        list.sort(new Ascending2());
        for (CityValue value : list) {
            System.out.println(value.getCityName() + "  " + value.getValue());
        }
        List<Double> datas = list.stream().map(CityValue::getValue).collect(Collectors.toList());
        //获取到cityName城市名称数据
        List<String> citys = list.stream().map(CityValue::getCityName).collect(Collectors.toList());
        //datas2放17个差值
        List<Double> datas2 = new ArrayList<>();
        //same判断相同最大值的个数
        int sameMax = 0;
        //same判断相同最小值的个数
        int sameMin = 0;
        //存放要返回的分类列表 及5个存放对应类列表
        List<Set<String>> cityNums = new ArrayList<>();
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        Set<String> set3 = new HashSet<>();
        Set<String> set4 = new HashSet<>();
        Set<String> set5 = new HashSet<>();
        //格式化datas2中存放的差值
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getPercentInstance();
        decimalFormat.applyPattern("00"); //00表示小数点2位
        decimalFormat.setMaximumFractionDigits(3); //2表示精确到小数点后2位
        for (int i = 0; i < datas.size() - 1; i++) {
            datas2.add(Double.parseDouble(decimalFormat.format(datas.get(i+1) - datas.get(i))));
        }
        System.out.println("datas2中的差值数据为: ");
        for (int i = 0; i < datas2.size(); i++) {
            System.out.print(citys.get(i) + "和" + citys.get(i+1) + "-(" + datas2.get(i) + ")" + " ");
        }
        max1 = Collections.max(datas2);
        for (Double aDouble : datas2) {
            if (aDouble.equals(max1)) {
                sameMax += 1;
            }
        }
        min = Collections.min(datas2);
        for (Double aDouble : datas2) {
            if (aDouble.equals(min)) {
                sameMin += 1;
            }
        }
        System.out.println();
        //复制一份datas2中的数据
        List<Double> datas2Copy = new ArrayList<>(datas2);
        Set<Double> data2Set = new HashSet<>(datas2);
        //差值都为0
        if (data2Set.size() == 1 && datas2.get(0) == 0){
            System.out.println("执行了方法1");
            set1.addAll(citys);
            cityNums.add(set1);
            return cityNums;
        }
        //只能分为 1 2 3 4 类的情况
        if (sameMin >= 14 && sameMin <= 16){
            System.out.println("sameMax=" + sameMax);
            System.out.println("sameMin=" + sameMin);
            //分一类
            if (data2Set.size() == 1 && datas2.get(0) != 0){
                System.out.println("执行了方法1");
                set1.addAll(citys);
                cityNums.add(set1);
                return cityNums;
            }
            //分两类
            else if (data2Set.size() == 2 && sameMax == 1){
                System.out.println("执行了方法2");
                max1 = Collections.max(datas2);
                index1 = datas2.indexOf(max1);
                for (int i = 0; i <= index1; i++) {
                    set1.add(citys.get(i));
                }
                cityNums.add(set1);
                for (int i = index1+1; i < citys.size(); i++) {
                    set2.add(citys.get(i));
                }
                cityNums.add(set2);
                return cityNums;
            }
            //分三类
            else if((data2Set.size() == 3 && sameMin == 15) || (data2Set.size() == 2 && sameMax == 2)){
                System.out.println("执行了方法3");
                Collections.sort(datas2Copy);
                //找到两个最大值下标。并把下标排序
                max1 = datas2Copy.get(datas2Copy.size()-1);
                max2 = datas2Copy.get(datas2Copy.size()-2);
                index1 = datas2.indexOf(max1);
                index2 = datas2.lastIndexOf(max2);
                List<Integer> index = new ArrayList<>();
                index.add(index1);
                index.add(index2);
                Collections.sort(index);
                index1 = index.get(0);
                index2 = index.get(1);
                
                for (int i = 0; i <= index1; i++) {
                    set1.add(citys.get(i));
                }
                cityNums.add(set1);
                for (int i = index1+1; i <= index2; i++) {
                    set2.add(citys.get(i));
                }
                cityNums.add(set2);
                for (int i = index2+1; i < citys.size(); i++) {
                    set3.add(citys.get(i));
                }
                cityNums.add(set3);
                return cityNums;
            }
            //分四类
            else {
                System.out.println("执行了方法4");
                System.out.println("data2Sort长度: " + data2Set.size());
                //4.1 长度为2，三个最大值相等的情况
                if (data2Set.size() == 2 && sameMax == 3){
                    System.out.println("执行了方法4.1");
                    Collections.sort(datas2Copy);
                    max1 = datas2Copy.get(datas2Copy.size()-1);
                    index1 = datas2.indexOf(max1);
                    index3 = datas2.lastIndexOf(max1);
                    for (int i = 0; i < datas2.size(); i++) {
                        if (datas2.get(i) == max1 && i != index1 && i != index2){
                            index2 = i;
                            break;
                        }
                    }
                    for (int i = 0; i <= index1; i++) {
                        set1.add(citys.get(i));
                    }
                    cityNums.add(set1);
                    for (int i = index1+1; i <= index2; i++) {
                        set2.add(citys.get(i));
                    }
                    cityNums.add(set2);
                    for (int i = index2+1; i <= index3; i++) {
                        set3.add(citys.get(i));
                    }
                    cityNums.add(set3);
                    for (int i = index3+1; i < citys.size(); i++) {
                        set4.add(citys.get(i));
                    }
                    cityNums.add(set4);
                }
                //4.2 长度为3，两个最大值相等，一个次大值
                else if (data2Set.size() == 3 && sameMax == 2 && sameMin == 14){
                    System.out.println("执行了方法4.2");
                    Collections.sort(datas2Copy);
                    max1 = datas2Copy.get(datas2Copy.size() - 1);
                    index1 = datas2.indexOf(max1);
                    index2 = datas2.lastIndexOf(max1);
                    max2 = datas2Copy.get(datas2Copy.size() - 3);
                    index3 = datas2.indexOf(max2);
                    List<Integer> index = new ArrayList<>();
                    index.add(index1);
                    index.add(index2);
                    index.add(index3);
                    Collections.sort(index);
                    index1 = index.get(0);
                    index2 = index.get(1);
                    index3 = index.get(2);
                    System.out.println("坐标1 " + index1 + " ");
                    System.out.println("坐标2 " + index2 + " ");
                    System.out.println("坐标3 " + index3 + " ");
                    for (int i = 0; i <= index1; i++) {
                        set1.add(citys.get(i));
                    }
                    cityNums.add(set1);
                    for (int i = index1+1; i <= index2; i++) {
                        set2.add(citys.get(i));
                    }
                    cityNums.add(set2);
                    for (int i = index2+1; i <= index3; i++) {
                        set3.add(citys.get(i));
                    }
                    cityNums.add(set3);
                    for (int i = index3+1; i < citys.size(); i++) {
                        set4.add(citys.get(i));
                    }
                    cityNums.add(set4);
                }
                //4.3 长度为3，一个最大值，两个次大值相等
                else if (data2Set.size() == 3 && sameMax == 1 && sameMin == 14){
                    System.out.println("执行了方法4.3");
                    Collections.sort(datas2Copy);
                    max1 = datas2Copy.get(datas2Copy.size() - 1);
                    index1 = datas2.indexOf(max1);
                    max2 = datas2Copy.get(datas2Copy.size() - 2);
                    index2 = datas2.indexOf(max2);
                    index3 = datas2.lastIndexOf(max2);
                    List<Integer> index = new ArrayList<>();
                    index.add(index1);
                    index.add(index2);
                    index.add(index3);
                    Collections.sort(index);
                    index1 = index.get(0);
                    index2 = index.get(1);
                    index3 = index.get(2);
                    System.out.println("坐标1: " + index1 + " ");
                    System.out.println("坐标2: " + index2 + " ");
                    System.out.println("坐标3: " + index3 + " ");
                    for (int i = 0; i <= index1; i++) {
                        set1.add(citys.get(i));
                    }
                    cityNums.add(set1);
                    for (int i = index1+1; i <= index2; i++) {
                        set2.add(citys.get(i));
                    }
                    cityNums.add(set2);
                    for (int i = index2+1; i <= index3; i++) {
                        set3.add(citys.get(i));
                    }
                    cityNums.add(set3);
                    for (int i = index3+1; i < citys.size(); i++) {
                        set4.add(citys.get(i));
                    }
                    cityNums.add(set4);
                }
                //4.4 长度为4，三个最大值都不相等
                else if (data2Set.size() == 4 && sameMin == 14){
                    System.out.println("执行了方法4.4");
                    Collections.sort(datas2Copy);
                    max1 = datas2Copy.get(datas2Copy.size() - 1);
                    index1 = datas2.indexOf(max1);
                    max2 = datas2Copy.get(datas2Copy.size() - 2);
                    index2 = datas2.indexOf(max2);
                    max3 = datas2Copy.get(datas2Copy.size() - 3);
                    index3 = datas2.indexOf(max3);
                    List<Integer> index = new ArrayList<>();
                    index.add(index1);
                    index.add(index2);
                    index.add(index3);
                    Collections.sort(index);
                    index1 = index.get(0);
                    index2 = index.get(1);
                    index3 = index.get(2);
                    System.out.println("坐标1" + index1 + " ");
                    System.out.println("坐标2" + index2 + " ");
                    System.out.println("坐标3" + index3 + " ");
                    for (int i = 0; i <= index1; i++) {
                        set1.add(citys.get(i));
                    }
                    cityNums.add(set1);
                    for (int i = index1+1; i <= index2; i++) {
                        set2.add(citys.get(i));
                    }
                    cityNums.add(set2);
                    for (int i = index2+1; i <= index3; i++) {
                        set3.add(citys.get(i));
                    }
                    cityNums.add(set3);
                    for (int i = index3+1; i < citys.size(); i++) {
                        set4.add(citys.get(i));
                    }
                    cityNums.add(set4);
                }
                return cityNums;
            }
        }
        //分五类
        if (data2Set.size() >= 5 && sameMin <= 13){
            System.out.println("执行了方法5.1");
            Collections.sort(datas2Copy);
            max1 = datas2Copy.get(datas2Copy.size()-1);
            max2 = datas2Copy.get(datas2Copy.size()-2);
            max3 = datas2Copy.get(datas2Copy.size()-3);
            max4 = datas2Copy.get(datas2Copy.size()-4);
            System.out.print("最大值: ");
            System.out.print(max1 + " ");
            System.out.print(max2 + " ");
            System.out.print(max3 + " ");
            System.out.println(max4);
            index1 = datas2.lastIndexOf(max1);
            index2 = datas2.lastIndexOf(max2);
            index3 = datas2.indexOf(max3);
            index4 = datas2.lastIndexOf(max4);
            List<Integer> index = new ArrayList<>();
            index.add(index1);
            index.add(index2);
            index.add(index3);
            index.add(index4);
            Collections.sort(index);
            index1 = index.get(0);
            index2 = index.get(1);
            index3 = index.get(2);
            index4 = index.get(3);
            System.out.print("下标: ");
            System.out.print(index1 + " ");
            System.out.print(index2 + " ");
            System.out.print(index3 + " ");
            System.out.println(index4 + " ");
            for (int i = 0; i <= index1; i++) {
                set1.add(citys.get(i));
            }
            System.out.println("第一个set中的数据");
            for (String s : set1) {
                System.out.print(s + " ");
            }
            cityNums.add(set1);
            for (int i = index1+1; i <= index2; i++) {
                set2.add(citys.get(i));
            }
            cityNums.add(set2);
            for (int i = index2+1; i <= index3; i++) {
                set3.add(citys.get(i));
            }
            cityNums.add(set3);
            for (int i = index3+1; i <= index4; i++) {
                set4.add(citys.get(i));
            }
            cityNums.add(set4);
            for (int i = index4+1; i < citys.size(); i++) {
                set5.add(citys.get(i));
            }
            cityNums.add(set5);
            return cityNums;
        }
        else if (data2Set.size() == 2 && sameMin <= 13){
            System.out.println("执行了方法5.2");
            Collections.sort(datas2Copy);
            max1 = datas2Copy.get(datas2Copy.size()-1);
            index1 = datas2.indexOf(max1);
            index2 = datas2.lastIndexOf(max1);
            for (int i = 0; i < datas2.size(); i++) {
                if (datas2.get(i) == max1 && i != index1 && i != index2){
                    index3 = i;
                    break;
                }
            }
            for (int i = 0; i < datas2.size(); i++) {
                if (datas2.get(i) == max1 && i != index1 && i != index2 && i != index3){
                    index4 = i;
                    break;
                }
            }
            List<Integer> index = new ArrayList<>();
            index.add(index1);
            index.add(index2);
            index.add(index3);
            index.add(index4);
            Collections.sort(index);
            index1 = index.get(0);
            index2 = index.get(1);
            index3 = index.get(2);
            index4 = index.get(3);
            System.out.print("下标: ");
            System.out.print(index1 + " ");
            System.out.print(index2 + " ");
            System.out.print(index3 + " ");
            System.out.println(index4 + " ");
            for (int i = 0; i <= index1; i++) {
                set1.add(citys.get(i));
            }
            cityNums.add(set1);
            for (int i = index1+1; i <= index2; i++) {
                set2.add(citys.get(i));
            }
            cityNums.add(set2);
            for (int i = index2+1; i <= index3; i++) {
                set3.add(citys.get(i));
            }
            cityNums.add(set3);
            for (int i = index3+1; i <= index4; i++) {
                set4.add(citys.get(i));
            }
            cityNums.add(set4);
            for (int i = index4+1; i < citys.size(); i++) {
                set5.add(citys.get(i));
            }
            cityNums.add(set5);
            return cityNums;
        }
        else if (data2Set.size() == 3 && sameMin <= 13 && sameMax == 1){
            System.out.println("执行了方法5.3");
            Collections.sort(datas2Copy);
            max1 = datas2Copy.get(datas2Copy.size()-1);
            max2 = datas2Copy.get(datas2Copy.size()-2);
            index1 = datas2.indexOf(max1);
            index2 = datas2.indexOf(max2);
            index4 = datas2.lastIndexOf(max2);
            for (int i = 0; i < datas2.size(); i++) {
                if (datas2.get(i) == max2 && i != index2 && i != index4){
                    index3 = i;
                    break;
                }
            }
            List<Integer> index = new ArrayList<>();
            index.add(index1);
            index.add(index2);
            index.add(index3);
            index.add(index4);
            Collections.sort(index);
            index1 = index.get(0);
            index2 = index.get(1);
            index3 = index.get(2);
            index4 = index.get(3);
            System.out.print("下标: ");
            System.out.print(index1 + " ");
            System.out.print(index2 + " ");
            System.out.print(index3 + " ");
            System.out.println(index4 + " ");
            for (int i = 0; i <= index1; i++) {
                set1.add(citys.get(i));
            }
            cityNums.add(set1);
            for (int i = index1+1; i <= index2; i++) {
                set2.add(citys.get(i));
            }
            cityNums.add(set2);
            for (int i = index2+1; i <= index3; i++) {
                set3.add(citys.get(i));
            }
            cityNums.add(set3);
            for (int i = index3+1; i <= index4; i++) {
                set4.add(citys.get(i));
            }
            cityNums.add(set4);
            for (int i = index4+1; i < citys.size(); i++) {
                set5.add(citys.get(i));
            }
            cityNums.add(set5);
            return cityNums;
        }
        else if (data2Set.size() == 3 && sameMin <= 13 && sameMax == 2){
            System.out.println("执行了方法5.4");
            Collections.sort(datas2Copy);
            max1 = datas2Copy.get(datas2Copy.size()-1);
            max2 = datas2Copy.get(datas2Copy.size()-3);
            index1 = datas2.indexOf(max1);
            index2 = datas2.lastIndexOf(max1);
            index3 = datas2.indexOf(max2);
            index4 = datas2.lastIndexOf(max2);
            List<Integer> index = new ArrayList<>();
            index.add(index1);
            index.add(index2);
            index.add(index3);
            index.add(index4);
            Collections.sort(index);
            index1 = index.get(0);
            index2 = index.get(1);
            index3 = index.get(2);
            index4 = index.get(3);
            System.out.print("下标: ");
            System.out.print(index1 + " ");
            System.out.print(index2 + " ");
            System.out.print(index3 + " ");
            System.out.println(index4 + " ");
            for (int i = 0; i <= index1; i++) {
                set1.add(citys.get(i));
            }
            cityNums.add(set1);
            for (int i = index1+1; i <= index2; i++) {
                set2.add(citys.get(i));
            }
            cityNums.add(set2);
            for (int i = index2+1; i <= index3; i++) {
                set3.add(citys.get(i));
            }
            cityNums.add(set3);
            for (int i = index3+1; i <= index4; i++) {
                set4.add(citys.get(i));
            }
            cityNums.add(set4);
            for (int i = index4+1; i < citys.size(); i++) {
                set5.add(citys.get(i));
            }
            cityNums.add(set5);
            return cityNums;
        }
        else if (data2Set.size() == 3 && sameMin <= 13 && sameMax == 3){
            System.out.println("执行了方法5.5");
            Collections.sort(datas2Copy);
            max1 = datas2Copy.get(datas2Copy.size()-1);
            max2 = datas2Copy.get(datas2Copy.size()-4);
            index1 = datas2.indexOf(max1);
            index2 = datas2.lastIndexOf(max1);
            for (int i = 0; i < datas2.size(); i++) {
                if (datas2.get(i) == max1 && i != index1 && i != index2){
                    index3 = i;
                    break;
                }
            }
            index4 = datas2.indexOf(max2);
            List<Integer> index = new ArrayList<>();
            index.add(index1);
            index.add(index2);
            index.add(index3);
            index.add(index4);
            Collections.sort(index);
            index1 = index.get(0);
            index2 = index.get(1);
            index3 = index.get(2);
            index4 = index.get(3);
            System.out.print("下标: ");
            System.out.print(index1 + " ");
            System.out.print(index2 + " ");
            System.out.print(index3 + " ");
            System.out.println(index4 + " ");
            for (int i = 0; i <= index1; i++) {
                set1.add(citys.get(i));
            }
            cityNums.add(set1);
            for (int i = index1+1; i <= index2; i++) {
                set2.add(citys.get(i));
            }
            cityNums.add(set2);
            for (int i = index2+1; i <= index3; i++) {
                set3.add(citys.get(i));
            }
            cityNums.add(set3);
            for (int i = index3+1; i <= index4; i++) {
                set4.add(citys.get(i));
            }
            cityNums.add(set4);
            for (int i = index4+1; i < citys.size(); i++) {
                set5.add(citys.get(i));
            }
            cityNums.add(set5);
            return cityNums;
        }
        else if (data2Set.size() == 4 && sameMin <= 13 && sameMax == 1){
            System.out.println("执行了方法5.6");
            Collections.sort(datas2Copy);
            max1 = datas2Copy.get(datas2Copy.size()-1);
            max2 = datas2Copy.get(datas2Copy.size()-2);
            max3 = datas2Copy.get(datas2Copy.size()-3);
            max4 = datas2Copy.get(datas2Copy.size()-4);
            index1 = datas2.indexOf(max1);
            if (max2 == max3){
                index2 = datas2.indexOf(max2);
                index3 = datas2.lastIndexOf(max2);
                index4 = datas2.indexOf(max4);
            }else if (max3 == max4){
                index2 = datas2.indexOf(max2);
                index3 = datas2.indexOf(max3);
                index4 = datas2.lastIndexOf(max3);
            }
            List<Integer> index = new ArrayList<>();
            index.add(index1);
            index.add(index2);
            index.add(index3);
            index.add(index4);
            Collections.sort(index);
            index1 = index.get(0);
            index2 = index.get(1);
            index3 = index.get(2);
            index4 = index.get(3);
            System.out.print("下标: ");
            System.out.print(index1 + " ");
            System.out.print(index2 + " ");
            System.out.print(index3 + " ");
            System.out.println(index4 + " ");
            for (int i = 0; i <= index1; i++) {
                set1.add(citys.get(i));
            }
            cityNums.add(set1);
            for (int i = index1+1; i <= index2; i++) {
                set2.add(citys.get(i));
            }
            cityNums.add(set2);
            for (int i = index2+1; i <= index3; i++) {
                set3.add(citys.get(i));
            }
            cityNums.add(set3);
            for (int i = index3+1; i <= index4; i++) {
                set4.add(citys.get(i));
            }
            cityNums.add(set4);
            for (int i = index4+1; i < citys.size(); i++) {
                set5.add(citys.get(i));
            }
            cityNums.add(set5);
            return cityNums;
        }
        else if (data2Set.size() == 4 && sameMin <= 13 && sameMax == 2){
            System.out.println("执行了方法5.7");
            Collections.sort(datas2Copy);
            max1 = datas2Copy.get(datas2Copy.size()-1);
            max2 = datas2Copy.get(datas2Copy.size()-3);
            max3 = datas2Copy.get(datas2Copy.size()-4);
            index1 = datas2.indexOf(max1);
            index2 = datas2.lastIndexOf(max1);
            index3 = datas2.indexOf(max2);
            index4 = datas2.indexOf(max3);
            List<Integer> index = new ArrayList<>();
            index.add(index1);
            index.add(index2);
            index.add(index3);
            index.add(index4);
            Collections.sort(index);
            index1 = index.get(0);
            index2 = index.get(1);
            index3 = index.get(2);
            index4 = index.get(3);
            System.out.print("下标: ");
            System.out.print(index1 + " ");
            System.out.print(index2 + " ");
            System.out.print(index3 + " ");
            System.out.println(index4 + " ");
            for (int i = 0; i <= index1; i++) {
                set1.add(citys.get(i));
            }
            cityNums.add(set1);
            for (int i = index1+1; i <= index2; i++) {
                set2.add(citys.get(i));
            }
            cityNums.add(set2);
            for (int i = index2+1; i <= index3; i++) {
                set3.add(citys.get(i));
            }
            cityNums.add(set3);
            for (int i = index3+1; i <= index4; i++) {
                set4.add(citys.get(i));
            }
            cityNums.add(set4);
            for (int i = index4+1; i < citys.size(); i++) {
                set5.add(citys.get(i));
            }
            cityNums.add(set5);
            return cityNums;
        }
        return cityNums;
    }
}