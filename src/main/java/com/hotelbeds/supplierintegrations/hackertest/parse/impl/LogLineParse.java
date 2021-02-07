package com.hotelbeds.supplierintegrations.hackertest.parse.impl;



import org.springframework.stereotype.Component;

import com.hotelbeds.supplierintegrations.hackertest.exception.LogLineParseException;
import com.hotelbeds.supplierintegrations.hackertest.model.LoginLine;
import com.hotelbeds.supplierintegrations.hackertest.parse.ParseToObject;
import com.hotelbeds.supplierintegrations.hackertest.util.LogAction;

@Component
public class LogLineParse implements ParseToObject<LoginLine, String>{

	@Override
	public LoginLine parseLoginLine(String logLine) {
		
		
		 if (null == logLine || logLine.isEmpty()) {
	            throw new LogLineParseException("Invalid log line is empty");
	        }
		 String[] logArray = logLine.split(",");
	        if (logArray.length != 4) {
	            throw new LogLineParseException("Invalid log line formatter");
	        }
	        try {
	        
	        return new LoginLine(logArray[0], Long.parseLong(logArray[1]), LogAction.valueOf(logArray[2]), logArray[3]);
	       }
	        catch (Exception e) 
	        {throw new LogLineParseException(e.toString());}
	        
	        
	
	}

}
