package com.kdh.exam.app.dto;

public class Article {
	public int id;
	public String title;
	public String body;
	
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", body=" + body + "]";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
}
