package com.tgroup.controller;

import com.tgroup.domain.Permission;
import com.tgroup.domain.Result;
import com.tgroup.domain.ResultType;
import com.tgroup.pojo.UploadFile;
import com.tgroup.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author yqf
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private UploadFileService uploadFileService;

    @GetMapping
    public ResponseEntity<Result> getAllFile(){
        List<UploadFile> allUploadFile = uploadFileService.getAllFile();

        if(allUploadFile ==null){
            return new ResponseEntity<>(new Result(ResultType.EmptyData), HttpStatus.OK);
        }

        return new ResponseEntity<>(new Result(ResultType.Success, allUploadFile),HttpStatus.OK);
    }

    /**
     * 单文件上传
     * */
    @PutMapping
    public ResponseEntity<Result> uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request){

        if(!Permission.checkGeneralAdmin(request)){
            return new ResponseEntity<>(new Result(ResultType.NotPermission),HttpStatus.OK);
        }

        System.out.println(file);
        boolean res = uploadFileService.storeFile(file);
        if (!res){
            return new ResponseEntity<>(new Result(ResultType.UploadFileFail), HttpStatus.OK);
        }

        return new ResponseEntity<>(new Result(ResultType.UploadFileSuccess), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteFile(@PathVariable Integer id,HttpServletRequest request){

        if(!Permission.checkGeneralAdmin(request)){
            return new ResponseEntity<>(new Result(ResultType.NotPermission),HttpStatus.OK);
        }

        boolean status = uploadFileService.deleteFile(id);

        if (!status){
            return new ResponseEntity<>(new Result(ResultType.DeleteFileFail),HttpStatus.OK);
        }

        return new ResponseEntity<>(new Result(ResultType.DeleteFileSuccess),HttpStatus.OK);
    }
}
