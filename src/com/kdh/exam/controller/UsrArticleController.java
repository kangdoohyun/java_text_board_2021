package com.kdh.exam.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kdh.exam.app.Rq;
import com.kdh.exam.app.container.Container;
import com.kdh.exam.app.dto.Article;
import com.kdh.exam.util.Util;

public class UsrArticleController {
	private List<Article> articles;
	private int articlesLastId;
	private Scanner sc;
	
	public UsrArticleController(){
		articles = new ArrayList<>();
		articlesLastId = 0;
		sc = Container.getSc();
		
		for(int i = 0; i < 10; i++) {
			int id = ++articlesLastId;
			articles.add(new Article(id, Util.getNowDateStr(), Util.getNowDateStr(), "제목_" + id, "제목_" + id));
			articlesLastId = id;
		}
	}
	public void performAction(Rq rq) {
		if(rq.getActionPath().equals("/usr/article/write")) {
			System.out.print("제목 : ");
			String title = sc.nextLine().trim();
			System.out.print("내용 : ");
			String body = sc.nextLine().trim();
			
			int id = ++articlesLastId ;
			
			articles.add(new Article(id, Util.getNowDateStr(), Util.getNowDateStr(), title, body));
			
			articlesLastId = id;
			
			System.out.println(id + "번 게시물이 생성되었습니다");
		}
		else if (rq.getActionPath().equals("/usr/article/list")) {
			System.out.println("번호 / 작성 날짜 / 제목");
			
			for (int i = articles.size() - 1; i >= 0; i--) {
				Article article = articles.get(i);
				System.out.println(article.getId() + " / " + article.getRegDate() + " / " + article.getTitle());
			}
		}
		else if (rq.getActionPath().equals("/usr/article/detail")) {
			int id = rq.getIntParam("id", 0);
			
			if (id == 0) {
				System.out.println("id를 입력해주세요");
				return;
			}
			Article article = getArticleById(id);
			
			if(article == null) {
				System.out.println("존재하지 않는 게시물입니다.");
				return;
			}
			
			System.out.println("번호 : " + article.getId());
			System.out.println("작성 날짜 : " + article.getRegDate());
			System.out.println("수정 날짜 : " + article.getUpdateDate());
			System.out.println("제목 : " + article.getTitle());
			System.out.println("내용 : " + article.getBody());
		}
		else if(rq.getActionPath().equals("/usr/article/delete")) {
			int id = rq.getIntParam("id", 0);
			
			if (id == 0) {
				System.out.println("id를 입력해주세요");
				return;
			}
			
			Article article = getArticleById(id);
			
			if(article == null) {
				System.out.println("존재하지 않는 게시물입니다.");
				return;
			}
			
			articles.remove(article);
			
			System.out.println(id + "번 게시물이 삭제되었습니다.");
		}
		else if(rq.getActionPath().equals("/usr/article/modify")) {
			int id = rq.getIntParam("id", 0);
			
			if (id == 0) {
				System.out.println("id를 입력해주세요");
				return;
			}
			
			Article article = getArticleById(id);
			
			if(article == null) {
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
	}
	private Article getArticleById(int id) {
		for (Article article : articles) {
			if(article.getId() == id) {
				return article;
			}
		}
		return null;
	}
}
