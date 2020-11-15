package com.tgroup.service.impl;

import com.tgroup.mapper.TimeMapper;
import com.tgroup.pojo.Time;
import com.tgroup.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yqf
 */
@Service
public class TimeServiceImpl implements TimeService {

    @Autowired
    private TimeMapper timeMapper;

    @Override
    public List<Time> getAllTime() {
        List<Time> times = timeMapper.findAll();

        if(times.size()==0){
            return null;
        }

        return times;
    }

    @Override
    public boolean addTime(Time time) {
        List<Time> allByTime = timeMapper.findAllByTime(time);

        if(allByTime.size()==0){
            timeMapper.addRecord(time);
        }
        return false;
    }

    @Override
    public Time findOneByName(String timeName) {
        return timeMapper.findOneByTime(timeName);
    }
}
