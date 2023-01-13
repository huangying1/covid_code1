package com.tyut.covid.dao;

import com.tyut.covid.bean.District;

import java.util.List;

/**
 * 处理全国地区的持久层接口
 *
 */
public interface DistrictDao {
	
	/**
	 * 通过父级代号，获取全国所有省 / 某省所有市 / 某市所有区 的列表
	 * @param parent 父级代号
	 * @return 所有省 / 某省所有市 / 某市所有区 的列表
	 */
	List<District> findByParent(String parent);
	
	/**
	 * 根据 code 查询地区信息
	 * @param code 省 / 市 / 区 的代号
	 * @return 地区信息
	 */
	District findByCode(String code);
	
	
}
