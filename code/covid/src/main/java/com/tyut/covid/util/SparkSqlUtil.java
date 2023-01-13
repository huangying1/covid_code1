package com.tyut.covid.util;

import com.google.common.collect.Lists;
import com.tyut.covid.bean.CovidBean;
import com.tyut.covid.bean.ResultVO;
import com.tyut.covid.bean.ResultVOPlus;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.List;

/**
 * @author Dell
 */
public class SparkSqlUtil {

    private static final String tempPath1 = System.getProperty("user.dir") + "\\src\\main\\resources\\getNumOfProvince.json";
    private static final String tempPath2 = System.getProperty("user.dir") + "\\src\\main\\resources\\getTopTenProvince.json";
    private static final String tempPath3 = System.getProperty("user.dir") + "\\src\\main\\resources\\getNumOfCity.json";
    private static final String tempPath4 = System.getProperty("user.dir") + "\\src\\main\\resources\\getTopTwentyCity.json";
    private static final String tempPath5 = System.getProperty("user.dir") + "\\src\\main\\resources\\getDeadTopFiveProvince.json";
    private static final String tempPath6 = System.getProperty("user.dir") + "\\src\\main\\resources\\getNumOfCountry.json";
    private static final String tempPath7 = System.getProperty("user.dir") + "\\src\\main\\resources\\getTopTwentyCountry.json";
    private static final String tempPath8 = System.getProperty("user.dir") + "\\src\\main\\resources\\getDeadTopTwentyCountry.json";
    private static final String tempPath9 = System.getProperty("user.dir") + "\\src\\main\\resources\\getNumOfContinents.json";
    private static final String tempPath10 = System.getProperty("user.dir") + "\\src\\main\\resources\\getDataOfShangHai.json";
    private static final String tempPath11 = System.getProperty("user.dir") + "\\src\\main\\resources\\getDataOfHuBei.json";
    private static final String tempPath12 = System.getProperty("user.dir") + "\\src\\main\\resources\\getDataOfBeiJing.json";

    // 各省确诊人数
    public static List<CovidBean> getNumByProvince(SparkSession sparkSession, String sql) {
        Dataset<Row> df = sparkSession.read().json(tempPath1);
        df.createOrReplaceTempView("covid_data");
        df = sparkSession.sql(sql);
        List<Row> rows = df.collectAsList();
        List<CovidBean> list = Lists.newArrayList();
        for (Row row : rows) {
            CovidBean vo = new CovidBean();
            vo.setProvinceName(row.getAs(0).toString());
            vo.setConfirmedCount(Integer.valueOf(row.getAs(1).toString()));
            list.add(vo);
        }
        return list;
    }

    // 确诊人数前十名的省份
    public static List<CovidBean> getTopTenProvince(SparkSession sparkSession, String sql) {
        Dataset<Row> df = sparkSession.read().json(tempPath2);
        df.createOrReplaceTempView("covid_data");
        df = sparkSession.sql(sql);
        List<Row> rows = df.collectAsList();
        List<CovidBean> list = Lists.newArrayList();
        for (Row row : rows) {
            CovidBean vo = new CovidBean();
            vo.setProvinceName(row.getAs(0).toString());
            vo.setConfirmedCount(Integer.valueOf(row.getAs(1).toString()));
            list.add(vo);
        }
        return list;
    }


    // 上海确诊、治愈、死亡
    public static List<ResultVOPlus> getDataOfShangHai(SparkSession sparkSession, String sql) {
        Dataset<Row> df = sparkSession.read().json(tempPath10);
        df.createOrReplaceTempView("covid_data");
        df = sparkSession.sql(sql);
        List<Row> rows = df.collectAsList();
        List<ResultVOPlus> list = Lists.newArrayList();
        for (Row row : rows) {
            ResultVOPlus vo = new ResultVOPlus();
            vo.setKey(row.getAs(0).toString());
            vo.setValue1(Integer.valueOf(row.getAs(1).toString()));
            vo.setValue2(Integer.valueOf(row.getAs(2).toString()));
            vo.setValue3(Integer.valueOf(row.getAs(3).toString()));
            list.add(vo);
        }
        return list;
    }

    // 湖北确诊、治愈、死亡
    public static List<ResultVOPlus> getDataOfHuBei(SparkSession sparkSession, String sql) {
        Dataset<Row> df = sparkSession.read().json(tempPath11);
        df.createOrReplaceTempView("covid_data");
        df = sparkSession.sql(sql);
        List<Row> rows = df.collectAsList();
        List<ResultVOPlus> list = Lists.newArrayList();
        for (Row row : rows) {
            ResultVOPlus vo = new ResultVOPlus();
            vo.setKey(row.getAs(0).toString());
            vo.setValue1(Integer.valueOf(row.getAs(1).toString()));
            vo.setValue2(Integer.valueOf(row.getAs(2).toString()));
            vo.setValue3(Integer.valueOf(row.getAs(3).toString()));
            list.add(vo);
        }
        return list;
    }

