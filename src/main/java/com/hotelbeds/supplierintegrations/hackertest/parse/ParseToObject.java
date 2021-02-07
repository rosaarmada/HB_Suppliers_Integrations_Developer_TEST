package com.hotelbeds.supplierintegrations.hackertest.parse;

import com.hotelbeds.supplierintegrations.hackertest.model.LoginLine;

public interface ParseToObject <T, U>{
	 LoginLine parseLoginLine(U logLine);
}
