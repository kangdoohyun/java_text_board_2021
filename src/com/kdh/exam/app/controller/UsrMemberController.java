package com.kdh.exam.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kdh.exam.app.Rq;
import com.kdh.exam.app.container.Container;
import com.kdh.exam.app.dto.Article;
import com.kdh.exam.app.dto.Member;
import com.kdh.exam.util.Util;

public class UsrMemberController {
	private List<Member> members;
	private int membersLastId;
	private Scanner sc;

	public UsrMemberController() {
		members = new ArrayList<>();
		membersLastId = 0;
		sc = Container.getSc();

		makeTestData();
	}

	private void makeTestData() {
		for (int i = 0; i < 2; i++) {
			int id = ++membersLastId;
			members.add(new Member(id, Util.getNowDateStr(), Util.getNowDateStr(), "user" + id, "user" + id, "user" + id, "user" + id, "user" + id, "user" + id));
			membersLastId = id;
		}
	}

	public void performAction(Rq rq) {
		if (rq.getActionPath().equals("/usr/member/login")) {
			actionLogin(rq);
		}
	}


	private void actionLogin(Rq rq) {
		System.out.print("아이디 : ");
		String loginId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String loginPw = sc.nextLine();
		
		if(loginId.length() == 0) {
			System.out.println("아이디를 입력해 주세요.");
			return;
		}
		if(loginPw.length() == 0) {
			System.out.println("비밀번호를 입력해 주세요.");
			return;
		}
		
		Member member = getMemberByLoginId(loginId);
		
		if(member == null) {
			System.out.println("존재하지 않는 아이디입니다.");
			return;
		}
		if(!(member.getLoginPw().equals(loginPw))) {
			System.out.println("비밀번호를 확인해 주세요.");
			return;
		}
		
		rq.setSessionAttr("loginedMember", member);
		
		System.out.println(member.getNickname() + "님 환영합니다.");
	}

	private Member getMemberByLoginId(String loginId) {
		for (Member member : members) {
			if (member.getLoginId().equals(loginId)) {
				return member;
			}
		}
		return null;
	}
}
