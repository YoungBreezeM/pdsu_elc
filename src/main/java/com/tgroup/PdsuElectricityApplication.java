package com.tgroup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tgroup.domain.MysqlConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yqf
 */
@SpringBootApplication

public class PdsuElectricityApplication {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(PdsuElectricityApplication.class, args);
    }

}
