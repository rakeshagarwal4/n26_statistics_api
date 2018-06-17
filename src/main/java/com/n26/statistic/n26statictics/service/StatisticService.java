package com.n26.statistic.n26statictics.service;

import com.n26.statistic.n26statictics.dto.Response;

public interface StatisticService {

	/**
	 * Service method to get the Statistics of the transaction happened in last
	 * 60 seconds.
	 * 
	 * @return {@link Response}
	 */
	public Response getStatistics();

}
