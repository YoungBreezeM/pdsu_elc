package com.tgroup.service.impl;

import com.tgroup.mapper.QuotaMapper;
import com.tgroup.pojo.Quota;
import com.tgroup.service.QuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yqf
 */
@Service
public class QuotaServiceImpl implements QuotaService {

    @Autowired
    private QuotaMapper quotaMapper;

    @Override
    public List<Quota> getAllQuota() {

        List<Quota> quotas = quotaMapper.findAll();

        if (quotas.size()==0){
            return null;
        }

        return quotas;
    }

    @Override
    public Boolean updateQuota(Quota quota) {

        Quota oneById = quotaMapper.findOneById(quota.getId());

        if(oneById!=null){
            quotaMapper.updateRecord(quota);
            return true;
        }

        return false;
    }

    @Override
    public Boolean addQuota(Quota quota) {
        List<Quota> allByIndexName = quotaMapper.findAllByIndexName(quota.getIndexName());

        if(allByIndexName.size()==0){
            quotaMapper.addRecord(quota);
        }
        return null;
    }

    @Override
    public Quota findOneByName(String quotaName) {


        return quotaMapper.findOneByIndexName(quotaName);
    }

    @Override
    public Boolean deleteQuota(Integer id) {
        Quota oneById = quotaMapper.findOneById(id);

        if(oneById!=null){
            quotaMapper.deleteRecord(id);
            return true;
        }

        return false;
    }
}
