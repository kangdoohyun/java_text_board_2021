package com.kdh.exam.app;

import java.util.Scanner;

import com.kdh.exam.app.container.Container;
import com.kdh.exam.app.controller.UsrArticleController;

public class App {
	static Scanner sc;
	
	static {
		sc = Container.getSc();
	}
	
	public void run() {
		System.out.println("== Java Text Board Start ==");
		
		UsrArticleController usrArticleController = new UsrArticleController();
		
		while (true) {
			System.out.print("명령어 : ");
			String command = sc.nextLine().trim();
			
			Rq rq = new Rq(command);
			
			if(rq.isValid == false) {
				System.out.println("명령어가 올바르지 않습니다.");
				continue;
			}
			
			if(rq.getControllerTypeCode().equals("usr")) {
				usrArticleController.performAction(rq);
			}
			else if(rq.getActionPath().equals("/usr/system/exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
		}
		System.out.println("== Java Text Board End ==");
	}

	
}
