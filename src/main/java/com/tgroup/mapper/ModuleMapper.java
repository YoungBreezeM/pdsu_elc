package com.tgroup.mapper;

import com.tgroup.pojo.Module;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yqf
 */
@Mapper
@Repository
public interface ModuleMapper {
    /**
     * find all module
     * @return list about module
     * */
    @Select("select * from tb_module")
    List<Module> findAll();

    /**
     * find all module and get quotas
     * @return list about module
     * */
    @Results(id = "moduleMap",value = {
            @Result(column = "id",property = "id"),
            @Result(column = "id",property = "quotas",many = @Many(select = "com.tgroup.mapper.QuotaMapper.findAllByModuleId")),
    })
    @Select("select * from tb_module")
    List<Module> findAllToQuotas();

    /**
     * find module  by id
     * @param id module is
     * @return module
     * */
    @Select("select * from tb_module where id =#{id}")
    Module findOneById(@Param("id") Integer id);

    /**
     * find module  by moduleName
     * @param moduleName  is
     * @return module
     * */
    @Select("select * from tb_module where moduleName =#{moduleName}")
    List<Module> findAllByName(@Param("moduleName") String moduleName);

    /**
     * find module  by id to quotas
     * @param id module is
     * @return module
     * */
    @ResultMap("moduleMap")
    @Select("select * from tb_module where id =#{id}")
    Module findOneByIdToQuotas(@Param("id") Integer id);


    /**
     * add module
     * @param module entity
     * */
    @Insert("insert into tb_module(moduleName,allScore,weight) values(#{moduleName},#{allScore},#{weight})")
    void addRecord(Module module);

    /**
     * delete module
     * @param id module id
     * */
    @Delete("delete from tb_module where id =#{id}")
    void deleteRecord(@Param("id") Integer id);

    /**
     * update module
     * @param module entity
     * */
    @Update("update tb_module set moduleName=#{moduleName},allScore=#{allScore},weight=#{weight} where id =#{id}")
    void updateRecord(Module module);
}
