package com.kdh.exam.app.service;

import java.util.List;

import com.kdh.exam.app.container.Container;
import com.kdh.exam.app.dto.Member;
import com.kdh.exam.app.repository.MemberRepository;
import com.kdh.exam.util.Util;

public class MemberService {
	private MemberRepository memberRepository;

	public MemberService() {
		memberRepository = Container.getMemberRepository();
	}
	
	public Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}
	
	public List<Member> getMembers() {
		return memberRepository.getMembers();
	}

	public String join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		return memberRepository.join(loginId, loginPw, name, nickname, cellphoneNo, email);
	}

	public void makeTestData() {
		for (int i = 0; i < 2; i++) {
			join("user" + (i + 1), "user" + (i + 1), "user" + (i + 1), "user" + (i + 1), "user" + (i + 1), "user" + (i + 1));
		}
	}
}
