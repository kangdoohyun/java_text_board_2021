package com.kdh.exam.app.container;

import java.util.Scanner;

import com.kdh.exam.app.session.Session;

public class Container {
	public static Scanner sc;
	public static Session session;
	
	static {
		sc = new Scanner(System.in);
		session = new Session();	
	}

	public static Scanner getSc() {
		return sc;
	}

	public static Session getSession() {
		return session;
	}
}
