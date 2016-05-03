package com.jd.weather.dao;

import com.jd.weather.model.Weather;

public interface WeatherDao {
	public Weather findById(int id);
	
	public void update(Weather weather);
}
