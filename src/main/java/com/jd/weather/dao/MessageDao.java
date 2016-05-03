package com.jd.weather.dao;

import java.util.List;

import com.jd.weather.model.Message;

public interface MessageDao {
	public List<Message> findAll();
	public Message findById(int id);
	public void addMessage(Message message);
	public void updateMessage(Message message);
	public void deleteMessage(int id);
	public List<Message> findByPaging(int currentPage,int pageSize);
	public int countAll();
}
