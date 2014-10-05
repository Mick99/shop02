/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import util.HashUtils;
import static util.LoggerHelper.*;

/**
 * TODO MW_141004: hash() + equals() incorrect auto implemention!
 * New impl not tested.
 * 
 * @author Mick_02
 */
@Entity
@NamedQueries({
  @NamedQuery(name = "Article.findAll",
          query = "select art from Article art order by art.id")
})
@Table(name = "article")
public class Article extends AbstractEntityVO implements Comparable<Article> {
  private static final long serialVersionUID = 1L;
  private String name;
  private Double price;

  public List<Article> findAll(EntityManager em) {
    return em.createNamedQuery("Article.findAll").getResultList();

  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Double getPrice() {
    return price;
  }
  public void setPrice(Double price) {
    this.price = price;
  }

  @Override
  public int hashCode() {
    int hash = 19;
    hash = HashUtils.calcHashCode(hash, id);
    hash = HashUtils.calcHashCode(hash, name);
    hash = HashUtils.calcHashCode(hash, price);
    return hash;
//		int hash = 0;
//		hash += (id != null ? id.hashCode() : 0);
//		return hash;
  }
  @Override
  public boolean equals(Object other) {
    if (other == null) {
      return false;
    }
    if (this == other) {
      return true;
    }
    if (getClass() != other.getClass()) {
      return false;
    }
    Article art = (Article) other;
    return compareTo(art) == 0;
  }
// 	public boolean equals(Object object) {
//		// TODO: Warning - this method won't work in the case the id fields are not set
//		if (!(object instanceof Article)) {
//			return false;
//		}
//		Article other = (Article) object;
//		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//			return false;
//		}
//		return true;
//	}
  @Override
  public String toString() {
    return String.format("persistence.Article[%d]: %s = %f", id, name, price);
  }
  @Override
  public int compareTo(Article other) {
    // javadocs for Comparable: Note that null is not an instance of any class, and e.compareTo(null) should throw a NullPointerException even
    // though e.equals(null) returns false.
    if (other == null) {
      excLog.error("Article == null");
      throw new NullPointerException();
    }
    int res = name.compareTo(other.name);
    if (res == 0) {
      res = price.compareTo(other.price);
    }
    return res;
  }
}
