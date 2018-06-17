package com.n26.statistic.n26statictics.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.n26.statistic.n26statictics.dao.TransactionRepository;
import com.n26.statistic.n26statictics.dto.Response;
import com.n26.statistic.n26statictics.dto.Transaction;
import com.n26.statistic.n26statictics.service.impl.StatisticServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class StatisticServiceImplTest {

	@InjectMocks
	private StatisticServiceImpl statisticServiceImpl;

	@Mock
	private TransactionRepository tranctionRepository;

	@Test
	public void testGetStatisticEmpty() {
		Mockito.when(tranctionRepository.getStatistic()).thenReturn(new HashSet<>());
		Response response = statisticServiceImpl.getStatistics();
		assertNotNull(response);
		assertEquals(0, response.getAvg(), 0.0);
		assertEquals(0, response.getMax(), 0.0);
		assertEquals(0, response.getMin(), 0.0);
		assertEquals(0, response.getSum(), 0.0);
		assertEquals(0, response.getCount());
	}

	@Test
	public void testGetStatisticNonEmpty() {
		Mockito.when(tranctionRepository.getStatistic()).thenReturn(getTransactions());
		Response response = statisticServiceImpl.getStatistics();
		assertNotNull(response);
		assertEquals(3, response.getAvg(), 0.0);
		assertEquals(3.5, response.getMax(), 0.0);
		assertEquals(2.2, response.getMin(), 0.0);
		assertEquals(9, response.getSum(), 0.0);
		assertEquals(3, response.getCount());
	}

	private Set<Transaction> getTransactions() {
		Set<Transaction> transactions = new HashSet<>();
		transactions.add(new Transaction(2.2, 123));
		transactions.add(new Transaction(3.3, 124));
		transactions.add(new Transaction(3.5, 125));
		return transactions;
	}

}
