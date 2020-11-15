package com.tgroup.service.impl;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import com.tgroup.domain.Base;
import com.tgroup.domain.Position;
import com.tgroup.pojo.City;
import com.tgroup.pojo.Quota;
import com.tgroup.pojo.QuotaData;
import com.tgroup.pojo.Time;
import com.tgroup.service.CityService;
import com.tgroup.service.QuotaDataService;
import com.tgroup.service.QuotaService;
import com.tgroup.service.TimeService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/***
 *  监听器
 * @author yqf
 */
public class ReadExcelModelListener extends AnalysisEventListener<Base> {

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */

    private static final int BATCH_COUNT = 5;
    private List<Base> list = new ArrayList<>();
    private static int count = 1;
    private List<City> cities;

    private CityService cityService;
    private QuotaService quotaService;
    private TimeService timeService;
    private QuotaDataService quotaDataService;

    public ReadExcelModelListener(CityService cityService, QuotaService quotaService, TimeService timeService, QuotaDataService quotaDataService) {
        this.cityService = cityService;
        this.quotaDataService = quotaDataService;
        this.timeService = timeService;
        this.quotaService = quotaService;
    }

    /**
     * 这里会一行行的返回头
     * 监听器只需要重写这个方法就可以读取到头信息
     *
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {

        City city = new City();
        for (int i = Position.START_CITY; i <= Position.END_CITY; i++) {
            city.setCityName(headMap.get(i));
            cityService.addRecord(city);
        }

        cities = cityService.getAllCity();
    }

    @Override
    public void invoke(Base data, AnalysisContext context) {
        list.add(data);
        count++;
        if (list.size() >= BATCH_COUNT) {
            saveData(count);
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData(count);
        System.out.println("所有数据解析完成！");
        System.out.println(" count ：" + count);

    }

    /**
     * 加上存储数据库
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    void saveData(int count) {
        //System.out.println(list);
        Quota quota = new Quota();
        Time time = new Time();

        for (Base base : list) {
            quota.setIndexName(base.getIndexName());
            quota.setDepartment(base.getDepartment());
            quota.setWeight(Float.parseFloat(base.getWeight()));
            quota.setCircle(base.getCircle());
            quota.setUnit(base.getUnit());
            time.setTime(base.getTime());

            quotaService.addQuota(quota);
            timeService.addTime(time);

            Quota rsQuota = quotaService.findOneByName(quota.getIndexName());
            Time  rsTime = timeService.findOneByName(time.getTime());

            List<Double> values = base.getValues();
            QuotaData quotaData = new QuotaData();

            for (int i = 0; i < values.size(); i++) {
                quotaData.setTimeId(rsTime.getId());
                quotaData.setIndexId(rsQuota.getId());
                quotaData.setCityId(cities.get(i).getId());
                quotaData.setValue(values.get(i));
                quotaData.setUploadDate(new Date());
                quotaDataService.addQuotaData(quotaData);
            }


        }
        System.out.println("{ " + count + " }条数据，开始存储数据库！" + list.size());
        System.out.println("存储数据库成功！");

    }

}