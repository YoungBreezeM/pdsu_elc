package com.tgroup.service;

import com.tgroup.pojo.City;
import com.tgroup.pojo.Quota;
import com.tgroup.pojo.QuotaData;
import com.tgroup.pojo.Time;

import java.util.List;
import java.util.Set;

/**
 * @author yqf
 */
public interface QuotaDataService {

    /**
     * get all city value by tid and ild;
     * @param quotaData entity
     * @return list
     * */
    List<QuotaData> getAllCityValueByTidAndIid(QuotaData quotaData);

    /**
     * get all time and city quotas
     * @param indexId int
     * @return list
     * */
    List<City> getAllTimeAndCityByIndexId(Integer indexId);

    /**
     * add quotaData
     * @param quotaData
     * @return b
     * */
    boolean addQuotaData(QuotaData quotaData);


    /**
     *
     * 这个模块是功能实按照每个指标的数值大小，然后排序，
     * 每两个地市指标之间的差值，找到最大的五个差值，
     * 把18个地市分成五类；传入的数据是，
     * 某个季度时间下的一个指标的全部城市的数据值的集合。
     * （就是柱状图的数据）；他写的算法传入的集合对象只需城市名称和数据即可；
     * @param timeId e
     * @param indexId e
     * */
    List<Set<String>> getAllCategory(Integer timeId,Integer indexId);
}
