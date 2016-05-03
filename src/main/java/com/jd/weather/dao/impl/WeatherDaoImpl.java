package com.jd.weather.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.jd.weather.dao.WeatherDao;
import com.jd.weather.model.Weather;
@Repository
public class WeatherDaoImpl implements WeatherDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Weather findById(int id) {
		final Weather weather = new Weather();
		String sql = "select * from t_weather where id = ?";
		Object[] params = new Object[]{id};
		//
		jdbcTemplate.query(sql, params, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				weather.setFengli(rs.getString("fengli"));
				weather.setFengxiang(rs.getString("fengxiang"));
				weather.setHigh(rs.getString("high"));
				weather.setLow(rs.getString("low"));
				weather.setType(rs.getString("type"));
			}
		});
		//1 
		return weather;
	}
	@Override
	public void update(Weather weather) {
		String sql="update t_weather t set t.fengli = ? ,"
				+ "t.fengxiang = ?,t.high = ?,t.low= ?,"
				+ "t.type = ? where t.id = ?;";
		Object[] params = new Object[]{weather.getFengli(),weather.getFengxiang(),
							weather.getHigh(),weather.getLow(),weather.getType(),weather.getId()};
		jdbcTemplate.update(sql, params);
	}

}
