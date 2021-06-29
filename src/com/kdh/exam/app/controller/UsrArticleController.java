package com.kdh.exam.app.controller;

import java.util.List;
import java.util.Scanner;

import com.kdh.exam.app.Rq;
import com.kdh.exam.app.container.Container;
import com.kdh.exam.app.dto.Article;
import com.kdh.exam.app.dto.Board;
import com.kdh.exam.app.dto.Member;
import com.kdh.exam.app.service.ArticleService;
import com.kdh.exam.app.service.BoardService;
import com.kdh.exam.app.service.MemberService;
import com.kdh.exam.util.Util;

public class UsrArticleController extends Controller{

	private BoardService boardService;
	private MemberService memberService;
	private ArticleService articleService;
	private Scanner sc;

	public UsrArticleController() {
		boardService = Container.getBoardService();
		memberService = Container.getMemberService();
		articleService = Container.getArticleService();
		sc = Container.getSc();

		makeTestData();
	}

	private void makeTestData() {
		boardService.makeTestData();
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

		if(article.getMemberId() != rq.getLoginedMemberId()) {
			System.out.println("본인 게시물만 수정할 수 있습니다.");
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
		
		if(article.getMemberId() != rq.getLoginedMemberId()) {
			System.out.println("본인 게시물만 삭제할 수 있습니다.");
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
		int boardId = rq.getIntParam("boardId", 0);
		int page = rq.getIntParam("page", 1);
		String searchKeyword = rq.getStrParam("searchKeyword", "");
		
		int itemsInAPage = 10;
		 
		List<Article> articles = articleService.getFilteredArticles(boardId, page, itemsInAPage, searchKeyword);
		
		System.out.println("번호 / 작성 날짜 / 제목 / 작성자 / 게시판");
		for (Article article : articles) {
			Member member = memberService.getMemberById(article.getMemberId());
			Board board = boardService.getBoardById(article.getBoardId());
			System.out.println(article.getId() + " / " + article.getRegDate() + " / " + article.getTitle() + " / " + member.getNickname() + " / " + board.getName());
		}
	}

	private void actionWrite(Rq rq) {
		int boardId = rq.getIntParam("boardId", 0);
		if(boardId == 0) {
			System.out.println("게시판번호를 입력해주세요");
			return;
		}
		
		Board board = boardService.getBoardById(boardId);
		
		if(board == null) {
			System.out.println("존재하지 않는 게시판입니다");
			return;
		}
		System.out.println("== " + board.getName() + "게시판 글작성 ==");
		System.out.print("제목 : ");
		String title = sc.nextLine().trim();
		System.out.print("내용 : ");
		String body = sc.nextLine().trim();

		int id = articleService.write(boardId, rq.getLoginedMemberId(),title, body);
		
		System.out.println(id + "번 게시물이 생성되었습니다");
	}
}
