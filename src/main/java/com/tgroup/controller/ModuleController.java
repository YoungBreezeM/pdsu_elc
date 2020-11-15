package com.tgroup.controller;

import com.sun.org.apache.regexp.internal.RE;
import com.tgroup.domain.Permission;
import com.tgroup.domain.Result;
import com.tgroup.domain.ResultType;
import com.tgroup.pojo.City;
import com.tgroup.pojo.Module;
import com.tgroup.pojo.Quota;
import com.tgroup.service.CityService;
import com.tgroup.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yqf
 */
@RestController
@RequestMapping("/module")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;
    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<Result> getAllModule(){
        List<Module> allModule = moduleService.getAllModule();

        if(allModule==null){
            return new ResponseEntity<>(new Result(ResultType.EmptyData), HttpStatus.OK);
        }

        return new ResponseEntity<>(new Result(ResultType.Success,allModule),HttpStatus.OK);
    }

    @GetMapping("/moduleScore/{timeId}/{cityId}")
    public ResponseEntity<Result> getModuleScore(@PathVariable Integer timeId, @PathVariable Integer cityId){
        List<Module> allModule = moduleService.getAllModuleScore(timeId,cityId);

        if(allModule==null){
            return new ResponseEntity<>(new Result(ResultType.EmptyData),HttpStatus.OK);
        }

        return new ResponseEntity<>(new Result(ResultType.Success,allModule),HttpStatus.OK);
    }

    @GetMapping("/allCityModule/{timeId}")
    public ResponseEntity<Result> getAllCityModule(@PathVariable Integer timeId){
        List<City> allCity = cityService.getAllCity();
        List<Map> rsJson = new ArrayList<>();
        if(allCity!=null){
            for (City city : allCity) {
                Map<String, Object> objectObjectMap = new HashMap<>(10);
                List<Module> allModule = moduleService.getAllModuleScore(timeId,city.getId());
                List<Double> values = new ArrayList<>();;
                if(allModule!=null){
                    for (Module module : allModule) {
                        values.add(module.getValue());
                    }
                }
                objectObjectMap.put("cityName",city.getCityName());
                objectObjectMap.put("values",values);

                rsJson.add(objectObjectMap);
            }

            return new ResponseEntity<>(new Result(ResultType.Success,rsJson),HttpStatus.OK);
        }
        return new ResponseEntity<>(new Result(ResultType.EmptyData),HttpStatus.OK);
    }

    @GetMapping("/moduleOne/{moduleId}/{timeId}/{cityId}")
    public ResponseEntity<Result> getOneModuleScore(@PathVariable Integer timeId, @PathVariable Integer cityId, @PathVariable Integer moduleId){
        List<Quota> oneModuleScore = moduleService.getOneModuleScore(moduleId, timeId, cityId);

        if(oneModuleScore==null){
            return new ResponseEntity<>(new Result(ResultType.EmptyData),HttpStatus.OK);
        }

        return new ResponseEntity<>(new Result(ResultType.Success,oneModuleScore),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Result> updateModule(@RequestBody Module module, HttpServletRequest request){

        if(!Permission.checkSystemAdmin(request)){
            return new ResponseEntity<>(new Result(ResultType.NotPermission),HttpStatus.OK);
        }

        Boolean aBoolean = moduleService.updateModule(module);

        if(aBoolean){
            return new ResponseEntity<>(new Result(ResultType.UpdateSuccess),HttpStatus.OK);
        }

        return new ResponseEntity<>(new Result(ResultType.UpdateFail),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Result> addModule(@RequestBody Module module, HttpServletRequest request){
        if(!Permission.checkSystemAdmin(request)){
            return new ResponseEntity<>(new Result(ResultType.NotPermission),HttpStatus.OK);
        }


        Boolean aBoolean = moduleService.addModule(module);
        if(aBoolean){
            return new ResponseEntity<>(new Result(ResultType.AddSuccess),HttpStatus.OK);
        }

        return new ResponseEntity<>(new Result(ResultType.AddFail),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Result> deleteModule(@PathVariable Integer id,HttpServletRequest request){
        if(!Permission.checkSystemAdmin(request)){
            return new ResponseEntity<>(new Result(ResultType.NotPermission),HttpStatus.OK);
        }

        Boolean aBoolean = moduleService.deleteModule(id);

        if(aBoolean){
            return new ResponseEntity<>(new Result(ResultType.DeleteSuccess),HttpStatus.OK);
        }

        return new ResponseEntity<>(new Result(ResultType.DeleteFail),HttpStatus.OK);

    }
}
