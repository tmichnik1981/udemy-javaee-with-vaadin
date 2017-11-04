package com.udemy.callback;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@EntityListeners(value={ArticleListener.class})
@Entity
public class Article {

	@Id
	@GeneratedValue
	@Column(name="id")
	private int  articleId;
	
	@Column(name="article_name")
	private String articleName;
	
	
	private Date date;

	public Article() {
		
	}

	public Article(String articleName) {
		this.articleName = articleName;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
}
