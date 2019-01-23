package com.tensquare.pojo;

/*
* id	string
标签ID

labelname	string
标签名称

state	string
状态

count	integer($int32)
recommend	string
是否推荐
*/

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="tb_label")
public class Label {

	@Id
	private String id;//标签ID
	private String labelname;//标签名称
	private String state;//状态
	private Integer count;//使用数量
	private String recommend;//是否推荐

	public Label() {
	}

	public Label(String id, String labelname, String state, Integer count, String recommend) {
		this.id = id;
		this.labelname = labelname;
		this.state = state;
		this.count = count;
		this.recommend = recommend;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabelname() {
		return labelname;
	}

	public void setLabelname(String labelname) {
		this.labelname = labelname;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
}
