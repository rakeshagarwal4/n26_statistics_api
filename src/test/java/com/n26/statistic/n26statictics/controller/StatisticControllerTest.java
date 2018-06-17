package com.n26.statistic.n26statictics.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.n26.statistic.n26statictics.dto.Response;
import com.n26.statistic.n26statictics.service.StatisticService;

@RunWith(MockitoJUnitRunner.class)
public class StatisticControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private StatisticController statisticController;

	@Mock
	private StatisticService statisticService;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(statisticController).build();
	}

	@Test
	public void testGetStatistic() throws Exception {
		Response response = new Response(30, 15, 17, 13, 2);
		Mockito.when(statisticService.getStatistics()).thenReturn(response);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/statistics");
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse result = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), result.getStatus());
		ObjectMapper objectMapper = new ObjectMapper();
		String expected = objectMapper.writer().writeValueAsString(response);
		assertEquals(expected, result.getContentAsString());
	}

}
