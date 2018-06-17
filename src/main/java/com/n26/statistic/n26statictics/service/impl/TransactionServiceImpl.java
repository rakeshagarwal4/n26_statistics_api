package com.n26.statistic.n26statictics.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n26.statistic.n26statictics.dao.TransactionRepository;
import com.n26.statistic.n26statictics.dto.Transaction;
import com.n26.statistic.n26statictics.exception.TransactionException;
import com.n26.statistic.n26statictics.service.TransactionService;
import com.n26.statistic.n26statictics.util.TransactionHelper;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository tranctionRepository;

	@Override
	public void createTransaction(Transaction transaction) throws TransactionException {
		if (!TransactionHelper.isTransactionInTime(transaction.getTimestamp())) {
			throw new TransactionException("Transaction time before 60 seconds");
		}

		tranctionRepository.createTransaction(transaction);

	}

}
