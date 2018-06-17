package com.n26.statistic.n26statictics.dao;

import java.util.Set;

import com.n26.statistic.n26statictics.dto.Transaction;

public interface TransactionRepository {

	/**
	 * Repository method to save the new Transaction.
	 * 
	 * @param transaction
	 */
	public void createTransaction(Transaction transaction);

	/**
	 * Repository method to get the statistics of the Transactions in last 60
	 * seconds.
	 * 
	 * @return
	 */
	public Set<Transaction> getStatistic();

}
