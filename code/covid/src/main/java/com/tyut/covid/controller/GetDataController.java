package com.tyut.covid.controller;

import com.tyut.covid.bean.*;
import com.tyut.covid.crawler.DataCrawler;
import com.tyut.covid.crawler.DataCrawlerPlus;
import com.tyut.covid.crawler.WorldDataCrawler;
import com.tyut.covid.service.GetDataService;
import com.tyut.covid.util.JsonFormatBean;
import com.tyut.covid.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/data")
public class GetDataController extends BaseController {
    @Resource
    private GetDataService service;

    @RequestMapping("/getNumOfProvince")
    public JsonResult<List<ResultVO>> getNumOfProvince() {
        List<ResultVO> dataList = service.getNumOfProvince();
        return new JsonResult<List<ResultVO>>(200, dataList);
    }

    @RequestMapping("/getTopTenProvince")
    public JsonResult<List<ResultVO>> getTopTenProvince() {
        List<ResultVO> dataList = service.getTopTenProvince();
        return new JsonResult<List<ResultVO>>(200, dataList);
    }

    @RequestMapping("/getNumOfCity")
    public JsonResult<List<ResultVO>> getNumOfCity() {
        List<ResultVO> dataList = service.getNumOfCity();
        return new JsonResult<List<ResultVO>>(200, dataList);
    }

    @RequestMapping("/getTopTwentyCity")
    public JsonResult<List<ResultVO>> getTopTwentyCity() {
        List<ResultVO> dataList = service.getTopTwentyCity();
        return new JsonResult<List<ResultVO>>(200, dataList);
    }

    @RequestMapping("/getDeadTopFiveProvince")
    public JsonResult<List<ResultVO>> getDeadTopFiveProvince() {
        List<ResultVO> dataList = service.getDeadTopFiveProvince();
        return new JsonResult<List<ResultVO>>(200, dataList);
    }

    @RequestMapping("/getNumOfCountry")
    public JsonResult<List<ResultVO>> getNumOfCountry() {
        List<ResultVO> dataList = service.getNumOfCountry();
        return new JsonResult<List<ResultVO>>(200, dataList);
    }

    @RequestMapping("/getTopTwentyCountry")
    public JsonResult<List<ResultVO>> getTopTwentyCountry() {
        List<ResultVO> dataList = service.getTopTwentyCountry();
        return new JsonResult<List<ResultVO>>(200, dataList);
    }

    @RequestMapping("/getDeadTopTwentyCountry")
    public JsonResult<List<ResultVO>> getDeadTopTwentyCountry() {
        List<ResultVO> dataList = service.getDeadTopTwentyCountry();
        return new JsonResult<List<ResultVO>>(200, dataList);
    }

    @RequestMapping("/getNumOfContinents")
    public JsonResult<List<ResultVO>> getNumOfContinents() {
        List<ResultVO> dataList = service.getNumOfContinents();
        return new JsonResult<List<ResultVO>>(200, dataList);
    }

    @RequestMapping("/getDataOfShangHai")
    public JsonResult<List<ResultVOPlus>> getDataOfShangHai() {
        List<ResultVOPlus> dataList = service.getDataOfShangHai();
        return new JsonResult<List<ResultVOPlus>>(200, dataList);
    }

    @RequestMapping("/getDataOfHuBei")
    public JsonResult<List<ResultVOPlus>> getDataOfHuBei() {
        List<ResultVOPlus> dataList = service.getDataOfHuBei();
        return new JsonResult<List<ResultVOPlus>>(200, dataList);
    }

    @RequestMapping("/getDataOfBeiJing")
    public JsonResult<List<ResultVOPlus>> getDataOfBeiJing() {
        List<ResultVOPlus> dataList = service.getDataOfBeiJing();
        return new JsonResult<List<ResultVOPlus>>(200, dataList);
    }





    @RequestMapping("/crawling")
    public void crawling(){
        System.err.println("执行爬虫代码...");
        DataCrawler dataCrawler = new DataCrawler();
        List<CovidBean> dataList = dataCrawler.crawling();
        for (CovidBean bean : dataList) {
            System.out.println(bean.toString());
        }
//        service.insertData(dataList);
    }

    @RequestMapping("/crawlingPlus")
    public void crawlingPlus(){
        System.err.println("执行爬虫代码...");
        DataCrawlerPlus dataCrawlerPlus = new DataCrawlerPlus();
        List<CovidBean> dataList = dataCrawlerPlus.crawling();
//        for (CovidBean bean : dataList) {
//            System.out.println(bean.toString());
//        }
//        service.insertData(dataList);
    }

    @RequestMapping("/worldCovidCrawling")
    public void worldCovidCrawling(){
        System.err.println("执行爬虫代码...");
        WorldDataCrawler worldDataCrawler = new WorldDataCrawler();
        List<WorldCovidBean> dataList = worldDataCrawler.crawling();
        for (WorldCovidBean worldCovidBean : dataList) {
            System.out.println(worldCovidBean);
        }
        service.insertWorldData(dataList);
    }

    @RequestMapping("/shanghai")
    public void shanghai() throws IOException {
        String path = "D:\\Intellijworkspace\\covid\\src\\main\\resources\\shanghai.txt";
        List<CityCovidBean> shanghaiList = JsonFormatBean.cityEveryDayData(path);
        for (CityCovidBean bean : shanghaiList) {
            System.out.println(bean);
        }
        service.insertShangHai(shanghaiList);
    }

    @RequestMapping("/hubei")
    public void hubei() throws IOException {
        String path = "D:\\Intellijworkspace\\covid\\src\\main\\resources\\hubei.txt";
        List<CityCovidBean> huBeiList = JsonFormatBean.cityEveryDayData(path);
        for (CityCovidBean bean : huBeiList) {
            System.out.println(bean);
        }
        service.insertHuBei(huBeiList);
    }

    @RequestMapping("/beijing")
    public void insertBeiJing() throws IOException {
        String path = "D:\\Intellijworkspace\\covid\\src\\main\\resources\\beijing.txt";
        List<CityCovidBean> huBeiList = JsonFormatBean.cityEveryDayData(path);
        for (CityCovidBean bean : huBeiList) {
            System.out.println(bean);
        }
        service.insertBeiJing(huBeiList);
    }

    @RequestMapping("/insertSupplies")
    public JsonResult<Void> insertSupplies(Supplies supplies) {
        service.insertSupplies(supplies);
        return new JsonResult<Void>(SUCCESS);
    }

    @RequestMapping("/suppliesList")
    public JsonResult<List<Supplies>> getSuppliesList(){
        List<Supplies> dataList = service.getSuppliesList();
        return new JsonResult<>(SUCCESS, dataList);
    }

}
