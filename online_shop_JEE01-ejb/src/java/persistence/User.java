/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import javax.persistence.Entity;
import javax.persistence.Table;
import util.HashUtils;
import static util.LoggerHelper.*;

/**
 *  TODO MW_141004: hash() + equals() incorrect auto implemention!
 *  New impl not tested.
 * 
 * @author Mick_02
 */
@Entity
@Table(name = "usertbl")
public class User extends AbstractEntityVO implements Comparable<User> {
  private static final long serialVersionUID = 1L;
  private String username;
  // Dont use this fake(only test): Role must own entity as one user has many roles. 
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
    int hash = 37;
    hash = HashUtils.calcHashCode(hash, id);
    hash = HashUtils.calcHashCode(hash, username);
    return hash;
//    int hash = 0;
//    hash += (id != null ? id.hashCode() : 0);
//    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof User)) {
      return false;
    }
    User other = (User) object;
//    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//      return false;
//    }
    return compareTo(other) == 0;
  }
  @Override
  public String toString() {
    return String.format("persistence.User[%d]: %s", id, username);
  }
 @Override
  public int compareTo(User other) {
    // javadocs for Comparable: Note that null is not an instance of any class, and e.compareTo(null) should throw a NullPointerException even
    // though e.equals(null) returns false.
    if (other == null) {
      excLog.error("Article == null");
      throw new NullPointerException();
    }
    int res = username.compareTo(other.username);
    return res;
  }
}
