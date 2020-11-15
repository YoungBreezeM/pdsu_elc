package com.tgroup;



import com.tgroup.domain.CityValue;
import com.tgroup.mapper.*;
import com.tgroup.pojo.*;
import com.tgroup.service.*;
import com.tgroup.utils.Md5util;
import com.tgroup.utils.TokenUtil;
import com.tgroup.utils.WuLei;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


@SpringBootTest
class PdsuElectricityApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TimeMapper timeMapper;
    @Autowired
    private QuotaMapper quotaMapper;
    @Autowired
    private QuotaDataMapper quotaDataMapper;
    @Autowired
    private ModuleMapper moduleMapper;
    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private QuotaDataService quotaDataService;
    @Autowired
    private UserService userService;
    @Autowired
    private CityService cityService;
    @Autowired
    private TimeService timeService;
    @Autowired
    private QuotaService quotaService;


    @Test
    void findAllUser() {
        List<User> list = userMapper.findAll();
        System.out.println(list);
    }

    @Test
    void findOneById() {
        User user = userMapper.findOneById(30);

        System.out.println(user);
    }

    @Test
    void findOneByUserNameAndPassword() {
        User user = new User();
        user.setId(114);
        user.setName("yqg");
        user.setUsername("yqf");
        user.setPassword("3691308F2A4C2F6983F2880D32E29C84");
        user.setRole(0);
        user.setDepartment("办公部");
        User user1 = userMapper.findOneByUserNameAndPassword(user);
        System.out.println(user1);
    }

    @Test
    void addRecordUser() {
        User user = new User();
        user.setName("yqf");
        user.setUsername("yqf");
        user.setPassword(Md5util.encryption("yqf"));
        user.setPhone("13233757120");
        user.setEmail("347453831@qq.com");
        user.setRole(0);
        user.setDepartment("办公部");
        userMapper.addRecord(user);
    }

    @Test
    void deleteRecordUser() {
        userMapper.deleteRecord(111);
    }

    @Test
    void updateRecordUser() {
        User user = new User();
        user.setId(115);
        user.setName("zy");
        user.setUsername("zy");
        user.setPassword("zh");
        user.setPhone("1323375712");
        user.setEmail("34745383@qq.com");
        user.setRole(0);
        user.setDepartment("财务部");
        userMapper.updateRecord(user);
    }

    @Test
    void findAllTime() {
        List<Time> times = timeMapper.findAll();
        System.out.println(times);
    }

    @Test
    void findOneByIdTime() {
        Time oneById = timeMapper.findOneById(13);
        System.out.println(oneById);
    }

    @Test
    void addRecordTime() {
        Time time = new Time();
        time.setTime("2020");
        timeMapper.addRecord(time);
    }

    @Test
    void deleteRecordTime() {
        timeMapper.deleteRecord(16);
    }

    @Test
    void updateRecordTime() {
        Time time = new Time();
        time.setId(15);
        time.setTime("1803");
        timeMapper.updateRecord(time);
    }

    @Test
    void findAllQuota() {
        List<Quota> quotas = quotaMapper.findAll();
        System.out.println(quotas);
    }

    @Test
    void findOneByIdQuota() {
        Quota quota = quotaMapper.findOneById(145);
        System.out.println(quota);
    }

    @Test
    void addRecordQuota() {
        Quota quota = new Quota();
        quota.setIndexName("测试数据");
        quota.setDepartment("测试部");
        quota.setWeight(4);
        quota.setCircle("%");
        quota.setUnit("季度");

        quotaMapper.addRecord(quota);
    }

    @Test
    void deleteRecordQuota() {
        quotaMapper.deleteRecord(177);
    }

    @Test
    void updateRecordQuota() {
        Quota quota = new Quota();
        quota.setId(176);
        quota.setIndexName("测试数据");
        quota.setDepartment("测试部");
        quota.setWeight(4);
        quota.setCircle("%");
        quota.setUnit("季度");
        quotaMapper.updateRecord(quota);
    }

    @Test
    void findAllMould() {
        List<Module> list = moduleMapper.findAll();
        System.out.println(list);
    }

    @Test
    void findOneByIdModule() {
        Module module = moduleMapper.findOneById(9);
        System.out.println(module);
    }

    @Test
    void findAllQuotas(){
        List<Module> allQuotas = moduleMapper.findAllToQuotas();
        System.out.println(allQuotas);
    }

    @Test
    void addRecordModule() {
        Module module = new Module();
        module.setModuleName("测试数据");
        module.setAllScore(451);
        module.setWeight(100);

        moduleMapper.addRecord(module);
    }

    @Test
    void updateRecordModule() {
        Module module = new Module();
        module.setId(13);
        module.setModuleName("测试数据");
        module.setAllScore(452);
        module.setWeight(90);
        moduleMapper.updateRecord(module);
    }

    @Test
    void deleteRecordModule() {

        moduleMapper.deleteRecord(13);
    }

    @Test
    void findAllFile() {
        List<UploadFile> list = fileMapper.findAll();
        System.out.println(list);
    }

    @Test
    void findOneByIdFile() {
        UploadFile file = fileMapper.findOneById(6);
        System.out.println(file);
    }
