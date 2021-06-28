package com.kdh.exam.app.controller;

import java.util.Scanner;

import com.kdh.exam.app.Rq;
import com.kdh.exam.app.container.Container;
import com.kdh.exam.app.dto.Article;
import com.kdh.exam.app.service.ArticleService;
import com.kdh.exam.util.Util;

public class UsrArticleController extends Controller{
	private ArticleService articleService;
	private Scanner sc;

	public UsrArticleController() {
		articleService = Container.getArticleService();
		sc = Container.getSc();

		makeTestData();
	}

	private void makeTestData() {
		articleService.makeTestData();
	}
	
	@Override
	public void performAction(Rq rq) {
		if (rq.getActionPath().equals("/usr/article/write")) {
			actionWrite(rq);
		} else if (rq.getActionPath().equals("/usr/article/list")) {
			actionList(rq);
		} else if (rq.getActionPath().equals("/usr/article/detail")) {
			actionDetail(rq);
		} else if (rq.getActionPath().equals("/usr/article/delete")) {
			actionDelete(rq);
		} else if (rq.getActionPath().equals("/usr/article/modify")) {
			actionModify(rq);
		}
	}

	private void actionModify(Rq rq) {
		int id = rq.getIntParam("id", 0);

		if (id == 0) {
			System.out.println("id를 입력해주세요");
			return;
		}

		Article article = articleService.getArticleById(id);

		if (article == null) {
			System.out.println("존재하지 않는 게시물입니다.");
			return;
		}

		System.out.print("제목 : ");
		String title = sc.nextLine().trim();
		System.out.print("내용 : ");
		String body = sc.nextLine().trim();

		article.setUpdateDate(Util.getNowDateStr());
		article.setTitle(title);
		article.setBody(body);

		System.out.println(id + "번 게시물이 수정되었습니다.");
	}

	private void actionDelete(Rq rq) {
		int id = rq.getIntParam("id", 0);

		if (id == 0) {
			System.out.println("id를 입력해주세요");
			return;
		}

		Article article = articleService.getArticleById(id);

		if (article == null) {
			System.out.println("존재하지 않는 게시물입니다.");
			return;
		}

		articleService.deleteArticleById(id);

		System.out.println(id + "번 게시물이 삭제되었습니다.");
	}

	private void actionDetail(Rq rq) {
		int id = rq.getIntParam("id", 0);

		if (id == 0) {
			System.out.println("id를 입력해주세요");
			return;
		}
		Article article = articleService.getArticleById(id);

		if (article == null) {
			System.out.println("존재하지 않는 게시물입니다.");
			return;
		}

		System.out.println("번호 : " + article.getId());
		System.out.println("작성 날짜 : " + article.getRegDate());
		System.out.println("수정 날짜 : " + article.getUpdateDate());
		System.out.println("제목 : " + article.getTitle());
		System.out.println("내용 : " + article.getBody());
	}

	private void actionList(Rq rq) {
		System.out.println("번호 / 작성 날짜 / 제목");

		for (int i = articleService.getArticles().size() - 1; i >= 0; i--) {
			Article article = articleService.getArticles().get(i);
			System.out.println(article.getId() + " / " + article.getRegDate() + " / " + article.getTitle());
		}
	}

	private void actionWrite(Rq rq) {
		System.out.print("제목 : ");
		String title = sc.nextLine().trim();
		System.out.print("내용 : ");
		String body = sc.nextLine().trim();


		int id = articleService.write(title, body);
		
		System.out.println(id + "번 게시물이 생성되었습니다");
	}
}
