package com.tensquare.service;

import com.tensquare.dao.LabelDao;
import com.tensquare.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import utis.IdWorker;

import java.util.List;

@Service
@Transactional
public class LabelService {

	@Autowired
	private LabelDao labelDao;
	@Autowired
	private IdWorker idWorker;
	//增  改
	public void saveAndUpdate(Label Label){
		if (StringUtils.isEmpty(Label.getId())){
			//id为空--->添加
			Label.setId(idWorker.nextId()+"");//生成并设置id
		}
			//id不为空-->修改
		labelDao.save(Label);//保存
	}
	//删  根据ID
	public  void delete(String labelId){
		labelDao.deleteById(labelId);
	}
	//查 根据ID
	public Label findById(String labelId){

		return labelDao.findById(labelId).get();
	}
	//查  全部
	public List<Label> findAll(){
		return labelDao.findAll();
	}
}
