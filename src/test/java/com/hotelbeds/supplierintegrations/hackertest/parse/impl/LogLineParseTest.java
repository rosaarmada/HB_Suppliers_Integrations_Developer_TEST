package com.hotelbeds.supplierintegrations.hackertest.parse.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import com.hotelbeds.supplierintegrations.hackertest.exception.LogLineParseException;
import com.hotelbeds.supplierintegrations.hackertest.model.LoginLine;
import com.hotelbeds.supplierintegrations.hackertest.parse.ParseToObject;
import com.hotelbeds.supplierintegrations.hackertest.parse.impl.LogLineParse;
import com.hotelbeds.supplierintegrations.hackertest.util.LogAction;

public class LogLineParseTest {

	private String logLineSuccessStr = "80.238.9.179,133612947,SIGNIN_SUCCESS,Will.Smith";
	private String logLineFailureStr = "80.238.9.179,133612947,SIGNIN_FAILURE,Will.Smith";
	private LoginLine loginLineSuccessObj = new LoginLine("80.238.9.179", 133612947, LogAction.SIGNIN_SUCCESS,
			"Will.Smith");
	private LoginLine loginLineFailureObj = new LoginLine("80.238.9.179", 133612947, LogAction.SIGNIN_FAILURE,
			"Will.Smith");

	
	private static ParseToObject<LoginLine, String> parseLog = new LogLineParse();

	@Test(expected = LogLineParseException.class)
	public void LogLineParseNull() {
		parseLog.parseLoginLine(null);
	}

	@Test(expected = LogLineParseException.class)
	public void LogLineParseEmpty() {
		parseLog.parseLoginLine("");
	}

	@Test(expected = LogLineParseException.class)
	public void LogLineParseInvalid() {
		parseLog.parseLoginLine("xxx,x");
	}

	@Test(expected = LogLineParseException.class)
	public void LogLineParseInvalidFormat() {
		parseLog.parseLoginLine("80.238.9.179,133612947,SIGIN,Will.Smith");
	}

	@Test
	public void LogLineParseSuccess() {
		LoginLine testObject = parseLog.parseLoginLine(logLineSuccessStr);
		assertThat(loginLineSuccessObj.equals(testObject), is(true));
	}

	@Test
	public void LogLineParseFailure() {
		LoginLine testObject = parseLog.parseLoginLine(logLineFailureStr);
		assertThat(loginLineFailureObj.equals(testObject), is(true));
	}
	
	
		//private static LogLineParse parseLog = new LogLineParse();

		public static LoginLine buildLoginLine(String line) {
			return parseLog.parseLoginLine(line);
		}

}
