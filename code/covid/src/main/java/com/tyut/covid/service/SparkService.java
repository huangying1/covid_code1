package com.tyut.covid.service;


import com.tyut.covid.bean.CovidBean;
import com.tyut.covid.bean.ResultVO;
import com.tyut.covid.bean.ResultVOPlus;
import com.tyut.covid.util.SparkSqlUtil;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;


@Service
public class SparkService implements Serializable {

    @Resource
    private SparkSession sparkSession;

    public List<CovidBean> getNumByProvince(){
        String sql = "SELECT province_name,sum(confirmed_count) FROM covid_data group by province_name";
        return SparkSqlUtil.getNumByProvince(sparkSession, sql);
    }

    public List<CovidBean> getTopTenProvince(){
        String sql = "SELECT name,sum(shoucnum) FROM covid_data group by name";
        return SparkSqlUtil.getTopTenProvince(sparkSession, sql);
    }

    @RequestMapping("/getNumOfCity")
    public List<ResultVO> getNumOfCity() {
        String sql = "select\n" +
                "\t\tcity_name AS `key`,\n" +
                "\t\tconfirmed_count AS `value`\n" +
                "\t\tfrom\n" +
                "\t\tcovid\n" +
                "\t\tgroup by\n" +
                "\t\tcity_name";
        return SparkSqlUtil.getNumOfCity(sparkSession, sql);
    }

    @RequestMapping("/getTopTwentyCity")
    public List<ResultVO> getTopTwentyCity() {
        String sql = "select\n" +
                "\t\tcity_name AS `key`,\n" +
                "\t\tconfirmed_count AS `value`\n" +
                "\t\tfrom\n" +
                "\t\tcovid\n" +
                "\t\tgroup by\n" +
                "\t\tcity_name\n" +
                "\t\torder by\n" +
                "\t\t`value`\n" +
                "\t\tdesc\n" +
                "\t\tLIMIT 0, 20;";
        return SparkSqlUtil.getTopTwentyCity(sparkSession, sql);
    }

    @RequestMapping("/getDeadTopFiveProvince")
    public List<ResultVO> getDeadTopFiveProvince() {
        String sql = "select\n" +
                "\t\tprovince_name AS `key`,\n" +
                "\t\tsum(dead_count) AS `value`\n" +
                "\t\tfrom\n" +
                "\t\tcovid\n" +
                "\t\tgroup by\n" +
                "\t\tprovince_name\n" +
                "\t\torder by\n" +
                "\t\t`value`\n" +
                "\t\tdesc\n" +
                "\t\tLIMIT 0, 5;";
        return SparkSqlUtil.getDeadTopFiveProvince(sparkSession, sql);
    }

    @RequestMapping("/getNumOfCountry")
    public List<ResultVO> getNumOfCountry() {
        String sql = "select\n" +
                "\t\tprovince_name AS `key`,\n" +
                "\t\tconfirmed_count AS `value`\n" +
                "\t\tfrom\n" +
                "\t\tworld_covid";
        return SparkSqlUtil.getNumOfCountry(sparkSession, sql);
    }

    @RequestMapping("/getTopTwentyCountry")
    public List<ResultVO> getTopTwentyCountry() {
        String sql = "select\n" +
                "\t\tprovince_name AS `key`,\n" +
                "\t\tconfirmed_count AS `value`\n" +
                "\t\tfrom\n" +
                "\t\tworld_covid\n" +
                "\t\torder by\n" +
                "\t\t`value`\n" +
                "\t\tdesc\n" +
                "\t\tlimit 0, 20";
        return SparkSqlUtil.getTopTwentyCountry(sparkSession, sql);
    }

    @RequestMapping("/getDeadTopTwentyCountry")
    public List<ResultVO> getDeadTopTwentyCountry() {
        String sql = "select\n" +
                "\t\tprovince_name AS `key`,\n" +
                "\t\tdead_count AS `value`\n" +
                "\t\tfrom\n" +
                "\t\tworld_covid\n" +
                "\t\torder by\n" +
                "\t\t`value`\n" +
                "\t\tdesc\n" +
                "\t\tlimit 0, 20";
        return SparkSqlUtil.getDeadTopTwentyCountry(sparkSession, sql);
    }

    @RequestMapping("/getNumOfContinents")
    public List<ResultVO> getNumOfContinents() {
        String sql = "SELECT\n" +
                "\t\tcontinents AS `key`,\n" +
                "\t\tSUM(confirmed_count) AS `value`\n" +
                "\t\tFROM\n" +
                "\t\t`world_covid`\n" +
                "\t\tGROUP BY\n" +
                "\t\tcontinents\n" +
                "\t\torder by\n" +
                "\t\t`value`\n" +
                "\t\tdesc";
        return SparkSqlUtil.getNumOfContinents(sparkSession, sql);
    }

    @RequestMapping("/getDataOfShangHai")
    public List<ResultVOPlus> getDataOfShangHai() {
        String sql = "select\n" +
                "\t\tconfirmed_count AS `value1`,\n" +
                "\t\tcured_count AS `value2`,\n" +
                "\t\tdead_count AS `value3`,\n" +
                "\t\tdate_id AS `key`\n" +
                "\t\tfrom\n" +
                "\t\tshanghai";
        return SparkSqlUtil.getDataOfShangHai(sparkSession, sql);
    }

    @RequestMapping("/getDataOfHuBei")
    public List<ResultVOPlus> getDataOfHuBei() {
        String sql = "select\n" +
                "\t\tconfirmed_count AS `value1`,\n" +
                "\t\tcured_count AS `value2`,\n" +
                "\t\tdead_count AS `value3`,\n" +
                "\t\tdate_id AS `key`\n" +
                "\t\tfrom\n" +
                "\t\thubei";
        return SparkSqlUtil.getDataOfHuBei(sparkSession, sql);
    }

    @RequestMapping("/getDataOfBeiJing")
    public List<ResultVOPlus> getDataOfBeiJing() {
        String sql = "select\n" +
                "\t\tconfirmed_count AS `value1`,\n" +
                "\t\tcured_count AS `value2`,\n" +
                "\t\tdead_count AS `value3`,\n" +
                "\t\tdate_id AS `key`\n" +
                "\t\tfrom\n" +
                "\t\tbeijing";
        return SparkSqlUtil.getDataOfBeiJing(sparkSession, sql);
    }

}
