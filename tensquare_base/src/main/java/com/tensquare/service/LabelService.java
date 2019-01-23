package com.tensquare.service;

import com.sun.org.apache.regexp.internal.RE;
import com.tensquare.dao.LabelDao;
import com.tensquare.pojo.Label;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import utis.IdWorker;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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

	//按照条件查询

	private Specification<Label> create(Label label){

		return new Specification<Label>() {
			@Override
			public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cd) {
					/*
						"id": "string",
						"labelname": "string",
						"state": "string",
						"count": 0,
						"recommend": "string"
					*/
					List<Predicate> Plist=new ArrayList<>();
					//判断当前条件是否为空
				if (!StringUtils.isEmpty(label.getId())){
					Plist.add(cd.equal(root.get("id").as(String.class), label.getId()));
				}
				if (!StringUtils.isEmpty(label.getLabelname())){
					Plist.add(cd.like(root.get("labelname").as(String.class), "%"+label.getLabelname()+"%"));
				}
				if (!StringUtils.isEmpty(label.getState())){
					Plist.add(cd.equal(root.get("state").as(String.class), label.getState()));
				}
				if (!StringUtils.isEmpty(label.getCount())){
					Plist.add(cd.equal(root.get("count").as(Integer.class), label.getCount()));
				}
				if (!StringUtils.isEmpty(label.getRecommend())){
					Plist.add(cd.equal(root.get("recommend").as(String.class), label.getRecommend()));
				}
				//拼接条件
				return cd.and(Plist.toArray(new Predicate[Plist.size()]));
			}
		};
	}

	//按照条件查询,简单方法 List<Label>
	public List<Label> findSearch(Label label){
		//指定labelname属性 使用 模糊(contains)查询
		ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("labelname",m -> m.contains());
		//自动根据传入label对象判断空,根据什么条件查询
		return labelDao.findAll(Example.of(label,matcher));
	}

	//按照条件查询+分页    Page<Label>
	public Page<Label> findSearchPage(Integer page, Integer size, Label label){
		//指定labelname属性 使用 模糊(contains)查询
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("labelname",m -> m.contains());
		//自动根据传入label对象判断,根据什么条件查询
		return labelDao.findAll(Example.of(label,matcher), PageRequest.of(page-1,size));
	}
}
