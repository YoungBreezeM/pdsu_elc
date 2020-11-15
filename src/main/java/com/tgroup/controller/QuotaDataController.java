package com.tgroup.controller;

import com.tgroup.domain.Result;
import com.tgroup.domain.ResultType;
import com.tgroup.pojo.City;
import com.tgroup.pojo.QuotaData;
import com.tgroup.service.CityService;
import com.tgroup.service.QuotaDataService;
import com.tgroup.utils.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author yqf
 */
@RestController
@RequestMapping("/quotaData")
public class QuotaDataController {
    @Autowired
    private QuotaDataService quotaDataService;
    @Autowired
    private CityService cityService;

    @GetMapping("/{timeId}/{indexId}")
    public ResponseEntity<Result> getCityValueByTidAndIid(@PathVariable Integer timeId, @PathVariable Integer indexId){
        QuotaData quotaData = new QuotaData();
        quotaData.setTimeId(timeId);
        quotaData.setIndexId(indexId);

        List<QuotaData> allCityValueByTidAndIid = quotaDataService.getAllCityValueByTidAndIid(quotaData);
        if(allCityValueByTidAndIid==null){
            return new ResponseEntity<>(new Result(ResultType.EmptyData), HttpStatus.OK);
        }

        List<HashMap<String, Object>> resJson = new ArrayList<>();

        for (QuotaData data : allCityValueByTidAndIid) {
            HashMap<String, Object> item = new HashMap<>(10);
            City city = cityService.findOneById(data.getCityId());

            if (city!=null){
                item.put("cityName",city.getCityName());
                item.put("value", FormatUtil.toDFormat(data.getValue()));
                resJson.add(item);
            }
        }

        return new ResponseEntity<>(new Result(ResultType.Success,resJson), HttpStatus.OK);
    }

    @GetMapping("/{indexId}")
    public ResponseEntity<Result>  getAllTimeAndCityByIndexId(@PathVariable Integer indexId){
        List<City> allTimeAndCityByIndexId = quotaDataService.getAllTimeAndCityByIndexId(indexId);

        if(allTimeAndCityByIndexId==null){
            return new ResponseEntity<>(new Result(ResultType.EmptyData), HttpStatus.OK);
        }

        return new ResponseEntity<>(new Result(ResultType.Success,allTimeAndCityByIndexId),HttpStatus.OK);
    }

    @GetMapping("/category/{timeId}/{indexId}")
    public ResponseEntity<Result> getAllCategory(@PathVariable Integer timeId, @PathVariable Integer indexId){
        List<Set<String>> allCategory = quotaDataService.getAllCategory(timeId, indexId);
        System.out.println(allCategory);
        Map<String,Object> reJson;
        List<Map<String,Object>> resList = new ArrayList<>();
        if(allCategory!=null){
            int index = 1;
            for (Set<String> li : allCategory) {

                for (String s : li) {
                    reJson = new HashMap<>(0);
                    reJson.put("cityName",s);
                    reJson.put("category",index);
                    resList.add(reJson);
                }

                index++;
            }

            return new ResponseEntity<>(new Result(ResultType.Success,resList),HttpStatus.OK);
        }

        return new ResponseEntity<>(new Result(ResultType.EmptyData),HttpStatus.OK);
    }

}
