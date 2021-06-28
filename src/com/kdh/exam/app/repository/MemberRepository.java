package com.kdh.exam.app.repository;

import java.util.ArrayList;
import java.util.List;

import com.kdh.exam.app.dto.Member;
import com.kdh.exam.util.Util;

public class MemberRepository {
	private List<Member> members;
	private int lastId;

	public MemberRepository() {
		members = new ArrayList<>();
		lastId = 0;
	}

	public Member getMemberByLoginId(String loginId) {
		for (Member member : members) {
			if (member.getLoginId().equals(loginId)) {
				return member;
			}
		}
		return null;
	}

	public List<Member> getMembers() {
		return members;
	}

	public String join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		int id = ++lastId;
		
		members.add(new Member(id, Util.getNowDateStr(), Util.getNowDateStr(), loginId, loginPw, name, nickname, cellphoneNo, email));
		
		lastId = id;
		
		return nickname;
	}
}
