package com.jd.weather.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jd.weather.dao.MessageDao;
import com.jd.weather.model.Message;
import com.jd.weather.model.vo.MessagePaging;

@Service
public class MessageService {
	@Autowired
	private MessageDao messageDao;
	public List<Message> findAll(){
		return messageDao.findAll();
	}
	public Message findById(int id){
		return messageDao.findById(id);
	}
	
	public void addMessage(Message message){
		messageDao.addMessage(message);
	}
	
	public void updateMessage(Message message){
		messageDao.updateMessage(message);
	}
	
	public void deleteMessage(int id){
		messageDao.deleteMessage(id);
	}
	public MessagePaging findByPaging(int currentPage,int pageSize){
		MessagePaging paging=new MessagePaging();
		//获取总记录数
		int total=messageDao.countAll();
		paging.setPageSize(pageSize);
		paging.setTotal(total);
		//获取总的页数
		int pageCount=(total-1)/pageSize+1;
		int returnCurrPage=currentPage>pageCount?pageCount:currentPage;
		paging.setMessages(messageDao.findByPaging(returnCurrPage, pageSize));
		paging.setCurrentPage(returnCurrPage);
		paging.setPageCount(pageCount);
		return paging;
	}
}