//    @Test
//    void addRecordFIle() {
//        UploadFile file = new UploadFile();
//        file.setFilePath("/usr/local/tomcat/tomcat85/bin2020-11-01+11-58-13指标数据汇总表.xlsx");
//        file.setFileName("测试表");
//        file.setUpTime(new Date(System.currentTimeMillis()));
//        fileMapper.addRecord(file);
//    }

    @Test
    void updateRecordFIle() {
        UploadFile file = new UploadFile();
        file.setId(13);
        file.setFilePath("/usr/local/tomcat/tomcat85/bin2020-11-01+11-58-13指标数据汇总表.xlsx");
        file.setFileName("更新测试表");
        file.setUpTime(new Date(System.currentTimeMillis()));
        fileMapper.updateRecord(file);
    }
    @Test
    void deleteRecordFile() {

        fileMapper.deleteRecord(14);
    }
    @Test
    void findAllCity() {
        List<City> list = cityMapper.findAll();
        System.out.println(list);
    }
    @Test
    void findOneByIdCity() {
        City city = cityMapper.findOneById(96);
        System.out.println(city);
    }
    @Test
    void addRecordCity() {
        City city=new City();
        city.setCityName("测试城市");
        cityMapper.addRecord(city);
    }
    @Test
    void updateRecordCity() {
        City city=new City();
        city.setId(111);
        city.setCityName("测试城");
        cityMapper.updateRecord(city);
    }
    @Test
    void deleteRecordCity() {

        cityMapper.deleteRecord(111);
    }

    @Test
    void testTimeService(){

        User user = new User();
        user.setId(1);



        String sign = TokenUtil.sign(user);

        System.out.println(sign);

        User verify = TokenUtil.verify(sign,User.class);

        System.out.println(verify);


    }

    @Test
    void testFileOr(){
        QuotaData quotaData = new QuotaData();
        quotaData.setTimeId(13);
        quotaData.setIndexId(142);
        List<QuotaData> allCityValueByTidAndIid = quotaDataService.getAllCityValueByTidAndIid(quotaData);
        List<CityValue> cityValues = new ArrayList<>();

        if(allCityValueByTidAndIid.size()>0){
            for (QuotaData data : allCityValueByTidAndIid) {
                String cityName = cityMapper.findOneById(data.getCityId()).getCityName();
                cityValues.add(new CityValue(cityName,data.getValue()));

            }

            List<Set<String>> sets = WuLei.five5(cityValues);

            System.out.println(sets);
        }





    }

//    @Test
//    void testUser(){
//        List<City> allC = cityMapper.findAll();
//        List<Quota> allQ = quotaMapper.findAll();
//        List<Time> allTimes= timeMapper.findAll();
//
//        QuotaData quotaData = new QuotaData();
//
//        for (Quota quota : allQ) {
//            for (City city : allC) {
//                for (Time allTime : allTimes) {
//                    quotaData.setUploadDate(new java.sql.Date(System.currentTimeMillis()));
//                    quotaData.setValue(Math.random()*100);
//                    quotaData.setTimeId(allTime.getId());
//                    quotaData.setCityId(city.getId());
//                    quotaData.setIndexId(quota.getId());
//                    quotaDataMapper.addRecord(quotaData);
//                }
//
//            }
//        }
//
//
//    }

    //读取到列表
//    @Test
//    void testReadList() throws Exception {
//        // 读取 excel 表格的路径
//        String readPath = ResourceUtils.getFile("classpath:pdsu.xlsx").toString();;
//
//        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
//        EasyExcel.read(readPath, Base.class, new ReadExcelModelListener(cityService,quotaService,timeService,quotaDataService)).sheet().doRead();
//
//    }



}
