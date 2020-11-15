package com.tgroup.service;

import com.tgroup.pojo.City;

import java.util.List;

/**
 * @author yqf
 */
public interface CityService {

    /**
     * get all city
     * @return get all City
     * */
    List<City> getAllCity();

    /**
     * find one by id
     * @param id s
     * @return city
     * */
    City findOneById(Integer id);

    /**
     * add city record
     * @param city
     * */
    void addRecord(City city);
}
