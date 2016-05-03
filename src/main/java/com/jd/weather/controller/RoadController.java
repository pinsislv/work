package com.jd.weather.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoadController {
	@RequestMapping(value={"/weather2","/weather3"})
	public String roadMessage(){
		return "大堵车";
	}
}

