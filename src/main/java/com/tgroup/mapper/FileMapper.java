package com.tgroup.mapper;

import com.tgroup.pojo.UploadFile;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yqf
 */
@Mapper
@Repository
public interface FileMapper {
    /**
     * find all file
     * @return list about file
     * */
    @Select("select * from tb_file")
    List<UploadFile> findAll();

    /**
     * find file  by id
     * @param id file is
     * @return file
     * */
    @Select("select * from tb_file where id =#{id}")
    UploadFile findOneById(@Param("id") Integer id);


    /**
     * add file
     * @param file entity
     * */
    @Insert("insert into tb_file(filePath,fileName,upTime) values(#{filePath},#{fileName},#{upTime})")
    void addRecord(UploadFile file);

    /**
     * delete file
     * @param id file id
     * */
    @Delete("delete from tb_file where id =#{id}")
    void deleteRecord(@Param("id") Integer id);

    /**
     * update file
     * @param file entity
     * */
    @Update("update tb_file set filePath=#{filePath},fileName=#{fileName},upTime=#{upTime} where id =#{id}")
    void updateRecord(UploadFile file);
}
