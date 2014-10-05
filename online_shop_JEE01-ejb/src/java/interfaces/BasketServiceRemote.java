/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import persistence.User;

/**
 *
 * @author Mick_02
 */
public interface BasketServiceRemote extends BasketService {
  void insertNew(Long articleId, User user);
  void insertNew(Long articleId); // DEL
  void finishOrder();
}
