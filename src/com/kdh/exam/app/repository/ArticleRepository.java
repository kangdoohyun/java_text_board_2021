package com.kdh.exam.app.repository;

import java.util.ArrayList;
import java.util.List;

import com.kdh.exam.app.dto.Article;
import com.kdh.exam.util.Util;

public class ArticleRepository {
	private List<Article> articles;
	private int lastId;

	public ArticleRepository() {
		articles = new ArrayList<>();
		lastId = 0;
	}

	public int write(int boardId, int memberId, String title, String body) {
		int id = ++lastId;

		articles.add(new Article(id, Util.getNowDateStr(), Util.getNowDateStr(), boardId, memberId, title, body));

		lastId = id;

		return id;
	}

	public Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void deleteArticleById(int id) {
		Article article = getArticleById(id);

		if (article != null) {
			articles.remove(article);
		}
	}

	public List<Article> getFilteredArticles(int boardId, int page, int itemsInAPage, String searchKeyword) {
		List<Article> filteredArticles1 = getBoardIdFilteredArticles(articles, boardId);
		List<Article> filteredArticles2 = getSearchKeywordFilteredArticles(filteredArticles1, searchKeyword);
		List<Article> filteredArticles3 = getPageFilteredArticles(filteredArticles2, page, itemsInAPage);
		return filteredArticles3;
	}

	private List<Article> getSearchKeywordFilteredArticles(List<Article> articles, String searchKeyword) {
		if(searchKeyword.length() == 0) {
			return articles;
		}
		
		List<Article> filteredArticles = new ArrayList<>();
		
		for(Article article : articles) {
			if(article.getTitle().contains(searchKeyword)) {
				filteredArticles.add(article);
			}
		}
		
		return filteredArticles;
	}

	private List<Article> getPageFilteredArticles(List<Article> articles, int page, int itemsInAPage) {
		List<Article> filteredArticles = new ArrayList<>();
		
		int jumpArticlesIndex = (page - 1) * itemsInAPage;
		int startIndex = articles.size() - jumpArticlesIndex - 1;
		System.out.println(startIndex);
		int endIndex = startIndex - itemsInAPage + 1;
		System.out.println(endIndex);
		
		if(endIndex < 0) {
			endIndex = 0;
		}
		
		for(int start = startIndex; start >= endIndex; start--) {
			filteredArticles.add(articles.get(start));
		}
		
		return filteredArticles;
	}

	private List<Article> getBoardIdFilteredArticles(List<Article> articles, int boardId) {
		if(boardId == 0) {
			return articles;
		}
		
		List<Article> filteredArticles = new ArrayList<>();
		
		for(Article article : articles) {
			if(article.getBoardId() == boardId) {
				filteredArticles.add(article);
			}
		}
		return filteredArticles;
	}

}
