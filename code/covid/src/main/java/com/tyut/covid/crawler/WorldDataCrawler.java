package com.tyut.covid.crawler;

import com.alibaba.fastjson.JSON;
import com.tyut.covid.bean.WorldCovidBean;
import com.tyut.covid.util.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WorldDataCrawler {
    public List<WorldCovidBean> crawling() {
        // 获取当前时间
//        String datetime = TimeUtil.format(System.currentTimeMillis(), "yyyy-MM-dd");
        String datetime = "2022-05-01";

        // 1. 爬取指定页面
        String html = HttpUtil.getHtml("https://ncov.dxy.cn/ncovh5/view/pneumonia");

        // 2. 解析页面中的指定内容
        Document doc = Jsoup.parse(html);
        String text = doc.select("script[id=getListByCountryTypeService2true]").toString();

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
        ArrayList<WorldCovidBean> dataList = new ArrayList<>();

        // 对 json 数据进行更进一步的解析
        // 4. 将第一层json(省份数据)解析成为JavaBean
        List<WorldCovidBean> worldCovidBean = JSON.parseArray(jsonStr, WorldCovidBean.class);
        for (WorldCovidBean wbean : worldCovidBean) {
            // pBean 为省份
            // 设置时间字段
            wbean.setDatetime(datetime);

            System.out.println(wbean);
            dataList.add(wbean);
        }
        return dataList;
    }

    public static void main(String[] args) {
        WorldDataCrawler worldDataCrawler = new WorldDataCrawler();
        List<WorldCovidBean> worldCovidBeanList = worldDataCrawler.crawling();
    }
}
