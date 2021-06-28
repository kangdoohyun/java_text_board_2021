package com.kdh.exam.app.controller;

import java.util.List;
import java.util.Scanner;

import com.kdh.exam.app.Rq;
import com.kdh.exam.app.container.Container;
import com.kdh.exam.app.dto.Member;
import com.kdh.exam.app.service.MemberService;

public class UsrMemberController extends Controller {
	private MemberService memberService;
	private Scanner sc;

	public UsrMemberController() {
		memberService = Container.getMemberService();
		sc = Container.getSc();

		makeTestData();
	}

	private void makeTestData() {
		memberService.makeTestData();
	}
	
	@Override
	public void performAction(Rq rq) {
		if (rq.getActionPath().equals("/usr/member/login")) {
			actionLogin(rq);
		}
		else if(rq.getActionPath().equals("/usr/member/logout")) {
			actionLogout(rq);
		}
		else if(rq.getActionPath().equals("/usr/member/join")) {
			actionJoin(rq);
		}
	}


	private void actionJoin(Rq rq) {
		System.out.print("아이디 : ");
		String loginId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String loginPw = sc.nextLine();
		System.out.print("이름 : ");
		String name = sc.nextLine();
		System.out.print("닉네임 : ");
		String nickname = sc.nextLine();
		System.out.print("전화번호 : ");
		String cellphoneNo = sc.nextLine();
		System.out.print("이메일 : ");
		String email = sc.nextLine();
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if(member != null) {
			System.out.println("중복된 아이디 입니다.");
			return;
		}
		
		String joinMemberNickname = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		
		System.out.println(joinMemberNickname + "님 환영합니다");
	}

	private void actionLogout(Rq rq) {
		if(rq.isLogined()) {
			rq.logout();
			System.out.println("로그아웃 되었습니다.");
			return;
		}
		System.out.println("로그인 후 이용해주세요.");
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
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if(member == null) {
			System.out.println("존재하지 않는 아이디입니다.");
			return;
		}
		if(!(member.getLoginPw().equals(loginPw))) {
			System.out.println("비밀번호를 확인해 주세요.");
			return;
		}
		
		rq.login(member);
		
		System.out.println(member.getNickname() + "님 환영합니다.");
	}
}