    // 北京确诊、治愈、死亡
    public static List<ResultVOPlus> getDataOfBeiJing(SparkSession sparkSession, String sql) {
        Dataset<Row> df = sparkSession.read().json(tempPath12);
        df.createOrReplaceTempView("covid_data");
        df = sparkSession.sql(sql);
        List<Row> rows = df.collectAsList();
        List<ResultVOPlus> list = Lists.newArrayList();
        for (Row row : rows) {
            ResultVOPlus vo = new ResultVOPlus();
            vo.setKey(row.getAs(0).toString());
            vo.setValue1(Integer.valueOf(row.getAs(1).toString()));
            vo.setValue2(Integer.valueOf(row.getAs(2).toString()));
            vo.setValue3(Integer.valueOf(row.getAs(3).toString()));
            list.add(vo);
        }
        return list;
    }

    // 死亡数前五名的省份
    public static List<ResultVO> getDeadTopFiveProvince(SparkSession sparkSession, String sql) {
        Dataset<Row> df = sparkSession.read().json(tempPath5);
        df.createOrReplaceTempView("covid_data");
        df = sparkSession.sql(sql);
        List<Row> rows = df.collectAsList();
        List<ResultVO> list = Lists.newArrayList();
        for (Row row : rows) {
            ResultVO vo = new ResultVO();
            vo.setKey(row.getAs(0).toString());
            vo.setValue(Integer.valueOf(row.getAs(1).toString()));
            list.add(vo);
        }
        return list;
    }


    // 全球各国的确诊人数
    public static List<ResultVO> getNumOfCountry(SparkSession sparkSession, String sql) {
        Dataset<Row> df = sparkSession.read().json(tempPath6);
        df.createOrReplaceTempView("covid_data");
        df = sparkSession.sql(sql);
        List<Row> rows = df.collectAsList();
        List<ResultVO> list = Lists.newArrayList();
        for (Row row : rows) {
            ResultVO vo = new ResultVO();
            vo.setKey(row.getAs(0).toString());
            vo.setValue(Integer.valueOf(row.getAs(1).toString()));
            list.add(vo);
        }
        return list;
    }


    // 七大洲的确诊人数
    public static List<ResultVO> getNumOfContinents(SparkSession sparkSession, String sql) {
        Dataset<Row> df = sparkSession.read().json(tempPath9);
        df.createOrReplaceTempView("covid_data");
        df = sparkSession.sql(sql);
        List<Row> rows = df.collectAsList();
        List<ResultVO> list = Lists.newArrayList();
        for (Row row : rows) {
            ResultVO vo = new ResultVO();
            vo.setKey(row.getAs(0).toString());
            vo.setValue(Integer.valueOf(row.getAs(1).toString()));
            list.add(vo);
        }
        return list;
    }


    // 全国各地区的确诊人数
    public static List<ResultVO> getNumOfCity(SparkSession sparkSession, String sql) {
        Dataset<Row> df = sparkSession.read().json(tempPath3);
        df.createOrReplaceTempView("covid_data");
        df = sparkSession.sql(sql);
        List<Row> rows = df.collectAsList();
        List<ResultVO> list = Lists.newArrayList();
        for (Row row : rows) {
            ResultVO vo = new ResultVO();
            vo.setKey(row.getAs(0).toString());
            vo.setValue(Integer.valueOf(row.getAs(1).toString()));
            list.add(vo);
        }
        return list;
    }

    // 确诊人数前二十名的地区
    public static List<ResultVO> getTopTwentyCity(SparkSession sparkSession, String sql) {
        Dataset<Row> df = sparkSession.read().json(tempPath4);
        df.createOrReplaceTempView("covid_data");
        df = sparkSession.sql(sql);
        List<Row> rows = df.collectAsList();
        List<ResultVO> list = Lists.newArrayList();
        for (Row row : rows) {
            ResultVO vo = new ResultVO();
            vo.setKey(row.getAs(0).toString());
            vo.setValue(Integer.valueOf(row.getAs(1).toString()));
            list.add(vo);
        }
        return list;
    }





    // 全球确诊人数最多的二十个国家
    public static List<ResultVO> getTopTwentyCountry(SparkSession sparkSession, String sql) {
        Dataset<Row> df = sparkSession.read().json(tempPath7);
        df.createOrReplaceTempView("covid_data");
        df = sparkSession.sql(sql);
        List<Row> rows = df.collectAsList();
        List<ResultVO> list = Lists.newArrayList();
        for (Row row : rows) {
            ResultVO vo = new ResultVO();
            vo.setKey(row.getAs(0).toString());
            vo.setValue(Integer.valueOf(row.getAs(1).toString()));
            list.add(vo);
        }
        return list;
    }

    // 死亡人数最多的国家
    public static List<ResultVO> getDeadTopTwentyCountry(SparkSession sparkSession, String sql) {
        Dataset<Row> df = sparkSession.read().json(tempPath8);
        df.createOrReplaceTempView("covid_data");
        df = sparkSession.sql(sql);
        List<Row> rows = df.collectAsList();
        List<ResultVO> list = Lists.newArrayList();
            for (Row row : rows) {
            ResultVO vo = new ResultVO();
            vo.setKey(row.getAs(0).toString());
            vo.setValue(Integer.valueOf(row.getAs(1).toString()));
            list.add(vo);
        }
        return list;
    }

}
