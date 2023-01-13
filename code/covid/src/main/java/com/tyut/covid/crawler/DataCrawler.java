package com.tyut.covid.crawler;

import com.alibaba.fastjson.JSON;
import com.tyut.covid.bean.CovidBean;
import com.tyut.covid.util.HttpUtil;
import com.tyut.covid.util.TimeUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 疫情数据爬取
 */

public class DataCrawler {

    public List<CovidBean> crawling() {
        // 获取当前时间
        String datetime = TimeUtil.format(System.currentTimeMillis(), "yyyy-MM-dd");
//        String datetime = TimeUtil.format(System.currentTimeMillis(), "2022-02-01");

        // 1. 爬取指定页面
        String html = HttpUtil.getHtml("https://ncov.dxy.cn/ncovh5/view/pneumonia");

        // 2. 解析页面中的指定内容
        Document doc = Jsoup.parse(html);
        String text = doc.select("script[id=getAreaStat]").toString();

        // 3. 使用正则表达式获取 json 格式的疫情数据
        // 定义正则表达式
        String pattern = "\\[(.*)\\]";
        // 编译成正则对象
        Pattern reg = Pattern.compile(pattern);
        // 调用 reg.matcher 来匹配 text 中符合正则表达式的内容
        Matcher matcher = reg.matcher(text);
        String jsonStr = "";
        // 如果 text 中的内容和正则规则匹配上就取出来
        if (matcher.find()) {
            jsonStr = matcher.group(0);
        } else {
            System.out.println("no match");
        }

        // ## dataList 用于存放解析好的数据
        ArrayList<CovidBean> dataList = new ArrayList<>();

        // 对 json 数据进行更进一步的解析
        // 4. 将第一层json(省份数据)解析成为JavaBean
        List<CovidBean> pCovidBeans = JSON.parseArray(jsonStr, CovidBean.class);
        for (CovidBean pBean : pCovidBeans) {
            // pBean 为省份
            // 设置时间字段
            pBean.setDatetime(datetime);
            // 获取 cities
            String citiesStr = pBean.getCities();
            // 5. 将第二层json(城市数据)解析成为JavaBean
            List<CovidBean> cCovidBeans = JSON.parseArray(citiesStr, CovidBean.class);
            for (CovidBean cBean : cCovidBeans) {
                // cBean 为城市
                cBean.setDatetime(datetime);
                cBean.setPid(pBean.getLocationId());
                cBean.setProvinceShortName(pBean.getProvinceShortName());
                cBean.setProvinceName(pBean.getProvinceName());

                // 城市数据
                System.out.println(cBean);
                dataList.add(cBean);
            }
        }
        return dataList;
    }

}

