package com.tyut.covid.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tyut.covid.bean.CityCovidBean;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dell
 */
public class JsonFormatBean {

    public static List<CityCovidBean> cityEveryDayData(String url) throws IOException {
        String filePath = url;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        String jsonStr = bufferedReader.readLine();

        List<CityCovidBean> dataList = new ArrayList<>();

        // 将 Json 字符串解析为 Json数组
        JSONArray arr = JSON.parseArray(jsonStr);
        for(int i = 0; i < arr.size(); i++){
            JSONObject object = (JSONObject) arr.get(i);
            CityCovidBean cBean = new CityCovidBean();
            cBean.setConfirmedCount(Integer.valueOf(object.getString("confirmedCount")));
            cBean.setConfirmedIncr(Integer.valueOf(object.getString("confirmedIncr")));
            cBean.setCuredCount(Integer.valueOf(object.getString("curedCount")));
            cBean.setCuredIncr(Integer.valueOf(object.getString("curedIncr")));
            cBean.setDeadCount(Integer.valueOf(object.getString("deadCount")));
            cBean.setDeadIncr(Integer.valueOf(object.getString("deadIncr")));
            cBean.setDateId(object.getString("dateId"));

            dataList.add(cBean);
        }
        return dataList;
    }

    public static void main(String[] args) throws IOException {
//        List<CityCovidBean> dataList =
//                JsonFormatBean.cityEveryDayData("D:\\Intellijworkspace\\covid\\src\\main\\resources\\shanghai.txt");

//        List<CityCovidBean> dataList =
//                JsonFormatBean.cityEveryDayData("D:\\Intellijworkspace\\covid\\src\\main\\resources\\hubei.txt");

        List<CityCovidBean> dataList =
                JsonFormatBean.cityEveryDayData("D:\\Intellijworkspace\\covid\\src\\main\\resources\\beijing.txt");

        for (CityCovidBean cityCovidBean : dataList) {
            System.out.println(cityCovidBean);
        }
    }
}
