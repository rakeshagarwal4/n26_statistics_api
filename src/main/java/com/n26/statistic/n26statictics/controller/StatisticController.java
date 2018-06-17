package com.n26.statistic.n26statictics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.n26.statistic.n26statictics.dto.Response;
import com.n26.statistic.n26statictics.service.StatisticService;

@RestController
public class StatisticController {

	@Autowired
	private StatisticService statisticService;

	/**
	 * Api exposed to get the statistics of the transactions created in last 60
	 * seconds.
	 * 
	 * @return {@link ResponseEntity}
	 */
	@GetMapping(path = "/statistics", produces = "application/json")
	public ResponseEntity<Response> statistics() {
		return ResponseEntity.ok(statisticService.getStatistics());
	}

}
