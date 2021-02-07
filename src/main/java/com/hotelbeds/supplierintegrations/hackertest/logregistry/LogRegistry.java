package com.hotelbeds.supplierintegrations.hackertest.logregistry;

import java.util.List;

import com.hotelbeds.supplierintegrations.hackertest.model.LoginLine;

public interface LogRegistry {

	List<LoginLine> getListIpRegistry(LoginLine newLog);

}
