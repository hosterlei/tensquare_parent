package com.tensquare.controller;

import com.tensquare.pojo.Label;
import com.tensquare.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/label")
@CrossOrigin //跨域处理
public class LabelController {

	@Autowired
	private LabelService labelService;

//增加标签
	@RequestMapping(method = RequestMethod.POST)
	public Result save(@RequestBody Label label){
		labelService.saveAndUpdate(label);
		return new Result(
				true,
				StatusCode.OK,
				"保存成功"
		);
	}

//全部标签
	@RequestMapping(method = RequestMethod.GET)
	public Result findAll(){
		List<Label> all = labelService.findAll();
		return new Result(
				true,
				StatusCode.OK,
				"查询成功",
				all
		);
	}

//根据id查询标签
	@RequestMapping(value = "/{labelId}",method = RequestMethod.GET)
	public Result findById(@PathVariable String labelId){
		Label label = labelService.findById(labelId);
		return new Result(
				true,
				StatusCode.OK,
				"查询成功",
				label
		);
	}

//修改
	@RequestMapping(value = "/{labelId}",method = RequestMethod.PUT)
	public Result update(@PathVariable String labelId,@RequestBody Label label){
		//参数放入实体类
		label.setId(labelId);
		//调用修改
		labelService.saveAndUpdate(label);
		return new Result(
				true,
				StatusCode.OK,
				"更新修改成功"
		);
	}
//删除
	@RequestMapping(value = "/{labelId}",method = RequestMethod.DELETE)
	public Result delete(@PathVariable String labelId){
		//调用删除
		labelService.delete(labelId);
		return new Result(
				true,
				StatusCode.OK,
				"删除成功"
		);
	}

//查询
@RequestMapping(value = "search",method = RequestMethod.POST)
	public Result findSearch(@RequestBody Label label){
	List<Label> list = labelService.findSearch(label);
	return new Result(
			true,
			StatusCode.OK,
			"查询成功",
			list
		);
	}
//查询
	@RequestMapping(value="/search/{page}/{size}",method = RequestMethod.POST)
	public Result findSearchPage(@RequestBody  Label label,@PathVariable Integer page,@PathVariable Integer size){
		Page<Label> pageList = labelService.findSearchPage(page,size,label);
		return new Result(
				true,
				StatusCode.OK,
				"查询成功",
				new PageResult<Label>(pageList.getTotalElements(),pageList.getContent())
		);
	}
}
