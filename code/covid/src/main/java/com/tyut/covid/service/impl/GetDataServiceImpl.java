package com.tyut.covid.service.impl;

import com.tyut.covid.bean.*;
import com.tyut.covid.dao.GetDataDao;
import com.tyut.covid.service.GetDataService;
import com.tyut.covid.service.IDistrictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class GetDataServiceImpl implements GetDataService {
    @Resource
    private GetDataDao dao;

    @Resource
    private IDistrictService districtService;

    @Override
    public void insertData(List<CovidBean> dataList) {
        for (CovidBean bean : dataList) {
            dao.insertData(bean);
        }
    }

    @Override
    public void insertWorldData(List<WorldCovidBean> dataList) {
        for (WorldCovidBean worldCovidBean : dataList) {
            if (worldCovidBean.getId() != null) {
                dao.insertWorldData(worldCovidBean);
            }
        }
    }

    @Override
    public void insertShangHai(List<CityCovidBean> dataList) {
        for (CityCovidBean cityCovidBean : dataList) {
            dao.insertShangHai(cityCovidBean);
        }
    }

    @Override
    public void insertHuBei(List<CityCovidBean> dataList) {
        for (CityCovidBean cityCovidBean : dataList) {
            dao.insertHuBei(cityCovidBean);
        }
    }

    @Override
    public void insertBeiJing(List<CityCovidBean> dataList) {
        for (CityCovidBean cityCovidBean : dataList) {
            dao.insertBeiJing(cityCovidBean);
        }
    }

    @Override
    public void insertSupplies(Supplies supplies) {
        // 补全数据：province_name、city_name、area_name
        District province = districtService.getByCode(supplies.getProvinceCode());
        District city = districtService.getByCode(supplies.getCityCode());
        District area = districtService.getByCode(supplies.getAreaCode());
        if(province == null) {
            // 此处，将 未选择 时的值 -1 直接改为 null
            supplies.setProvinceCode(null);
        }else {
            supplies.setProvinceName(province.getName());
        }
        if(city == null) {
            supplies.setCityCode(null);
        }else {
            supplies.setCityName(city.getName());
        }
        if(area == null) {
            supplies.setAreaCode(null);
        }else {
            supplies.setAreaName(area.getName());
        }

        // 查看物资是否已存在
//        List<Supplies> list = dao.getSuppliesList();
//        for (Supplies supplies1 : list) {
//            if(supplies.getName().equals(supplies1.getName())){
//                // 物资已存在！设置新的金额
//                supplies.setMoney(supplies.getCount() * supplies.getPrice() + supplies1.getMoney());
//                // 设置新的数量
//                supplies.setCount(supplies.getCount() + supplies1.getCount());
//                // 设置最新时间
//                Date date = new Date();
//                supplies.setAddtime(date);
//                // 将 supplies 插入到数据库
//                dao.updateSupplies(supplies);
//                return;
//            }
//        }
//        // 不存在，直接添加
        Date date = new Date();
        supplies.setAddtime(date);
        supplies.setMoney(supplies.getPrice() * supplies.getCount());
        dao.insertSupplies(supplies);
    }

    @Override
    public List<Supplies> getSuppliesList() {
        List<Supplies> dateList = dao.getSuppliesList();
        return dateList;
    }


    @Override
    public List<ResultVO> getNumOfProvince() {
        List<ResultVO> dataList = dao.getNumOfProvince();
        return dataList;
    }

    @Override
    public List<ResultVO> getTopTenProvince() {
        List<ResultVO> dataList = dao.getTopTenProvince();
        return dataList;
    }

    @Override
    public List<ResultVO> getNumOfCity() {
        List<ResultVO> dataList = dao.getNumOfCity();
        return dataList;
    }

    @Override
    public List<ResultVO> getTopTwentyCity() {
        List<ResultVO> dataList = dao.getTopTwentyCity();
        return dataList;
    }

    @Override
    public List<ResultVO> getDeadTopFiveProvince() {
        List<ResultVO> dataList = dao.getDeadTopFiveProvince();
        return dataList;
    }

    @Override
    public List<ResultVO> getNumOfCountry() {
        List<ResultVO> dataList = dao.getNumOfCountry();
        return dataList;
    }

    @Override
    public List<ResultVO> getTopTwentyCountry() {
        List<ResultVO> dataList = dao.getTopTwentyCountry();
        return dataList;
    }

    @Override
    public List<ResultVO> getDeadTopTwentyCountry() {
        List<ResultVO> dataList = dao.getDeadTopTwentyCountry();
        return dataList;
    }

    @Override
    public List<ResultVO> getNumOfContinents() {
        List<ResultVO> dataList = dao.getNumOfContinents();
        return dataList;
    }

    @Override
    public List<ResultVOPlus> getDataOfShangHai() {
        List<ResultVOPlus> dataList = dao.getDataOfShangHai();
        return dataList;
    }

    @Override
    public List<ResultVOPlus> getDataOfHuBei() {
        List<ResultVOPlus> dataList = dao.getDataOfHuBei();
        return dataList;
    }

    @Override
    public List<ResultVOPlus> getDataOfBeiJing() {
        List<ResultVOPlus> dataList = dao.getDataOfBeiJing();
        return dataList;
    }


}
