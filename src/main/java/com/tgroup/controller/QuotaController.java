package com.tgroup.controller;

import com.tgroup.domain.Permission;
import com.tgroup.domain.Result;
import com.tgroup.domain.ResultType;
import com.tgroup.pojo.Quota;
import com.tgroup.pojo.QuotaData;
import com.tgroup.service.QuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author yqf
 */
@RestController
@RequestMapping("/quota")
public class QuotaController {

    @Autowired
    private QuotaService quotaService;

    @GetMapping
    public ResponseEntity<Result> getAllQuotas(){
        List<Quota> allQuota = quotaService.getAllQuota();

        if (allQuota==null){
            return new ResponseEntity<>(new Result(ResultType.EmptyData), HttpStatus.OK);
        }

        return new ResponseEntity<>(new Result(ResultType.Success,allQuota),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Result> updateQuotas(@RequestBody Quota quota, HttpServletRequest request){



        if(!Permission.checkSystemAdmin(request)){
            return new ResponseEntity<>(new Result(ResultType.NotPermission),HttpStatus.OK);
        }

        Boolean aBoolean = quotaService.updateQuota(quota);

        if(aBoolean){
            return new ResponseEntity<>(new Result(ResultType.UpdateSuccess),HttpStatus.OK);
        }
        return new ResponseEntity<>(new Result(ResultType.UpdateFail),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Result> deleteQuotas(HttpServletRequest request, @PathVariable Integer id){



        if(!Permission.checkSystemAdmin(request)){
            return new ResponseEntity<>(new Result(ResultType.NotPermission),HttpStatus.OK);
        }

        Boolean aBoolean = quotaService.deleteQuota(id);

        if(aBoolean){
            return new ResponseEntity<>(new Result(ResultType.DeleteSuccess),HttpStatus.OK);
        }
        return new ResponseEntity<>(new Result(ResultType.DeleteFail),HttpStatus.OK);
    }


}
