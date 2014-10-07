/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import interfaces.BasketServiceRemote;
import logic.BeanHelper;
import logic.Helper;
import logic.WrapperHelperFake;
import service.BasketBean;

/**
 *
 * @author Mick_02
 */
public class BasketController extends AbstractController {
  private BasketServiceRemote basketService; // = (BasketServiceRemote) BeanHelper.getBean(BasketBean.class, BasketServiceRemote.class);
  
  public BasketController() {
    basketService = WrapperHelperFake.INSTANCE.getWrapper();
  }
  
    @Override
  public Class getBean() {
    return BasketBean.class;
  }
  @Override
  public Class getRemote() {
    return BasketServiceRemote.class;
  }
  @Override
  public void setProxy(Object proxy) {
    basketService = (BasketServiceRemote) proxy;
  }

	public void allAction() throws ServletException, IOException {
    request.setAttribute("entitys", basketService.getAll());
    request.setAttribute("template", "article_all.jsp");
    forward("/view/standard.jsp");
  }
  public void addAction() throws ServletException, IOException {
    Long addArticleId = Long.valueOf(Helper.getFromRequest(request, Helper.Part.PARAMETER));
    basketService.insertNew(addArticleId);
    String cPath = request.getContextPath() + "/front/article/all";
    response.sendRedirect(cPath); //"${root}/front/article/all"
//		request.setAttribute("entity", new Article());
//		request.setAttribute("template", "article_form.jsp");
//		forward("/view/standard.jsp");
  }
  public void finishorderAction() throws ServletException, IOException {
    try {
      basketService.finishOrder();

    } catch (Exception e) {
      System.out.println("FinishExc");
    }

  }

  @Override
  public void indexAction() throws ServletException, IOException {
    request.setAttribute("template", "action_not_found.jsp");
    forward("/view/standard.jsp");
  }
}
