package com.tyut.covid.controller;

import com.tyut.covid.bean.District;
import com.tyut.covid.service.IDistrictService;
import com.tyut.covid.util.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * 地区数据的控制器类
 *
 */
@RestController
@RequestMapping("/districts/")
public class DistrictController extends BaseController {
	
	@Resource
	private IDistrictService service;
	
	@GetMapping("/")
	public JsonResult<List<District>> getByParent(String parent){
		List<District> list = service.getByParent(parent);
		return new JsonResult<List<District>>(SUCCESS, list);
	}
	
	
}
