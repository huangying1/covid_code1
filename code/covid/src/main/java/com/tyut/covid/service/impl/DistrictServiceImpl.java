package com.tyut.covid.service.impl;

import com.tyut.covid.bean.District;
import com.tyut.covid.dao.DistrictDao;
import com.tyut.covid.service.IDistrictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 全国地区数据业务层接口的实现类
 *
 */
@Service
public class DistrictServiceImpl implements IDistrictService {
	
	@Resource
	private DistrictDao districtDao;
	
	@Override
	public List<District> getByParent(String parent) {
		return findByParent(parent);	
	}
	
	@Override
	public District getByCode(String code) {
		return findByCode(code);
	}
	
	
	/**
	 * 根据父级代号获取 全国所有省、某省所有市、某市所有区 的列表
	 * @param parent 父级代号
	 * @return 全国所有省、某省所有市、某市所有区 的列表
	 */
	private List<District> findByParent(String parent){
		return districtDao.findByParent(parent);
	}
	
	
	/**
	 * 根据 code 查询地区信息
	 * @param code 省 / 市 / 区 的代号
	 * @return 地区信息
	 */
	private District findByCode(String code) {
		return districtDao.findByCode(code);
	}
	
	
}
