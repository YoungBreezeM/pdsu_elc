package com.tgroup.service.impl;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.tgroup.domain.Base;
import com.tgroup.domain.FileProperties;
import com.tgroup.exception.FileException;
import com.tgroup.mapper.FileMapper;
import com.tgroup.pojo.UploadFile;
import com.tgroup.service.*;
import com.tgroup.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author yqf
 */
@Service
public class UploadFileServiceImpl implements UploadFileService {

    @Autowired
    private FileMapper fileMapper;
    @Autowired
    FileProperties fileProperties;
    @Autowired
    private QuotaDataService quotaDataService;
    @Autowired
    private CityService cityService;
    @Autowired
    private TimeService timeService;
    @Autowired
    private QuotaService quotaService;


    private final Path fileStorageLocation;

    @Autowired
    public UploadFileServiceImpl(FileProperties fileProperties) throws FileNotFoundException {


        this.fileStorageLocation = Paths.get(
                fileProperties.getUploadDir()
        );

        try {
            //如果文件目录不存在就创建目录
            Files.createDirectories(this.fileStorageLocation);

        } catch (Exception ex) {
            throw new FileException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public List<UploadFile> getAllFile() {

        return fileMapper.findAll();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean storeFile(MultipartFile file) {
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        String[] split = originalFileName.split("\\.");
        DateUtil dateUtil = new DateUtil();
        String fileName =split[0]+"_"+dateUtil.createTime("yyyy-MM-dd_HH-mm-ss")+"."+split[1];
        String fileAccessUrl = "/uploads/"+fileName;

        UploadFile uploadFile = new UploadFile(
                fileAccessUrl,
                fileName,
                dateUtil.createTime()
        );

        // 检查文件路径是否正确
        if(fileName.contains("..")) {
            throw new FileException("Sorry! Filename contains invalid path sequence " + fileName);
        }

        //解析本地文件路径
        //Path targetLocation = this.fileStorageLocation.resolve(fileName);
        //解析文件
        //InputStream inputStream = file.getInputStream();
        ExcelReader excelReader = null;
        try (InputStream in = file.getInputStream()) {
            excelReader = EasyExcel.read(in, Base.class, new ReadExcelModelListener(cityService,quotaService,timeService,quotaDataService)).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
        } catch (IOException ex) {
            System.out.println(ex);
        } finally {
            // 这里一定别忘记关闭，读的时候会创建临时文件，到时磁盘会崩
            if (excelReader != null) {
                excelReader.finish();
            }
        }
        //将图片拷贝到指定文件夹下
        //Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        //记录文件信息
        fileMapper.addRecord(uploadFile);

        return true;

    }

    /**
     * 删除文件
     * */
    @Override
    public boolean deleteFile(Integer id){
        fileMapper.deleteRecord(id);

        return true;

    }
}
