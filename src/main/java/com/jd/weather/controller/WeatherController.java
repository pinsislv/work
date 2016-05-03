package com.jd.weather.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jd.weather.model.Weather;
import com.jd.weather.service.WeatherService;

@RestController
@RequestMapping(value="/weather")
public class WeatherController {
	@Autowired
	private WeatherService weatherService;
	@RequestMapping(value="/today")
	public Weather getWeather(){
		Weather weather = weatherService.findById(1);
		return weather;
	}
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Weather updateWeather(Weather weather){
		//1.更新
		weatherService.update(weather);
		//2.查询返回
		return weatherService.findById(weather.getId());
	}
	@RequestMapping(value="/own/today")
	public ModelAndView getOwnWeather(){
		Weather weather = weatherService.findById(1);
		ModelAndView result = new ModelAndView("/weather2", "model", weather);
		return result;
	}
	
	@RequestMapping(value="/today/yesterday")
	public List<Weather> getWeather2(){
		Weather weather = weatherService.findById(1);
		Weather yesterDay = new Weather();
		yesterDay.setFengli("111");
		yesterDay.setFengxiang("");
		yesterDay.setHigh("dfs");
		yesterDay.setId(2);
		List<Weather> result = new ArrayList<Weather>();
		result.add(weather);
		result.add(yesterDay);
		return result;
	}
	
}
