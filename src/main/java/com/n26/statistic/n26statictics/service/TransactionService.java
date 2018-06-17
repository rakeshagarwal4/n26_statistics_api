package com.n26.statistic.n26statictics.service;

import com.n26.statistic.n26statictics.dto.Transaction;
import com.n26.statistic.n26statictics.exception.TransactionException;

public interface TransactionService {

	/**
	 * This method create a new Transaction if transaction time is less than 60
	 * seconds from current time.
	 * 
	 * @param transaction
	 * @throws TransactionException
	 *             If Transaction time is before 60 seconds from the current
	 *             time.
	 */
	public void createTransaction(Transaction transaction) throws TransactionException;

}
