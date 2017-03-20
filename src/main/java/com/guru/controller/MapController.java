package com.guru.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guru.model.BusStation;
import com.guru.service.IMapService;
import com.guru.service.MapServiceImpl;


@Controller
@RequestMapping(value="/map")
public class MapController {
//	@Autowired
	public static IMapService serviceMap;
	
	private static final Logger logger = LoggerFactory.getLogger(MapController.class);
	
	@RequestMapping(method=RequestMethod.GET)
	public String home(Model model){
		serviceMap= new MapServiceImpl();
		List<BusStation> busStations= serviceMap.loadDummyMarker();
		model.addAttribute("busStations",busStations);
		return "map";
	}
	
	@RequestMapping(value="/direction",method=RequestMethod.GET)
	public String directionDummy(Model model){
		serviceMap= new MapServiceImpl();
		List<BusStation> busStations= serviceMap.loadDummyMarker();
		model.addAttribute("busStations",busStations);
		return "direction_ajax";
	}
	
	@RequestMapping(value="/direction/ajax",method=RequestMethod.GET)
	public @ResponseBody String directionAjax(@RequestParam(value="busRoute")String route,
												@RequestParam(value="trend") String trend){
		serviceMap= new MapServiceImpl();
		logger.info(route);
		String reponseJson="";
		reponseJson=serviceMap.findBusRoute(route,trend);
		return reponseJson;
	}
	
	
}
