package com.n26.statistic.n26statictics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.n26.statistic.n26statictics.dto.Transaction;
import com.n26.statistic.n26statictics.exception.TransactionException;
import com.n26.statistic.n26statictics.service.TransactionService;

@RestController
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	/**
	 * Api exposed to create the Transaction entry.
	 * 
	 * @param {@link
	 * 			Transaction}
	 * @return {@link ResponseEntity} Status code - 201 if success or 204 if
	 *         failure.
	 * 
	 */
	@PostMapping(path = "/transactions", consumes = "application/json")
	public ResponseEntity<String> createTransaction(@RequestBody Transaction transaction) {
		try {
			transactionService.createTransaction(transaction);
		} catch (TransactionException e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
