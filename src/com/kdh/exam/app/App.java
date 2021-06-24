package com.kdh.exam.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kdh.exam.app.dto.Article;
import com.kdh.exam.util.Util;

public class App {	
	public static void run() {
		System.out.println("== Java Text Board Start ==");
		int articlesLastId = 0;
		
		List<Article> articles = new ArrayList<>();
		
		Scanner sc = new Scanner(System.in);
		
		for(int i = 0; i <= 10; i++) {
			int id = ++articlesLastId;
			String regDate = Util.getNowDateStr();
			String updateDate = Util.getNowDateStr();
			Article article = new Article();
			
			article.setId(id);
			article.setRegDate(regDate);
			article.setUpdateDate(updateDate);
			article.setTitle("제목_" + id);
			article.setBody("내용_" + id);
			
			articles.add(article);
			articlesLastId = id;
		}
		while (true) {
			System.out.print("명령어 : ");
			String command = sc.nextLine().trim();
			
			if(command.equals("/usr/system/exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			else if(command.equals("/usr/article/write")) {
				System.out.print("제목 : ");
				String title = sc.nextLine().trim();
				System.out.print("내용 : ");
				String body = sc.nextLine().trim();
				
				int id = ++articlesLastId ;
				String regDate = Util.getNowDateStr();
				String updateDate = Util.getNowDateStr();
				Article article = new Article();
				
				article.setId(id);
				article.setRegDate(regDate);
				article.setUpdateDate(updateDate);
				article.setTitle(title);
				article.setBody(body);
				
				articles.add(article);
				articlesLastId = id;
				
				System.out.println(id + "번 게시물이 생성되었습니다");
			}
			else if (command.equals("/usr/article/list")) {
				System.out.println("번호 / 작성 날짜 / 제목");
				
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.println(article.getId() + " / " + article.getRegDate() + " / " + article.getTitle());
				}
			}
		}
		System.out.println("== Java Text Board End ==");
	}
}
