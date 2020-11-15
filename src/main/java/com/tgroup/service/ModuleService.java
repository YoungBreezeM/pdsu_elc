package com.tgroup.service;

import com.tgroup.pojo.Module;
import com.tgroup.pojo.Quota;

import java.util.List;

/**
 * @author yqf
 */
public interface ModuleService {

    /**
     * get all Model
     * @return list
     * */
    List<Module> getAllModule();

    /**
     * get all module quotas
     * @return list
     * @param timeId int
     * @param cityId int
     * */
    List<Module> getAllModuleScore(Integer timeId,Integer cityId);

    /**
     * get one module quotas by id
     * @param moduleId int
     * @param timeId int
     * @param cityId int
     * @return module
     * */
    List<Quota> getOneModuleScore(Integer moduleId, Integer timeId, Integer cityId);

    /**
     * update module
     * @param module
     * @return b
     * */
    Boolean updateModule(Module module);

    /**
     * add module
     * @param module
     * @return b
     * */
    Boolean addModule(Module module);


    /**
     * delete module
     * @param id
     * @return b
     * */
    Boolean deleteModule(Integer id);

}
