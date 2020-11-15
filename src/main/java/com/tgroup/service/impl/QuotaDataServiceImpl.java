package com.tgroup.service.impl;

import com.tgroup.domain.CityValue;
import com.tgroup.mapper.CityMapper;
import com.tgroup.mapper.QuotaDataMapper;
import com.tgroup.mapper.TimeMapper;
import com.tgroup.pojo.City;
import com.tgroup.pojo.QuotaData;
import com.tgroup.pojo.Time;
import com.tgroup.service.QuotaDataService;
import com.tgroup.utils.FormatUtil;
import com.tgroup.utils.WuLei;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author yqf
 */
@Service
public class QuotaDataServiceImpl implements QuotaDataService {

    @Autowired
    private QuotaDataMapper quotaDataMapper;
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private TimeMapper timeMapper;


    @Override
    public List<QuotaData> getAllCityValueByTidAndIid(QuotaData quotaData) {

        List<City> cities = cityMapper.findAll();
        if(cities.size()==0){
            return null;
        }

        List<QuotaData> allCityScore = new ArrayList<>();

        for (City city : cities) {
            quotaData.setCityId(city.getId());
            QuotaData allCityValueByTidAndIid = quotaDataMapper.findOneByTidAndIidAndCid(quotaData);

            if(allCityValueByTidAndIid!=null){
                allCityScore.add(allCityValueByTidAndIid);
            }
        }

        return allCityScore;
    }

    @Override
    public List<City> getAllTimeAndCityByIndexId(Integer indexId) {
        List<Time> times = timeMapper.findAll();
        List<City> cities = cityMapper.findAll();
        QuotaData quotaData = new QuotaData();
        quotaData.setIndexId(indexId);
        if(times!=null&&cities!=null){
            for (City city : cities) {
                quotaData.setCityId(city.getId());
                List<QuotaData> quotaDataList = new ArrayList<>();
                for (Time time : times) {
                    quotaData.setTimeId(time.getId());
                    QuotaData oneByTidAndIidAndCid = quotaDataMapper.findOneByTidAndIidAndCid(quotaData);
                    if(oneByTidAndIidAndCid==null){
                        QuotaData data = new QuotaData();
                        data.setValue(0d);
                        data.setTimeId(time.getId());
                        quotaDataList.add(data);
                    }else {
                        Double value = oneByTidAndIidAndCid.getValue();
                        Double format = FormatUtil.toDFormat(value);
                        oneByTidAndIidAndCid.setValue(format);
                        quotaDataList.add(oneByTidAndIidAndCid);
                    }

                }
                city.setQuotaDataList(quotaDataList);
            }

            return cities;
        }
        return null;
    }

    @Override
    public boolean addQuotaData(QuotaData quotaData) {

        List<QuotaData> allByTidAndIidAndCid = quotaDataMapper.findAllByTidAndIidAndCid(quotaData);

        if(allByTidAndIidAndCid.size()==0){
            quotaDataMapper.addRecord(quotaData);
            return true;
        }

        return false;
    }

    @Override
    public List<Set<String>> getAllCategory(Integer timeId, Integer indexId) {
        QuotaData quotaData = new QuotaData();
        quotaData.setTimeId(timeId);
        quotaData.setIndexId(indexId);
        List<QuotaData> allCityValueByTidAndIid = this.getAllCityValueByTidAndIid(quotaData);
        List<CityValue> cityValues = new ArrayList<>();

        if(allCityValueByTidAndIid.size()>0){
            for (QuotaData data : allCityValueByTidAndIid) {
                String cityName = cityMapper.findOneById(data.getCityId()).getCityName();
                cityValues.add(new CityValue(cityName,data.getValue()));

            }

            List<Set<String>> sets = WuLei.five5(cityValues);

            if(sets.size()>0){
                return sets;
            }
        }

        return null;
    }
}
