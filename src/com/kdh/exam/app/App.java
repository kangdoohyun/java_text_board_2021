package com.kdh.exam.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kdh.exam.app.container.Container;
import com.kdh.exam.app.controller.Controller;
import com.kdh.exam.app.dto.Member;
import com.kdh.exam.app.intercepter.Intercepter;
import com.kdh.exam.app.intercepter.NeedLoginIntercepter;
import com.kdh.exam.app.intercepter.NeedLogoutIntercepter;
import com.kdh.exam.app.service.MemberService;

public class App {
	static Scanner sc;

	static {
		sc = Container.getSc();
	}

	public void run() {
		System.out.println("== Java Text Board Start ==");
		forTestLoginByMemberId(1);
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
			if(runIntercepters(rq) == false) {
				continue;
			}
			controller.performAction(rq);

			if (rq.getActionPath().equals("/usr/system/exit")) {
				break;
			}
		}
		System.out.println("== Java Text Board End ==");
	}
	
	private void forTestLoginByMemberId(int id) {
		Member member = Container.getMemberService().getMemberById(id);
		new Rq().login(member);		
	}

	private boolean runIntercepters(Rq rq) {
		List<Intercepter> intercepters = new ArrayList<>();
		
		intercepters.add(Container.getNeedLoginIntercepter());
		intercepters.add(Container.getNeedLogoutIntercepter());
		
		for(Intercepter intercepter : intercepters) {
			if(intercepter.run(rq) == false) {
				return false;
			}
		}
		return true;
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
