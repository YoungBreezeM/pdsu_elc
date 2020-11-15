package com.tgroup.service.impl;

import com.tgroup.mapper.ModuleMapper;
import com.tgroup.mapper.QuotaDataMapper;
import com.tgroup.pojo.Module;
import com.tgroup.pojo.Quota;
import com.tgroup.pojo.QuotaData;
import com.tgroup.service.ModuleService;
import com.tgroup.utils.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;


/**
 * @author yqf
 */
@Service
public class ModelServiceImpl implements ModuleService {

    @Autowired
    private ModuleMapper moduleMapper;
    @Autowired
    private QuotaDataMapper quotaDataMapper;

    @Override
    public List<Module> getAllModule() {
        return moduleMapper.findAll();
    }

    @Override
    public List<Module> getAllModuleScore(Integer timeId, Integer cityId) {
        List<Module> allToQuotas = moduleMapper.findAllToQuotas();

        if (allToQuotas != null) {

            QuotaData quotaData = new QuotaData();
            quotaData.setTimeId(timeId);
            quotaData.setCityId(cityId);

            for (Module allToQuota : allToQuotas) {
                List<Quota> quotas = allToQuota.getQuotas();
                allToQuota.setValue(0d);
                for (Quota quota : quotas) {
                    quotaData.setIndexId(quota.getId());
                    QuotaData oneByTidAndIidAndCid = quotaDataMapper.findOneByTidAndIidAndCid(quotaData);
                    if (oneByTidAndIidAndCid != null) {
                        allToQuota.setValue(allToQuota.getValue() + oneByTidAndIidAndCid.getValue());
                    }
                }
                Double format;
                if(allToQuota.getAllScore()!=0&&allToQuota.getWeight()!=0){
                     format = FormatUtil.toDFormat(
                            allToQuota.getValue() / allToQuota.getAllScore() * allToQuota.getWeight()
                    );
                }else {
                    format = FormatUtil.toDFormat(0d);
                }
                allToQuota.setValue(format);
                allToQuota.setQuotas(null);

            }
            return allToQuotas;
        }

        return null;
    }

    @Override
    public List<Quota> getOneModuleScore(Integer moduleId, Integer timeId, Integer cityId) {
        Module module = moduleMapper.findOneByIdToQuotas(moduleId);

        if (module != null) {
            List<Quota> quotas = module.getQuotas();
            QuotaData quotaData = new QuotaData();
            quotaData.setTimeId(timeId);
            quotaData.setCityId(cityId);


            List<Quota> rsList = new ArrayList<>();
            for (Quota quota : quotas) {
                quotaData.setIndexId(quota.getId());

                QuotaData oneByTidAndIidAndCid = quotaDataMapper.findOneByTidAndIidAndCid(quotaData);

                if (oneByTidAndIidAndCid != null) {
                    Double format;
                    if(quota.getAllScore()!=null){
                        format = FormatUtil.toDFormat(
                            oneByTidAndIidAndCid.getValue() / quota.getAllScore() * quota.getWeight()
                        );
                    }else {
                        format = FormatUtil.toDFormat(0d);
                    }

                    quota.setValue(format);
                    rsList.add(quota);
                }

            }


            return rsList;
        }

        return null;
    }

    @Override
    public Boolean updateModule(Module module) {
        Module oneById = moduleMapper.findOneById(module.getId());
        if (oneById!=null){
            moduleMapper.updateRecord(module);
            return true;
        }
        return false;
    }

    @Override
    public Boolean addModule(Module module) {
        List<Module> allByName = moduleMapper.findAllByName(module.getModuleName());

        if(allByName.size()==0){
            moduleMapper.addRecord(module);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteModule(Integer id) {

        Module oneById = moduleMapper.findOneById(id);

        if(oneById!=null){
            moduleMapper.deleteRecord(id);
            return true;
        }
        return false;
    }
}
