package com.n26.statistic.n26statictics.controller;

import static org.junit.Assert.assertEquals;

import java.time.Clock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.n26.statistic.n26statictics.dto.Transaction;
import com.n26.statistic.n26statictics.exception.TransactionException;
import com.n26.statistic.n26statictics.service.TransactionService;

@RunWith(MockitoJUnitRunner.class)
public class TransactionControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private TransactionController transactionController;

	@Mock
	private TransactionService transactionService;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
	}

	@Test
	public void testCreateTransactionNoContentStatus() throws Exception {
		Mockito.doThrow(TransactionException.class).when(transactionService).createTransaction(Mockito.any());
		Transaction transaction = new Transaction();
		transaction.setAmount(13.2);
		transaction.setTimestamp(1);
		ObjectMapper objectMapper = new ObjectMapper();
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/transactions")
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writer().writeValueAsString(transaction));
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
	}

	@Test
	public void testCreateTransactionCreateStatus() throws Exception {
		Mockito.doNothing().when(transactionService).createTransaction(Mockito.any());
		Transaction transaction = new Transaction();
		transaction.setAmount(13.2);
		transaction.setTimestamp(Clock.systemUTC().instant().toEpochMilli());
		ObjectMapper objectMapper = new ObjectMapper();
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/transactions")
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writer().writeValueAsString(transaction));
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}

}
