package com.tyut.covid.dao;

import com.tyut.covid.bean.CovidBean;
import com.tyut.covid.bean.ResultVO;
import com.tyut.covid.bean.ResultVOPlus;
import com.tyut.covid.bean.Supplies;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class GetDataDaoTest {

    @Resource
    private GetDataDao dao;

    @Test
    public void test1(){
        CovidBean bean = new CovidBean();
        bean.setProvinceName("湖北");
        bean.setProvinceShortName("a");
        bean.setCityName("asd");
        bean.setCurrentConfirmedCount(0);
        bean.setConfirmedCount(1);
        bean.setSuspectedCount(0);
        bean.setCuredCount(0);
        bean.setDeadCount(1);
        bean.setLocationId(640200);
        bean.setPid(640000);
        bean.setStatisticsData(null);
        bean.setCities(null);
        bean.setDatetime("2022-05-15");
        int row = dao.insertData(bean);
        if(row == 1){
            System.err.println("成功！");
        }
    }

    @Test
    public void test2(){
        List<ResultVO> dataList = dao.getNumOfProvince();
        if (dataList != null && !dataList.isEmpty()) {
            for (ResultVO resultVO : dataList) {
                System.out.println(resultVO.getKey() + "--" + resultVO.getValue());
            }
        }
    }

    @Test
    public void test3(){
        List<ResultVO> dataList = dao.getTopTenProvince();
        if (dataList != null && !dataList.isEmpty()) {
            for (ResultVO resultVO : dataList) {
                System.out.println(resultVO.getKey() + "--" + resultVO.getValue());
            }
        }
    }

    @Test
    public void test4(){
        List<ResultVO> dataList = dao.getNumOfCity();
        if (dataList != null && !dataList.isEmpty()) {
            for (ResultVO resultVO : dataList) {
                System.out.println(resultVO.getKey() + "--" + resultVO.getValue());
            }
        }
        System.out.println(dataList.size());
    }

    @Test
    public void test5(){
        List<ResultVO> dataList = dao.getTopTwentyCity();
        if (dataList != null && !dataList.isEmpty()) {
            for (ResultVO resultVO : dataList) {
                System.out.println(resultVO.getKey() + "--" + resultVO.getValue());
            }
        }
        System.out.println(dataList.size());
    }

    @Test
    public void test6(){
        List<ResultVO> dataList = dao.getDeadTopFiveProvince();
        if (dataList != null && !dataList.isEmpty()) {
            for (ResultVO resultVO : dataList) {
                System.out.println(resultVO.getKey() + "--" + resultVO.getValue());
            }
        }
        System.out.println(dataList.size());
    }

    @Test
    public void test7(){
        List<ResultVO> dataList = dao.getNumOfCountry();
        if (dataList != null && !dataList.isEmpty()) {
            for (ResultVO resultVO : dataList) {
                System.out.println(resultVO.getKey() + "--" + resultVO.getValue());
            }
        }
        System.out.println(dataList.size());
    }

    @Test
    public void test8(){
        List<ResultVO> dataList = dao.getTopTwentyCountry();
        if (dataList != null && !dataList.isEmpty()) {
            for (ResultVO resultVO : dataList) {
                System.out.println(resultVO.getKey() + "--" + resultVO.getValue());
            }
        }
        System.out.println(dataList.size());
    }

    @Test
    public void test9(){
        List<ResultVO> dataList = dao.getDeadTopTwentyCountry();
        if (dataList != null && !dataList.isEmpty()) {
            for (ResultVO resultVO : dataList) {
                System.out.println(resultVO.getKey() + "--" + resultVO.getValue());
            }
        }
        System.out.println(dataList.size());
    }

    @Test
    public void test10(){
        List<ResultVO> dataList = dao.getNumOfContinents();
        if (dataList != null && !dataList.isEmpty()) {
            for (ResultVO resultVO : dataList) {
                System.out.println(resultVO.getKey() + "--" + resultVO.getValue());
            }
        }
        System.out.println(dataList.size());
    }

    @Test
    public void test11(){
        List<ResultVOPlus> dataList = dao.getDataOfShangHai();
        if (dataList != null && !dataList.isEmpty()) {
            for (ResultVOPlus resultVOPlus : dataList) {
                System.out.println(
                        resultVOPlus.getKey() + "--" +
                                resultVOPlus.getValue1() + "--" +
                                resultVOPlus.getValue2() + "--" +
                                resultVOPlus.getValue3()
                );
            }
        }
        System.out.println(dataList.size());
    }

    @Test
    public void test12(){
        List<ResultVOPlus> dataList = dao.getDataOfHuBei();
        if (dataList != null && !dataList.isEmpty()) {
            for (ResultVOPlus resultVOPlus : dataList) {
                System.out.println(
                        resultVOPlus.getKey() + "--" +
                        resultVOPlus.getValue1() + "--" +
                        resultVOPlus.getValue2() + "--" +
                        resultVOPlus.getValue3()
                );
            }
        }
        System.out.println(dataList.size());
    }

    @Test
    public void test13(){
        Supplies supplies = new Supplies();
        supplies.setName("呼吸机");
        supplies.setPrice(48690);
        supplies.setCount(78);
        Date date = new Date();
        supplies.setMoney(48690 * 78);
        supplies.setAddtime(date);
        dao.insertSupplies(supplies);
    }



    @Test
    public void test14(){
        List<Supplies> list = dao.getSuppliesList();
        for (Supplies supplies : list) {
            System.out.println(supplies);
        }
    }

    @Test
    public void test15(){
        Supplies supplies = new Supplies();
        supplies.setName("呼吸机");
        supplies.setPrice(48690);
        supplies.setCount(12);
        Date date = new Date();
        supplies.setMoney(48690 * 12);
        supplies.setAddtime(date);
        dao.updateSupplies(supplies);
    }

}
