package com.tgroup.service.impl;

import com.tgroup.mapper.CityMapper;
import com.tgroup.pojo.City;
import com.tgroup.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yqf
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityMapper cityMapper;

    @Override
    public List<City> getAllCity() {
        List<City> cities = cityMapper.findAll();

        if(cities.size()==0){
            return null;
        }
        return cities;
    }

    @Override
    public City findOneById(Integer id) {

        return cityMapper.findOneById(id);
    }

    @Override
    public void addRecord(City city) {
        List<City> oneByCityName = cityMapper.findOneByCityName(city.getCityName());

        if(oneByCityName.size()==0){
            cityMapper.addRecord(city);
        }
    }
}
