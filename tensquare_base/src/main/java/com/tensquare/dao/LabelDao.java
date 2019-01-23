package com.tensquare.dao;

import com.tensquare.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelDao extends JpaRepository<Label,String> {

}
