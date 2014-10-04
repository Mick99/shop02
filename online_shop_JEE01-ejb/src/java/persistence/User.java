/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *  TODO MW_141004: hash() + equals() incorrect auto implemention!
 * 
 * @author Mick_02
 */
@Entity
@Table(name = "user")
public class User extends AbstractEntityVO {
  private static final long serialVersionUID = 1L;
  private String username;
  private String userrole;

  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getUserrole() {
    return userrole;
  }
  public void setUserrole(String userrole) {
    this.userrole = userrole;
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
    if (!(object instanceof User)) {
      return false;
    }
    User other = (User) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }
  @Override
  public String toString() {
    return String.format("persistence.User[%d]: %s", id, username);
  }
}
