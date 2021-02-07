package com.hotelbeds.supplierintegrations.hackertest.logregistry.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import com.hotelbeds.supplierintegrations.hackertest.model.LoginLine;
import com.hotelbeds.supplierintegrations.hackertest.util.LogAction;

public class LogRegistryImplTest {

	private static String ipTest = "80.238.9.179";
	private static LogRegistryImpl logRegistry = new LogRegistryImpl();

	private static LoginLine loginLineFailureObj = new LoginLine("80.238.9.179", 133612947, LogAction.SIGNIN_FAILURE,
			"Will.Smith");

	@Test
	public void LogRegistryTest() {
		List<LoginLine> lisTestObject = logRegistry.getListIpRegistry(loginLineFailureObj);
		assertThat(lisTestObject.size(), is(1));
		assertThat(lisTestObject.get(0).equals(loginLineFailureObj), is(true));
		assertThat(lisTestObject.get(0).getIp().equals(ipTest), is(true));

	}

	public static List<LoginLine> ipRegistry(LoginLine loginLineFailureObjr) {
		List<LoginLine> lisTestObject = logRegistry.getListIpRegistry(loginLineFailureObjr);
		return lisTestObject;
	}

	public static List<LoginLine> ipRegistry5(LoginLine loginLineFailureObjr) {
		List<LoginLine> lisTestObject = logRegistry.getListIpRegistry(loginLineFailureObj);
		lisTestObject = logRegistry.getListIpRegistry(loginLineFailureObj);
		lisTestObject = logRegistry.getListIpRegistry(loginLineFailureObj);
		lisTestObject = logRegistry.getListIpRegistry(loginLineFailureObj);
		lisTestObject = logRegistry.getListIpRegistry(loginLineFailureObjr);
		return lisTestObject;
	}

}
