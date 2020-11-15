package com.tgroup.controller;

import com.tgroup.domain.Result;
import com.tgroup.domain.ResultType;
import com.tgroup.pojo.Time;
import com.tgroup.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

/**
 * @author yqf
 */
@RestController
@RequestMapping("/time")
public class TimeController {

    @Autowired
    private TimeService timeService;

    @GetMapping
    public ResponseEntity<Result> getAllTime(){
        List<Time> allTime = timeService.getAllTime();

        return new ResponseEntity<>(new Result(ResultType.Success,allTime),HttpStatus.OK);
    }
}
