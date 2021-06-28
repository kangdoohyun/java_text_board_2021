package com.kdh.exam.app.container;

import java.util.Scanner;

import com.kdh.exam.app.controller.UsrArticleController;
import com.kdh.exam.app.controller.UsrMemberController;
import com.kdh.exam.app.controller.UsrSystemController;
import com.kdh.exam.app.intercepter.NeedLoginIntercepter;
import com.kdh.exam.app.intercepter.NeedLogoutIntercepter;
import com.kdh.exam.app.repository.ArticleRepository;
import com.kdh.exam.app.repository.MemberRepository;
import com.kdh.exam.app.service.ArticleService;
import com.kdh.exam.app.service.MemberService;
import com.kdh.exam.app.session.Session;

import lombok.Getter;

public class Container {
	@Getter
	private static Scanner sc;
	@Getter
	private static Session session;
	
	@Getter
	private static MemberService memberService;
	@Getter
	private static MemberRepository memberRepository;
	@Getter
	private static ArticleService articleService;
	@Getter
	private static ArticleRepository articleRepository;
	
	@Getter
	private static NeedLoginIntercepter needLoginIntercepter;
	@Getter
	private static NeedLogoutIntercepter needLogoutIntercepter;
	
	@Getter
	private static UsrSystemController usrSystemController;
	@Getter
	private static UsrMemberController usrMemberController;
	@Getter
	private static UsrArticleController usrArticleController;
	
	static {
		sc = new Scanner(System.in);
		session = new Session();
		
		memberRepository = new MemberRepository();
		memberService = new MemberService();
		
		articleRepository = new ArticleRepository();
		articleService = new ArticleService();
		
		needLoginIntercepter = new NeedLoginIntercepter();
		needLogoutIntercepter = new NeedLogoutIntercepter();
		
		usrSystemController = new UsrSystemController();
		usrMemberController = new UsrMemberController();
		usrArticleController = new UsrArticleController();
	}
}
