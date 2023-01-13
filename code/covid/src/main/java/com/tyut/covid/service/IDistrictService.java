package com.tyut.covid.service;

import com.tyut.covid.bean.District;

import java.util.List;

/**
 * 全国地区数据的业务层接口
 *
 */
public interface IDistrictService {
	
	/**
	 * 根据父级代号获取 全国所有省、某省所有市、某市所有区 的列表
	 * @param parent 父级代号
	 * @return 全国所有省、某省所有市、某市所有区 的列表
	 */
	List<District> getByParent(String parent);
	
	/**
	 * 根据 code 查询地区信息
	 * @param code 
	 * @return 地区信息
	 */
	District getByCode(String code);
	
}
