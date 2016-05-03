package com.jd.weather.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jd.weather.model.Message;
import com.jd.weather.model.Result;
import com.jd.weather.model.ro.PagingRequest;
import com.jd.weather.service.MessageService;



@RestController
@RequestMapping(value="/message")
public class MessageController {
	@Autowired
	private MessageService messageService;
	private static Logger logger=LoggerFactory.getLogger(MessageController.class);
	@RequestMapping(value="/index")
	public ModelAndView index(){
		logger.info("访问message首页info");
		logger.error("访问message首页error");
		logger.warn("访问message首页warn");
		logger.debug("访问message首页debug");
		return new ModelAndView("/message");
	}
	@RequestMapping(value="/all")
	public Result findAll(){
		return Result.success(messageService.findAll());
	}
	@RequestMapping(value="/page")
	public Result findAll(PagingRequest pagingRequest){
		return Result.success(messageService.findByPaging(pagingRequest.getCurrentPage(), pagingRequest.getCurrentPage()));
	}
	@RequestMapping(value="/find/{id}")
	public Result findById(@PathVariable("id")int id){
		Message message = messageService.findById(id);
		if(message == null){
			return Result.fail("相关消息不存在！请刷新后再试");
		}else{
			return Result.success(message);
		}
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result add(Message message){
		messageService.addMessage(message);
		return Result.success(null);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Result update(Message message){
		messageService.updateMessage(message);
		return Result.success(null);
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.POST)
	public Result delete(@PathVariable("id")int id){
		Message message = messageService.findById(id);
		if(message == null){
			return Result.fail("相关消息不存在！请刷新后再试");
		}else{
			messageService.deleteMessage(id);
			return Result.success(null);
		}
	}
	
}
