package com.jd.weather.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.jd.weather.dao.MessageDao;
import com.jd.weather.model.Message;

@Repository
public class MessageDaoImpl implements MessageDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Message> findAll() {
		String sql = "select * from t_message order by id desc";
		final List<Message> messages = new ArrayList<Message>();
		jdbcTemplate.query(sql, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Message message = new Message();
				message.setContent(rs.getString("content"));
				message.setCreatedDate(rs.getDate("createdDate"));
				message.setId(rs.getInt("id"));
				message.setTitle(rs.getString("title"));
				messages.add(message);
			}
		});
		return messages;
	}

	@Override
	public Message findById(int id) {
		String sql = "select * from t_message where id= ?";
		final Message message = new Message();
		jdbcTemplate.query(sql, new Object[] { id }, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				message.setContent(rs.getString("content"));
				message.setCreatedDate(rs.getDate("createdDate"));
				message.setId(rs.getInt("id"));
				message.setTitle(rs.getString("title"));
			}
		});
		if (message.getId() <= 0) {
			return null;
		} else {
			return message;
		}
	}

	public void addMessage(Message message) {
		String sql = "insert into t_message(title,content,createdDate) values(?,?,?)";
		Object[] params = new Object[]{message.getTitle(),message.getContent(),new Date()};
		jdbcTemplate.update(sql, params);
	}

	public void updateMessage(Message message) {
		String sql = "update t_message t set t.title = ? , t.content = ? where id = ?";
		Object[] params = new Object[]{message.getTitle(),message.getContent(),message.getId()};
		jdbcTemplate.update(sql, params);

	}

	public void deleteMessage(int id) {
		String sql ="delete from t_message where id = ?";
		jdbcTemplate.update(sql, new Object[]{id});

	}

	@Override
	public List<Message> findByPaging(int currentPage, int pageSize) {
		String sql="select * from t_message order by id desc limit ?,?";
		Object[] params=new Object[]{(currentPage-1)*pageSize,pageSize};
		final List<Message> messages=new ArrayList<Message>();
		jdbcTemplate.query(sql, params, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				Message message=new Message();
				message.setContent(rs.getString("content"));
				message.setCreatedDate(rs.getDate("createdDate"));
				message.setId(rs.getInt("id"));
				message.setTitle(rs.getString("title"));
				messages.add(message);
			}
		});
		return messages;
	}

	@Override
	public int countAll() {
		String sql="select count(*) from t_message";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

}
