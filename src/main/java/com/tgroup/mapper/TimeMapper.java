package com.tgroup.mapper;

import com.tgroup.pojo.Time;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yqf
 */
@Mapper
@Repository
public interface TimeMapper {
    /**
     * find all time
     * @return list about time
     * */
    @Select("select * from tb_time")
    List<Time> findAll();

    /**
     * find time  by id
     * @param id time is
     * @return time
     * */
    @Select("select * from tb_time where id =#{id}")
    Time findOneById(@Param("id") Integer id);

    /**
     * find time by time
     * @param time
     * @return list
     * */
    @Select("select * from tb_time where time = #{time}")
    List<Time> findAllByTime(Time time);

    /**
     * find time by time
     * @param timeName
     * @return list
     * */
    @Select("select * from tb_time where time = #{time}")
    Time findOneByTime(@Param("time") String timeName);


    /**
     * add time
     * @param time entity
     * */
    @Insert("insert into tb_time(time) values(#{time})")
    void addRecord(Time time);

    /**
     * delete time
     * @param id time id
     * */
    @Delete("delete from tb_time where id =#{id}")
    void deleteRecord(@Param("id") Integer id);

    /**
     * update time
     * @param time entity
     * */
    @Update("update tb_time set time=#{time} where id =#{id}")
    void updateRecord(Time time);
}
