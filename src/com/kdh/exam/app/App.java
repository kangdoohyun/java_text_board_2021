package com.kdh.exam.app;

import java.util.Scanner;

import com.kdh.exam.app.container.Container;
import com.kdh.exam.app.controller.Controller;
import com.kdh.exam.app.dto.Member;

public class App {
	static Scanner sc;

	static {
		sc = Container.getSc();
	}

	public void run() {
		System.out.println("== Java Text Board Start ==");

		while (true) {
			String prompt = "명령어";
			
			Rq rq = new Rq();
			
			if (rq.isLogined()) {
				Member loginedMember = rq.getLoginedMember();
				prompt = loginedMember.getNickname();
			}
			System.out.print(prompt + " : ");

			String command = sc.nextLine().trim();

			rq.setCommand(command);

			if (rq.isValid == false) {
				System.out.println("명령어가 올바르지 않습니다.");
				continue;
			}

			Controller controller = getControllerByRequestUri(rq);

			controller.performAction(rq);

			if (rq.getActionPath().equals("/usr/system/exit")) {
				break;
			}
		}
		System.out.println("== Java Text Board End ==");
	}

	private Controller getControllerByRequestUri(Rq rq) {
		switch (rq.getControllerTypeCode()) {
		case "usr":
			switch (rq.getControllerName()) {
			case "system":
				return Container.getUsrSystemController();
			case "article":
				return Container.getUsrArticleController();
			case "member":
				return Container.getUsrMemberController();
			}
			break;
		}

		return null;
	}

}
