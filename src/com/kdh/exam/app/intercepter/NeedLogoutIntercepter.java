package com.kdh.exam.app.intercepter;

import com.kdh.exam.app.Rq;

public class NeedLogoutIntercepter implements Intercepter {

	@Override
	public boolean run(Rq rq) {
		if (rq.isLogined() == false) {
			return true;
		}

		switch (rq.getActionPath()) {
		case "/usr/member/login":
		case "/usr/member/join":
			System.out.println("이미 로그인중입니다.");
			return false;
		}
		return true;
	}

}
