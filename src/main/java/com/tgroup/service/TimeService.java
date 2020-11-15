package com.tgroup.service;

import com.tgroup.pojo.Time;

import java.util.List;

/**
 * @author yqf
 */
public interface TimeService {

    /**
     * get all time
     * @return list time
     * */
    List<Time> getAllTime();

    /**
     * add time
     * @param time
     * @return b
     * */
    boolean addTime(Time time);


    /**
     * find one by timeName
     * @param timeName
     * @return time
     * */
    Time findOneByName(String timeName);
}
