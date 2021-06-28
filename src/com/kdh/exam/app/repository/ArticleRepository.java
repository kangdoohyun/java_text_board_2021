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

	public int write(String title, String body) {
		int id = ++lastId;

		articles.add(new Article(id, Util.getNowDateStr(), Util.getNowDateStr(), title, body));

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

}
