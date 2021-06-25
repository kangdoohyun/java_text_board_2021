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
		
		for(int i = 0; i < 10; i++) {
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
			
			Rq rq = new Rq(command);
			
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
			else if (rq.getActionPath().equals("/usr/article/list")) {
				System.out.println("번호 / 작성 날짜 / 제목");
				
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.println(article.getId() + " / " + article.getRegDate() + " / " + article.getTitle());
				}
			}
			else if (rq.getActionPath().equals("/usr/article/detail")) {
				String queryStr = "";
				try {
					queryStr = command.split("\\?", 2)[1];
				}
				catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("쿼리를 입력해 주세요");
					continue;
				}
				
				String[] queryStrBits = queryStr.split("&");
				int id = 0;
				for (String queryStrBit : queryStrBits) {
					String[] queryStrBitsBits = queryStrBit.split("=", 2);
					String paramName = queryStrBitsBits[0];
					String paramValue = queryStrBitsBits[1];
					
					if(paramName.equals("id")) {
						id = Integer.parseInt(paramValue);
					}
				}
				if (id == 0) {
					System.out.println("id를 입력해주세요");
					continue;
				}
				
				Article foundArticle = null;
				
				for (Article article : articles) {
					if(article.getId() == id) {
						foundArticle = article;
						break;
					}
				}
				
				if(foundArticle == null) {
					System.out.println("존재하지 않는 게시물입니다.");
					return;
				}
				
				System.out.println("번호 : " + foundArticle.getId());
				System.out.println("작성 날짜 : " + foundArticle.getRegDate());
				System.out.println("수정 날짜 : " + foundArticle.getUpdateDate());
				System.out.println("제목 : " + foundArticle.getTitle());
				System.out.println("내용 : " + foundArticle.getBody());
			}
			else if(rq.getActionPath().equals("/usr/article/delete")) {
				String queryStr = "";
				try {
					queryStr = command.split("\\?", 2)[1];
				}
				catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("쿼리를 입력해 주세요");
					continue;
				}
				
				String[] queryStrBits = queryStr.split("&");
				int id = 0;
				for (String queryStrBit : queryStrBits) {
					String[] queryStrBitsBits = queryStrBit.split("=", 2);
					String paramName = queryStrBitsBits[0];
					String paramValue = queryStrBitsBits[1];
					
					if(paramName.equals("id")) {
						id = Integer.parseInt(paramValue);
					}
				}
				if (id == 0) {
					System.out.println("id를 입력해주세요");
					continue;
				}
				
				Article foundArticle = null;
				
				for (Article article : articles) {
					if(article.getId() == id) {
						foundArticle = article;
						break;
					}
				}
				
				if(foundArticle == null) {
					System.out.println("존재하지 않는 게시물입니다.");
					return;
				}
				
				articles.remove(foundArticle);
				
				System.out.println(id + "번 게시물이 삭제되었습니다.");
			}
			else if(rq.getActionPath().equals("/usr/article/modify")) {
				String queryStr = "";
				try {
					queryStr = command.split("\\?", 2)[1];
				}
				catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("쿼리를 입력해 주세요");
					continue;
				}
				
				String[] queryStrBits = queryStr.split("&");
				int id = 0;
				for (String queryStrBit : queryStrBits) {
					String[] queryStrBitsBits = queryStrBit.split("=", 2);
					String paramName = queryStrBitsBits[0];
					String paramValue = queryStrBitsBits[1];
					
					if(paramName.equals("id")) {
						id = Integer.parseInt(paramValue);
					}
				}
				if (id == 0) {
					System.out.println("id를 입력해주세요");
					continue;
				}
				
				Article foundArticle = null;
				
				for (Article article : articles) {
					if(article.getId() == id) {
						foundArticle = article;
						break;
					}
				}
				
				if(foundArticle == null) {
					System.out.println("존재하지 않는 게시물입니다.");
					return;
				}
				
				System.out.print("제목 : ");
				String title = sc.nextLine();
				System.out.print("내용 : ");
				String body = sc.nextLine();
				
				foundArticle.setUpdateDate(Util.getNowDateStr());
				foundArticle.setTitle(title);
				foundArticle.setBody(body);
				
				System.out.println(id + "번 게시물이 수정되었습니다.");
			}
		}
		System.out.println("== Java Text Board End ==");
	}
}
