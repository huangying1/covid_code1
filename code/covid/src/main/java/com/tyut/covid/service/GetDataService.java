package com.tyut.covid.service;

import com.tyut.covid.bean.*;

import java.io.IOException;
import java.util.List;

public interface GetDataService {

    void insertData(List<CovidBean> dataList);

    void insertWorldData(List<WorldCovidBean> dataList);

    void insertShangHai(List<CityCovidBean> dataList) throws IOException;

    void insertHuBei(List<CityCovidBean> dataList) throws IOException;

    void insertBeiJing(List<CityCovidBean> dataList) throws IOException;

    void insertSupplies(Supplies supplies);

    List<Supplies> getSuppliesList();

    List<ResultVO> getNumOfProvince();

    List<ResultVO> getTopTenProvince();

    List<ResultVO> getNumOfCity();

    List<ResultVO> getTopTwentyCity();

    List<ResultVO> getDeadTopFiveProvince();

    List<ResultVO> getNumOfCountry();

    List<ResultVO> getTopTwentyCountry();

    List<ResultVO> getDeadTopTwentyCountry();

    List<ResultVO> getNumOfContinents();

    List<ResultVOPlus> getDataOfShangHai();

    List<ResultVOPlus> getDataOfHuBei();

    List<ResultVOPlus> getDataOfBeiJing();

}
