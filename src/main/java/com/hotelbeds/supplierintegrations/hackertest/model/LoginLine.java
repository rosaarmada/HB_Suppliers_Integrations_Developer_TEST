package com.hotelbeds.supplierintegrations.hackertest.model;

import com.hotelbeds.supplierintegrations.hackertest.util.LogAction;

import lombok.Data;

@Data
public class LoginLine {
	private String ip;
	private long date;
	private LogAction loginAction;
	private String username;

	public LoginLine(String ip, long date, LogAction loginAction, String username) {
		this.ip = ip;
		this.date = date;
		this.loginAction = loginAction;
		this.username = username;
	}

	/*
	 * For unit testing
	 */
	LoginLine() {

	}

}
