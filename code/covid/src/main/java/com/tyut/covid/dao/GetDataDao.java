package com.tyut.covid.dao;

import com.tyut.covid.bean.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GetDataDao {

    int insertData(CovidBean bean);

    void insertWorldData(WorldCovidBean bean);

    void insertShangHai(CityCovidBean bean);

    void insertHuBei(CityCovidBean bean);

    void insertBeiJing(CityCovidBean bean);

    void insertSupplies(Supplies supplies);

    void updateSupplies(Supplies supplies);


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
