package com.hotelbeds.supplierintegrations.hackertest.detector.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelbeds.supplierintegrations.hackertest.CleanLoginlog;
import com.hotelbeds.supplierintegrations.hackertest.detector.HackerDetector;
import com.hotelbeds.supplierintegrations.hackertest.logregistry.LogRegistry;
import com.hotelbeds.supplierintegrations.hackertest.model.LoginLine;
import com.hotelbeds.supplierintegrations.hackertest.parse.ParseToObject;
import com.hotelbeds.supplierintegrations.hackertest.util.LogAction;

@Service
public class HackerDetectorImpl implements HackerDetector {
	private static final Logger logger = LoggerFactory.getLogger(CleanLoginlog.class);

	@Autowired
	private ParseToObject<LoginLine, String> parseLog;
	@Autowired
	private LogRegistry ipReg;

	@Override
	public String parseLine(String line) {

		LoginLine logLine = parseLog.parseLoginLine(line);

		if (logLine.getLoginAction().equals(LogAction.SIGNIN_FAILURE)) {
			List<LoginLine> reg = ipReg.getListIpRegistry(logLine);

			logger.info("List for IP:{} es {}", logLine.getIp(), reg.toString());
			if (reg.size() >= 5) {
				logger.warn("SUSPICIOUS IP {} with {} attempts in the last 5 min", logLine.getIp(), reg.size());
				return logLine.getIp();
			}
		}

		return null;

	}

}
