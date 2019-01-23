package com.tensquare.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BaseExceptionHandler {
	//执行通知
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Result error(Exception e){
		//打印异常到控制台
		e.printStackTrace();
		return new Result(false,StatusCode.ERROR,"异常处理:"+e.getMessage());

	}
}
