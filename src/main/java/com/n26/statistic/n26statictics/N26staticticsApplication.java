package com.n26.statistic.n26statictics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@SpringBootApplication
public class N26staticticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(N26staticticsApplication.class, args);
	}

	/**
	 * TaskScheduler to schedule the cleanup thread
	 * 
	 * @return {@link ThreadPoolTaskScheduler}
	 */
	@Bean
	public ThreadPoolTaskScheduler taskExecutor() {
		return new ThreadPoolTaskScheduler();
	}
}
