package com.tyut.covid.controller;

import com.tyut.covid.bean.CovidBean;
import com.tyut.covid.bean.ResultVO;
import com.tyut.covid.bean.ResultVOPlus;
import com.tyut.covid.service.SparkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class SparkController {

    @Autowired
    private SparkService sparkService;

    @GetMapping("/getNumByProvince")
    public List<CovidBean> getNumByProvince() {
        List<CovidBean> list = sparkService.getNumByProvince();
        return list;
    }

    @GetMapping("/getTopTenProvince")
    public List<CovidBean> getTopTenProvince() {
        List<CovidBean> list = sparkService.getTopTenProvince();
        return list;
    }

    @RequestMapping("/getNumOfCity")
    public List<ResultVO> getNumOfCity() {
        List<ResultVO> list = sparkService.getNumOfCity();
        return list;
    }

    @RequestMapping("/getTopTwentyCity")
    public List<ResultVO> getTopTwentyCity() {
        List<ResultVO> list = sparkService.getTopTwentyCity();
        return list;
    }

    @RequestMapping("/getDeadTopFiveProvince")
    public List<ResultVO> getDeadTopFiveProvince() {
        List<ResultVO> list = sparkService.getDeadTopFiveProvince();
        return list;
    }

    @RequestMapping("/getNumOfCountry")
    public List<ResultVO> getNumOfCountry() {
        List<ResultVO> list = sparkService.getNumOfCountry();
        return list;
    }

    @RequestMapping("/getTopTwentyCountry")
    public List<ResultVO> getTopTwentyCountry() {
        List<ResultVO> list = sparkService.getTopTwentyCountry();
        return list;
    }

    @RequestMapping("/getDeadTopTwentyCountry")
    public List<ResultVO> getDeadTopTwentyCountry() {
        List<ResultVO> list = sparkService.getDeadTopTwentyCountry();
        return list;
    }

    @RequestMapping("/getNumOfContinents")
    public List<ResultVO> getNumOfContinents() {
        List<ResultVO> list = sparkService.getNumOfContinents();
        return list;
    }

    @RequestMapping("/getDataOfShangHai")
    public List<ResultVOPlus> getDataOfShangHai() {
        List<ResultVOPlus> list = sparkService.getDataOfShangHai();
        return list;
    }

    @RequestMapping("/getDataOfHuBei")
    public List<ResultVOPlus> getDataOfHuBei() {
        List<ResultVOPlus> list = sparkService.getDataOfHuBei();
        return list;
    }

    @RequestMapping("/getDataOfBeiJing")
    public List<ResultVOPlus> getDataOfBeiJing() {
        List<ResultVOPlus> list = sparkService.getDataOfBeiJing();
        return list;
    }

}
