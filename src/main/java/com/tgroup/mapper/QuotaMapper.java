package com.tgroup.mapper;

import com.tgroup.pojo.Quota;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yqf
 */
@Mapper
@Repository
public interface QuotaMapper {
    /**
     * find all quota
     * @return list about quota
     * */
    @Select("select * from tb_quota")
    List<Quota> findAll();

    /**
     * find quota  by id
     * @param id quota is
     * @return quota
     * */
    @Select("select * from tb_quota where id =#{id}")
    Quota findOneById(@Param("id") Integer id);

    /**
     * find quota by indexName
     * @param indexName
     * @return list
     * */
    @Select("select * from tb_quota where indexName =#{indexName}")
    List<Quota> findAllByIndexName(@Param("indexName") String indexName);

    /**
     * find quota by indexName
     * @param indexName
     * @return list
     * */
    @Select("select * from tb_quota where indexName =#{indexName}")
    Quota findOneByIndexName(@Param("indexName") String indexName);

    /**
     * find quota by module id
     * @param moduleId int
     * @return list
     * */
    @Select("select * from tb_quota where moduleId =#{moduleId}")
    List<Quota> findAllByModuleId(@Param("moduleId") Integer moduleId);


    /**
     * add quota
     * @param quota entity
     * */
    @Insert("insert into tb_quota(indexName,department,weight,circle,unit,moduleId,allScore) values(#{indexName},#{department},#{weight},#{circle},#{unit},#{moduleId},#{allScore})")
    void addRecord(Quota quota);

    /**
     * delete quota
     * @param id quota id
     * */
    @Delete("delete from tb_quota where id =#{id}")
    void deleteRecord(@Param("id") Integer id);

    /**
     * update quota
     * @param quota entity
     * */
    @Update("update tb_quota set indexName=#{indexName},department=#{department},weight=#{weight},circle=#{circle},unit=#{unit},moduleId=#{moduleId},allScore=#{allScore} where id =#{id}")
    void updateRecord(Quota quota);
}
