package com.n26.statistic.n26statictics.dao;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.n26.statistic.n26statictics.dao.impl.TransactionRepositoryImpl;
import com.n26.statistic.n26statictics.dto.Transaction;

@RunWith(MockitoJUnitRunner.class)
public class TransactionRepositoryImplTest {

	@InjectMocks
	private TransactionRepositoryImpl transactionRepositoryImpl;

	@Test
	public void testCreateTransaction() {
		transactionRepositoryImpl.createTransaction(new Transaction(12.3, 1));
		Set<Transaction> keySet = transactionRepositoryImpl.getStatistic();
		assertEquals(1, keySet.size());
	}

}
