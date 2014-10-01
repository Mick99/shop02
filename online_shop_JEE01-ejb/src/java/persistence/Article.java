/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;

/**
 *
 * @author Mick_02
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Article.findAll",
					query = "select art from Article art order by art.id")
})
@Table(name = "article")
public class Article implements Serializable {

//	@PersistenceContext(unitName = "online_shop_JEE01-ejbPU")
//	EntityManager em;
	
	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	private String name;
	private double price;

	public List<Article> findAll(EntityManager em) {
		return em.createNamedQuery("Article.findAll").getResultList();
		
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Article)) {
			return false;
		}
		Article other = (Article) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return String.format("persistence.Article[%d]: %s = %f%n", id, name, price);
	}

}
