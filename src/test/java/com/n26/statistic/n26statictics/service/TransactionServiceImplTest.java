package com.n26.statistic.n26statictics.service;

import static org.mockito.Mockito.times;

import java.time.Clock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.n26.statistic.n26statictics.dao.TransactionRepository;
import com.n26.statistic.n26statictics.dto.Transaction;
import com.n26.statistic.n26statictics.exception.TransactionException;
import com.n26.statistic.n26statictics.service.impl.TransactionServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceImplTest {

	@InjectMocks
	private TransactionServiceImpl transactionServiceImpl;

	@Mock
	private TransactionRepository transactionRepository;

	@Test(expected = TransactionException.class)
	public void testCreateTransactionException() throws TransactionException {
		Transaction transaction = new Transaction(12.3, 1);
		transactionServiceImpl.createTransaction(transaction);
	}

	@Test()
	public void testCreateTransaction() throws TransactionException {
		Transaction transaction = new Transaction(13.3, Clock.systemUTC().instant().toEpochMilli());
		Mockito.doNothing().when(transactionRepository).createTransaction(Mockito.any());
		transactionServiceImpl.createTransaction(transaction);
		Mockito.verify(transactionRepository,times(1)).createTransaction(transaction);
	}

}
