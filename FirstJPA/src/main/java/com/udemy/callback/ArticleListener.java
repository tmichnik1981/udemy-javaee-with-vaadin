package com.udemy.callback;

import java.util.Date;

import javax.persistence.PostPersist;
import javax.persistence.PrePersist;

public class ArticleListener {

	@PrePersist
	public void beforePersist(Article article){
		article.setDate(new Date());
		System.out.println("Before persisting article...");
	}
	
	@PostPersist
	public void AfterPersist(Article article){
		System.out.println("After persisting article...");
	}
}
