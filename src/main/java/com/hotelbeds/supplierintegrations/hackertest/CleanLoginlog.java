package com.hotelbeds.supplierintegrations.hackertest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hotelbeds.supplierintegrations.hackertest.logregistry.impl.LogRegistryImpl;

@Component
public class CleanLoginlog {
	private static final Logger logger = LoggerFactory
			.getLogger(CleanLoginlog.class);
	@Autowired
	private LogRegistryImpl ipReg;

	@Scheduled(cron = "1 * * * * ?")
	public void cleanLogRegistryImpl() {
		logger.info("jobDelete cleanLogRegistryImpl start");
		ipReg.jobDelete();

	}

}
