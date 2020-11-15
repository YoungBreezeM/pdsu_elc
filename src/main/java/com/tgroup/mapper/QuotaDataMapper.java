package com.tgroup.mapper;

import com.tgroup.pojo.QuotaData;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yqf
 */
@Mapper
@Repository
public interface QuotaDataMapper {
    /**
     * find all quotaData
     * @return list about quotaData
     * */
    @Select("select * from tb_quota_data")
    List<QuotaData> findAll();

    /**
     * find quotaData  by id
     * @param id quotaData is
     * @return quotaData
     * */
    @Select("select * from tb_quota_data where id =#{id}")
    QuotaData findOneById(@Param("id") Integer id);

    /**
     * find all  by timeId and indexId ,cityId
     * @return list
     * @param quotaData
     * */
    @Select("select * from tb_quota_data where cityId=#{cityId} and timeId=#{timeId} and indexId=#{indexId}")
    QuotaData findOneByTidAndIidAndCid(QuotaData quotaData);

    /**
     * find all  by timeId and indexId ,cityId
     * @return list
     * @param quotaData
     * */
    @Select("select * from tb_quota_data where cityId=#{cityId} and timeId=#{timeId} and indexId=#{indexId}")
    List<QuotaData> findAllByTidAndIidAndCid(QuotaData quotaData);




    /**
     * add quotaData
     * @param quotaData entity
     * */
    @Insert("insert into tb_quota_data(value,cityId,timeId,indexId,uploadDate) values(#{value},#{cityId},#{timeId},#{indexId},#{uploadDate})")
    void addRecord(QuotaData quotaData);

    /**
     * delete quotaData
     * @param id quotaData id
     * */
    @Delete("delete from tb_quota_data where id =#{id}")
    void deleteRecord(@Param("id") Integer id);

    /**
     * update quotaData
     * @param quotaData entity
     * */
    @Update("update tb_quota_data set value=#{value},cityId=#{cityId},timeId=#{timeId},indexId=#{indexId},uploadDate=#{uploadDate} where id =#{id}")
    void updateRecord(QuotaData quotaData);
}
