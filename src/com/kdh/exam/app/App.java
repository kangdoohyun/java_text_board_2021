package com.kdh.exam.app;

import java.util.Scanner;

import com.kdh.exam.app.container.Container;
import com.kdh.exam.app.controller.UsrArticleController;
import com.kdh.exam.app.controller.UsrMemberController;
import com.kdh.exam.app.dto.Member;
import com.kdh.exam.app.session.Session;

public class App {
	static Scanner sc;
	
	static {
		sc = Container.getSc();
	}
	
	public void run() {
		System.out.println("== Java Text Board Start ==");
		
		UsrMemberController usrMemberController = new UsrMemberController();
		UsrArticleController usrArticleController = new UsrArticleController();
		Session session = Container.getSession();
		
		while (true) {
			Member loginedMember = (Member) session.getAttribute("loginedMember");
			
			String prompt = "명령어";
			
			if(loginedMember != null) {
				prompt = loginedMember.getNickname();
			}
			System.out.print(prompt + " : ");
			
			String command = sc.nextLine().trim();
			
			Rq rq = new Rq(command);
			
			if(rq.isValid == false) {
				System.out.println("명령어가 올바르지 않습니다.");
				continue;
			}
			
			if(rq.getControllerTypeCode().equals("usr")) {
				if(rq.getControllerName().equals("system")) {
					if(rq.getActionMethodName().equals("exit")) {
						System.out.println("프로그램을 종료합니다.");
						break;
					}
				}
				else if(rq.getControllerName().equals("article")) {
					usrArticleController.performAction(rq);
				}
				else if(rq.getControllerName().equals("member")) {
					usrMemberController.performAction(rq);
				}
			}
		}
		System.out.println("== Java Text Board End ==");
	}

	
}
