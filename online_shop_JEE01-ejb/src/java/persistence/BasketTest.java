/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TODO MW_141004: Only simple class for test @Statefull not correct impl
 * 
 * @author Mick_02
 */
@Entity
@Table(name = "test_basket")
public class BasketTest extends AbstractEntityVO {
  @Column(name = "article_id")
  private Long artId;
  @Column(name = "article_price")
  private Double artPrice;
  @Column(name = "user_id")
  private Long userId;
  
  public BasketTest() {
  }
  public BasketTest(Article a) {
    artId = a.id;
    artPrice = a.getPrice();
    userId = 1L;
  }
  public BasketTest(Article a, User fakeUser) {
    artId = a.id;
    artPrice = a.getPrice();
    userId = fakeUser.id;
  }
  public Long getArtId() {
    return artId;
  }
  public void setArtId(Long artId) {
    this.artId = artId;
  }
  public Double getArtPrice() {
    return artPrice;
  }
  public void setArtPrice(Double artPrice) {
    this.artPrice = artPrice;
  }
  public Long getUserId() {
    return userId;
  }
  public void setUserId(Long userId) {
    this.userId = userId;
  }
  @Override
  public String toString() {
    return String.format("persistence.BasketTest[%d]: %d = %f", id, userId, artPrice);
  }
}
