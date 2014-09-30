/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import persistence.Article;

/**
 *
 * @author Mick_02
 */
public interface ArticleService {
	List<Article> getAll();
	void insertNew(Article article);
	void updateExist(Article article);
	
}
