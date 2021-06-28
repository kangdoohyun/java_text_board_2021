package com.kdh.exam.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kdh.exam.app.dto.Article;
import com.kdh.exam.util.Util;

public class App {
	List<Article> articles;
	Scanner sc;
	int articlesLastId;
	
	App() {
		articles = new ArrayList<>();
		sc = new Scanner(System.in);
		articlesLastId = 0;
	}
	
	public void run() {
		System.out.println("== Java Text Board Start ==");
		
		
		for(int i = 0; i < 10; i++) {
			int id = ++articlesLastId;
			articles.add(new Article(id, Util.getNowDateStr(), Util.getNowDateStr(), "제목_" + id, "제목_" + id));
			articlesLastId = id;
		}
		
		while (true) {
			System.out.print("명령어 : ");
			String command = sc.nextLine().trim();
			
			Rq rq = new Rq(command);
			
			if(rq.isValid == false) {
				System.out.println("명령어가 올바르지 않습니다.");
				continue;
			}
			
			if(rq.getActionPath().equals("/usr/system/exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			
			else if(rq.getActionPath().equals("/usr/article/write")) {
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
					continue;
				}
				Article article = getArticleById(id);
				
				if(article == null) {
					System.out.println("존재하지 않는 게시물입니다.");
					continue;
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
					continue;
				}
				
				Article article = getArticleById(id);
				
				if(article == null) {
					System.out.println("존재하지 않는 게시물입니다.");
					continue;
				}
				
				articles.remove(article);
				
				System.out.println(id + "번 게시물이 삭제되었습니다.");
			}
			else if(rq.getActionPath().equals("/usr/article/modify")) {
				int id = rq.getIntParam("id", 0);
				
				if (id == 0) {
					System.out.println("id를 입력해주세요");
					continue;
				}
				
				Article article = getArticleById(id);
				
				if(article == null) {
					System.out.println("존재하지 않는 게시물입니다.");
					continue;
				}
				
				System.out.print("제목 : ");
				String title = sc.nextLine();
				System.out.print("내용 : ");
				String body = sc.nextLine();
				
				article.setUpdateDate(Util.getNowDateStr());
				article.setTitle(title);
				article.setBody(body);
				
				System.out.println(id + "번 게시물이 수정되었습니다.");
			}
		}
		System.out.println("== Java Text Board End ==");
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
