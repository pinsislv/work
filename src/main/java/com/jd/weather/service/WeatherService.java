package com.jd.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jd.weather.dao.WeatherDao;
import com.jd.weather.model.Weather;

@Service
public class WeatherService {
	
	@Autowired
	private WeatherDao weatherDao;
	public void update(Weather weather){
		weatherDao.update(weather);
	}
	
	public Weather findById(int id){
		return weatherDao.findById(id);
	}
	
}
