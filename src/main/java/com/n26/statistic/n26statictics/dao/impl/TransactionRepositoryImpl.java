package com.n26.statistic.n26statictics.dao.impl;

import java.time.Clock;
import java.time.Instant;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Repository;

import com.n26.statistic.n26statictics.dao.TransactionRepository;
import com.n26.statistic.n26statictics.dto.Transaction;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

	private Map<Transaction, Transaction> records = new ConcurrentHashMap<>();

	@Autowired
	private ThreadPoolTaskScheduler threadPoolTaskScheduler;

	@PostConstruct
	public void init() {
		Runnable runnable = () -> {
			Instant instant = Clock.systemUTC().instant().minusSeconds(60);
			long startTime = instant.toEpochMilli();
			Set<Transaction> keySet = records.keySet();
			for (Transaction transaction : keySet) {
				if (transaction.getTimestamp() < startTime) {
					records.remove(transaction);
				}
			}
		};
		PeriodicTrigger periodicTrigger = new PeriodicTrigger(1, TimeUnit.SECONDS);
		threadPoolTaskScheduler.schedule(runnable, periodicTrigger);
	}

	@Override
	public void createTransaction(Transaction transaction) {
		records.put(transaction, transaction);
	}

	@Override
	public Set<Transaction> getStatistic() {
		return records.keySet();
	}

}
