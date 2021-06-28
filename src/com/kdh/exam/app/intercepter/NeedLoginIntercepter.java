package com.kdh.exam.app.intercepter;

import com.kdh.exam.app.Rq;

public class NeedLoginIntercepter implements Intercepter {

	@Override
	public boolean run(Rq rq) {
		if (rq.isLogined()) {
			return true;
		}

		switch (rq.getActionPath()) {
		case "/usr/article/write":
		case "/usr/article/delete":
		case "/usr/article/modify":
		case "/usr/member/logout":
			System.out.println("로그인 후 이용해주세요.");
			return false;
		}
		return true;
	}

}
