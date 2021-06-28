package com.kdh.exam.app.container;

import java.util.Scanner;

import com.kdh.exam.app.controller.UsrArticleController;
import com.kdh.exam.app.controller.UsrMemberController;
import com.kdh.exam.app.controller.UsrSystemController;
import com.kdh.exam.app.session.Session;

import lombok.Getter;

public class Container {
	@Getter
	public static Scanner sc;
	@Getter
	public static Session session;
	
	@Getter
	public static UsrSystemController usrSystemController;
	@Getter
	public static UsrMemberController usrMemberController;
	@Getter
	public static UsrArticleController usrArticleController;
	
	static {
		sc = new Scanner(System.in);
		session = new Session();
		
		usrSystemController = new UsrSystemController();
		usrMemberController = new UsrMemberController();
		usrArticleController = new UsrArticleController();
	}
}
