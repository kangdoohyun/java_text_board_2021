package com.kdh.exam.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kdh.exam.app.dto.Article;

public class App {
	
	static int articlesLastId = 0;
	
	static List<Article> articles = new ArrayList<>();
	
	public static void run() {
		System.out.println("== Java Text Board Start ==");
		
		Scanner sc = new Scanner(System.in);
		
		
		while (true) {
			System.out.print("명령어 : ");
			String command = sc.nextLine().trim();
			
			if(command.equals("usr/system/exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			else if(command.equals("usr/article/write")) {
				System.out.print("제목 : ");
				String title = sc.nextLine().trim();
				System.out.print("내용 : ");
				String body = sc.nextLine().trim();
				int id = addArticle(title, body);
				System.out.println(id + "번 게시물이 생성되었습니다");
			}
			else if (command.equals("usr/article/list")) {
				System.out.println("번호 / 제목 / 내용");
				
				for (Article article : articles) {
					System.out.println(article.id + " / " + article.title + " / " + article.body);
				}
			}
		}
		System.out.println("== Java Text Board End ==");
	}
	
	public static int addArticle(String title, String body) {
		int id = ++articlesLastId ;
		Article article = new Article();
		
		article.setId(id);
		article.title = title;
		article.body = body;
		
		articles.add(article);
		articlesLastId = id;
		
		return id;
	}

}
