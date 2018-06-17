package com.n26.statistic.n26statictics.exception;

public class TransactionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9118460999419132980L;

	public TransactionException() {
		super();
	}

	public TransactionException(String message) {
		super(message);
	}

	public TransactionException(Throwable throwable) {
		super(throwable);
	}

	public TransactionException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
