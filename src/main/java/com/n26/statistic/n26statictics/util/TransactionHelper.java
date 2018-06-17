package com.n26.statistic.n26statictics.util;

import java.time.Clock;
import java.time.Instant;

public class TransactionHelper {

	/**
	 * Utility method to check passed time in millisecond is not less than
	 * current time - 60 seconds.
	 * 
	 * @param transactionTime
	 * @return
	 * 
	 *         <pre>
	{@link Boolean.TRUE} - passed time is >= (current time - 60 seconds). <br/> 
	{@link Boolean.FALSE} - passed time is < (current time - 60 seconds).
	 *         </pre>
	 */
	public static boolean isTransactionInTime(long transactionTime) {
		Instant instant = Clock.systemUTC().instant();
		instant = instant.minusSeconds(60);
		if (transactionTime < instant.toEpochMilli()) {
			return false;
		}
		return true;
	}

}
