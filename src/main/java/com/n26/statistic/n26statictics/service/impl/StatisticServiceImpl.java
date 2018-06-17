package com.n26.statistic.n26statictics.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n26.statistic.n26statictics.dao.TransactionRepository;
import com.n26.statistic.n26statictics.dto.Response;
import com.n26.statistic.n26statictics.dto.Transaction;
import com.n26.statistic.n26statictics.service.StatisticService;

@Service
public class StatisticServiceImpl implements StatisticService {

	@Autowired
	private TransactionRepository tranctionRepository;

	@Override
	public Response getStatistics() {
		Set<Transaction> transactions = tranctionRepository.getStatistic();
		if (!transactions.isEmpty()) {
			long count = transactions.size();
			double sum = transactions.parallelStream().mapToDouble(Transaction::getAmount).sum();
			double avg = sum / count;
			double min = transactions.parallelStream().mapToDouble(Transaction::getAmount).min().getAsDouble();
			double max = transactions.parallelStream().mapToDouble(Transaction::getAmount).max().getAsDouble();
			Response response = new Response(sum, avg, max, min, count);
			return response;
		}
		return new Response();
	}

}
