package com.kdh.exam.app;

import java.util.Scanner;

import com.kdh.exam.app.container.Container;
import com.kdh.exam.app.controller.Controller;
import com.kdh.exam.app.dto.Member;
import com.kdh.exam.app.session.Session;

public class App {
	static Scanner sc;

	static {
		sc = Container.getSc();
	}

	public void run() {
		System.out.println("== Java Text Board Start ==");

		Session session = Container.getSession();

		while (true) {
			Member loginedMember = (Member) session.getAttribute("loginedMember");

			String prompt = "명령어";

			if (loginedMember != null) {
				prompt = loginedMember.getNickname();
			}
			System.out.print(prompt + " : ");

			String command = sc.nextLine().trim();

			Rq rq = new Rq(command);

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
