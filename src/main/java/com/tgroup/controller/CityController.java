package com.tgroup.controller;

import com.tgroup.domain.Result;
import com.tgroup.domain.ResultType;
import com.tgroup.pojo.City;
import com.tgroup.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yqf
 */
@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<Result> getAllCity(){
        List<City> allCity = cityService.getAllCity();

        if(allCity==null){
            return new ResponseEntity<>(new Result(ResultType.EmptyData), HttpStatus.OK);
        }

        return new ResponseEntity<>(new Result(ResultType.Success,allCity),HttpStatus.OK);
    }
}
