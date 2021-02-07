package com.hotelbeds.supplierintegrations.hackertest.logregistry.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hotelbeds.supplierintegrations.hackertest.logregistry.LogRegistry;
import com.hotelbeds.supplierintegrations.hackertest.model.LoginLine;

@Service
public class LogRegistryImpl implements LogRegistry {
	private static final Logger logger = LoggerFactory.getLogger(LogRegistry.class);

	public Map<String, List<LoginLine>> ipRegistry = new HashMap<>();

	@Override
	public List<LoginLine> getListIpRegistry(LoginLine newLog) {

		List<LoginLine> aux = ipRegistry.get(newLog.getIp());
		if (CollectionUtils.isEmpty(aux)) {
			aux = new ArrayList<>();
			aux.add(newLog);

		} else {
			aux.add(newLog);
			aux = deleteOld(aux, newLog.getDate());
		}

		ipRegistry.put(newLog.getIp(), aux);
		return aux;
	}

	private List<LoginLine> deleteOld(List<LoginLine> aux, Long lastDate) {
		logger.debug("delete records older than 5 min");
		aux.removeIf(x -> ((lastDate - x.getDate()) >= 300000));
		return aux;
	}

	public void jobDelete() {
		this.ipRegistry.forEach((k, v) -> deleteOld(v, new Date().getTime()));

	}

}
