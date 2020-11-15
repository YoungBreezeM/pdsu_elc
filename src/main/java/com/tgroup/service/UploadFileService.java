package com.tgroup.service;

import com.tgroup.pojo.UploadFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author yqf
 */

public interface UploadFileService {

    /**
     * get all file
     * @return list
     * */
     List<UploadFile> getAllFile();

    /**
     * upload file
     * @param file
     * @return file
     * */
    boolean storeFile(MultipartFile file);

    /**
     * delete file
     * @param id file id
     * @return status
     * **/
    boolean deleteFile(Integer id);
}
