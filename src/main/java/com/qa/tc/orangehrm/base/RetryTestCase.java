package com.qa.tc.orangehrm.base;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTestCase implements IRetryAnalyzer {
	
	Logger log = Logger.getLogger(RetryTestCase.class);

	private int maxRetry = 0;
	private int tryCnt = 0;

	@Override
	public boolean retry(ITestResult result) {
		if (!result.isSuccess()) {
			if (tryCnt < maxRetry) {
				tryCnt++;
				return true;
			} else
				return false;
		}
		return false;
	}

}
