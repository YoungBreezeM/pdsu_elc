package com.tgroup.service;

import com.tgroup.pojo.Quota;

import java.util.List;

/**
 * @author yqf
 */
public interface QuotaService {
    /**
     * get all quotaService
     * @return list quota
     * */
    List<Quota> getAllQuota();

    /**
     * update quotas
     * @param quota
     * @return b
     * */
    Boolean updateQuota(Quota quota);

    /**
     * add quota
     * @param quota
     * @return b
     * */
    Boolean addQuota(Quota quota);

    /**
     * find quota by name
     * @param quotaName
     * @return quota
     * */
    Quota findOneByName(String quotaName);

    /**
     * delete quota
     * @param id
     * @return b
     * */
    Boolean deleteQuota(Integer id);
}
