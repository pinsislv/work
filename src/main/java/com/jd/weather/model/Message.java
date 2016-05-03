package com.jd.weather.model;

import java.util.Date;

/**
 * 消息实体
 * @author fly
 * @version v1.0
 * @date 2016年4月21日上午10:24:33
 */
public class Message {
	private String title;
	private String content;
	private int id;
	private Date createdDate;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
