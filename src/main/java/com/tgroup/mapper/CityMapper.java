package com.tgroup.mapper;

import com.tgroup.pojo.City;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yqf
 */
@Mapper
@Repository
public interface CityMapper {
    /**
     * find all city
     * @return list about city
     * */
    @Select("select * from tb_city")
    List<City> findAll();

    /**
     * find city  by id
     * @param id city is
     * @return city
     * */
    @Select("select * from tb_city where id =#{id}")
    City findOneById(@Param("id") Integer id);

    /**
     * find city by cityName
     * @param cityName
     * @return list
     * */
    @Select("select * from tb_city where cityName =#{cityName}")
    List<City> findOneByCityName(@Param("cityName") String cityName);


    /**
     * add city
     * @param city entity
     * */
    @Insert("insert into tb_city(cityName) values(#{cityName})")
    void addRecord(City city);

    /**
     * delete city
     * @param id city id
     * */
    @Delete("delete from tb_city where id =#{id}")
    void deleteRecord(@Param("id") Integer id);

    /**
     * update city
     * @param city entity
     * */
    @Update("update tb_city set cityName=#{cityName} where id =#{id}")
    void updateRecord(City city);
}
