/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Entity superclass to hold shared attributes. Eg. deleteFlag...
 *
 * @author Mick_02
 */
@MappedSuperclass
public class AbstractEntityVO implements Serializable {
  @Id
  protected Long id;

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
}
