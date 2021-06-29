package com.kdh.exam.app.service;

import java.util.List;

import com.kdh.exam.app.container.Container;
import com.kdh.exam.app.dto.Article;
import com.kdh.exam.app.repository.ArticleRepository;

public class ArticleService {
	private ArticleRepository articleRepository;

	public ArticleService() {
		articleRepository = Container.getArticleRepository();
	}

	public int write(int boardId, int memberId, String title, String body) {
		return articleRepository.write(boardId, memberId, title, body);
	}

	public Article getArticleById(int id) {
		return articleRepository.getArticleById(id);
	}

	public List<Article> getArticles() {
		return articleRepository.getArticles();
	}

	public void deleteArticleById(int id) {
		articleRepository.deleteArticleById(id);
	}

	public void makeTestData() {
		for (int i = 0; i < 100; i++) {
			write(i % 2 + 1, i % 2 + 1, "제목_" + (i + 1), "제목_" + (i + 1));
		}
	}

	public List<Article> getFilteredArticles(int boardId, int page, int itemsInAPage, String searchKeyword) {
		return articleRepository.getFilteredArticles(boardId, page, itemsInAPage, searchKeyword);
	}

}
