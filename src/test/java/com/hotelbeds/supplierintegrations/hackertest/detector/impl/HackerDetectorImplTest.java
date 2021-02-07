package com.hotelbeds.supplierintegrations.hackertest.detector.impl;


import static com.hotelbeds.supplierintegrations.hackertest.logregistry.impl.LogRegistryImplTest.ipRegistry;
import static com.hotelbeds.supplierintegrations.hackertest.logregistry.impl.LogRegistryImplTest.ipRegistry5;
import static  com.hotelbeds.supplierintegrations.hackertest.parse.impl.LogLineParseTest.buildLoginLine;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hotelbeds.supplierintegrations.hackertest.logregistry.LogRegistry;
import com.hotelbeds.supplierintegrations.hackertest.model.LoginLine;
import com.hotelbeds.supplierintegrations.hackertest.parse.ParseToObject;
import com.hotelbeds.supplierintegrations.hackertest.util.LogAction;
@RunWith(MockitoJUnitRunner.class)
public class HackerDetectorImplTest {
	
	private String logLineSuccessStr="80.238.9.179,133612947,SIGNIN_SUCCESS,Will.Smith";
	public Map<String, List<LoginLine>> ipRegistry = new HashMap<>();
	private static String ipTest="80.238.9.179";

	
	private static LoginLine loginLineFailureObj = new LoginLine("80.238.9.179", 133612947, LogAction.SIGNIN_FAILURE,"Will.Smith");
	private String logLineFailureStr="80.238.9.179,133612947,SIGNIN_FAILURE,Will.Smith";
	private static LoginLine loginLineFailureObjLess5Min = new LoginLine("80.238.9.179", 133912946, LogAction.SIGNIN_FAILURE,"Will.Smith");
	private String logLineFailureStrLess5Min="80.238.9.179,133912946,SIGNIN_FAILURE,Will.Smith";
	private static LoginLine loginLineFailureObjMore5Min = new LoginLine("80.238.9.179", 133912947, LogAction.SIGNIN_FAILURE,"Will.Smith");
	private String logLineFailureStrMore5Min="80.238.9.179,133912947,SIGNIN_FAILURE,Will.Smith";
	
	@InjectMocks
    private HackerDetectorImpl hackerDetector;
	
    @Mock
    private ParseToObject<LoginLine, String> parseLog;
    
    @Mock
	private LogRegistry ipReg;
    
	@Test
	public void HackerDetectorLogSuccess() {
		given(parseLog.parseLoginLine(logLineSuccessStr)).willReturn(buildLoginLine(logLineSuccessStr));
        assertNull(hackerDetector.parseLine(logLineSuccessStr));	
        }
	
	@Test
	public void HackerDetectorLogFailure() {
		given(parseLog.parseLoginLine(logLineFailureStr)).willReturn(buildLoginLine(logLineFailureStr));
        assertNull(hackerDetector.parseLine(logLineFailureStr));	
        }
	
	@Test
	public void HackerDetectorLogFailure5() {
		
		given(parseLog.parseLoginLine(logLineFailureStrLess5Min)).willReturn(buildLoginLine(logLineFailureStrLess5Min));
		given(ipReg.getListIpRegistry(loginLineFailureObjLess5Min)).willReturn(ipRegistry5(loginLineFailureObjLess5Min));
		assertThat(hackerDetector.parseLine(logLineFailureStrLess5Min).equals(ipTest), is(true));
		
		given(parseLog.parseLoginLine(logLineFailureStrMore5Min)).willReturn(buildLoginLine(logLineFailureStrMore5Min));
		given(ipReg.getListIpRegistry(loginLineFailureObjMore5Min)).willReturn(ipRegistry(loginLineFailureObjMore5Min));
		assertNull(hackerDetector.parseLine(logLineFailureStrMore5Min));
        
		given(parseLog.parseLoginLine(logLineFailureStr)).willReturn(buildLoginLine(logLineFailureStr));
		given(ipReg.getListIpRegistry(loginLineFailureObj)).willReturn(ipRegistry(loginLineFailureObj));
		assertNull(hackerDetector.parseLine(logLineFailureStr));
	}
	
}
