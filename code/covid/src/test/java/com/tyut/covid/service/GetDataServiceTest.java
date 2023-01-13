package com.tyut.covid.service;

import com.tyut.covid.bean.CovidBean;
import com.tyut.covid.bean.Supplies;
import com.tyut.covid.dao.GetDataDao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class GetDataServiceTest {
    @Resource
    private GetDataService service;

    @Resource
    private GetDataDao dao;

    @Test
    public void test1(){
        ArrayList<CovidBean> list = new ArrayList<>();
        CovidBean bean = new CovidBean();
        bean.setProvinceName("河南");
        bean.setProvinceShortName("a");
        bean.setCityName("asd");
        bean.setCurrentConfirmedCount(0);
        bean.setConfirmedCount(1);
        bean.setSuspectedCount(0);
        bean.setCuredCount(0);
        bean.setDeadCount(1);
        bean.setLocationId(640200);
        bean.setPid(640000);
        bean.setStatisticsData(null);
        bean.setCities(null);
        bean.setDatetime("2022-05-15");

        CovidBean bean1 = new CovidBean();
        bean1.setProvinceName("河南");
        bean1.setProvinceShortName("a");
        bean1.setCityName("asd");
        bean1.setCurrentConfirmedCount(0);
        bean1.setConfirmedCount(1);
        bean1.setSuspectedCount(0);
        bean1.setCuredCount(0);
        bean1.setDeadCount(1);
        bean1.setLocationId(640200);
        bean1.setPid(640000);
        bean1.setStatisticsData(null);
        bean1.setCities(null);
        bean1.setDatetime("2022-05-15");

        list.add(bean);
        list.add(bean1);
        service.insertData(list);
    }

    @Test
    public void test2(){
        Supplies supplies = new Supplies();
        supplies.setName("呼吸机");
        supplies.setPrice(3000);
        supplies.setCount(5);
        Date date = new Date();
        supplies.setAddtime(date);
        service.insertSupplies(supplies);
    }

    @Test
    public void test3(){
        List<Supplies> dataList = service.getSuppliesList();
        for (Supplies supplies : dataList) {
            System.out.println(supplies);
        }
    }


}
