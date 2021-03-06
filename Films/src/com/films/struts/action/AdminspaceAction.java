/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.films.struts.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.films.domain.Area;
import com.films.domain.Orders;
import com.films.domain.Sort;
import com.films.domain.Users;
import com.films.service.inter.IAreaService;
import com.films.service.inter.IFilmService;
import com.films.service.inter.IOrderService;
import com.films.service.inter.ISortService;
import com.films.service.inter.ITimeTableService;
import com.films.service.inter.IUserService;

/** 
 * MyEclipse Struts
 * Creation date: 10-12-2012
 * 
 * XDoclet definition:
 * @struts.action parameter="flag"
 */
public class AdminspaceAction extends DispatchAction {
	
	private IFilmService filmService;
	private IUserService userService;
	private IAreaService areaService;	
	private ISortService sortService;
	private ITimeTableService timeTableService;
	private IOrderService orderService;
	
	
	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}
	public void setFilmService(IFilmService filmService) {
		this.filmService = filmService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public void setTimeTableService(ITimeTableService timeTableService) {
		this.timeTableService = timeTableService;
	}
	public void setAreaService(IAreaService areaService) {
		this.areaService = areaService;
	}	
	public void setSortService(ISortService sortService) {
		this.sortService = sortService;
	}

	public ActionForward goAdminUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Users user = (Users) request.getSession().getAttribute("loginUser");
		if(user!=null&&user.getUrole().equals("admin")){
			return mapping.findForward("goAdminUI");
		}else{	
			return mapping.findForward("logout");
		}
	}
	
	public ActionForward goMovieUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Users user = (Users) request.getSession().getAttribute("loginUser");
		if(user!=null&&user.getUrole().equals("admin")){
			//split page
			String s_pageNow=request.getParameter("pageNow");
			int pageNow=1;
			if(s_pageNow!=null){
				pageNow=Integer.parseInt(s_pageNow);
			}
			request.setAttribute("now", pageNow);
			request.setAttribute("film", filmService.showFilms(10, pageNow));
			int pageCount=filmService.getPageCount(10);
			request.setAttribute("pageCount", pageCount);
			return mapping.findForward("goMovieCenterUI");
		}else{	
			return mapping.findForward("logout");
		}
	}
	
	public ActionForward goPostUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Users user = (Users) request.getSession().getAttribute("loginUser");
		if(user!=null&&user.getUrole().equals("admin")){
			List<Area> list1 = areaService.getAreas();
			List<Sort> list2 = sortService.getSort();
			if(list1.size()!=0){
				request.setAttribute("area", list1);				
			}	
			if(list2.size()!=0){
				request.setAttribute("sort", list2);				
			}
			return mapping.findForward("goPostUI");
		}else{	
			return mapping.findForward("logout");
		}
	}
	public ActionForward goFilmUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Users user = (Users) request.getSession().getAttribute("loginUser");
		if(user!=null&&user.getUrole().equals("admin")){
			//split page
			String s_pageNow=request.getParameter("pageNow");
			int pageNow=1;
			if(s_pageNow!=null){
				pageNow=Integer.parseInt(s_pageNow);
			}
			request.setAttribute("now", pageNow);
			request.setAttribute("timeTable", timeTableService.showTimeTable(10,pageNow));
			int pageCount=timeTableService.getPageCount(10);
			request.setAttribute("pageCount", pageCount);
			return mapping.findForward("goFilmUI");
		}else{	
			return mapping.findForward("logout");
		}
		
	}
	public ActionForward goUserUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Users user = (Users) request.getSession().getAttribute("loginUser");
		if(user!=null&&user.getUrole().equals("admin")){
			String s_pageNow=request.getParameter("pageNow");
			int pageNow=1;
			if(s_pageNow!=null){
				pageNow=Integer.parseInt(s_pageNow);
			}
			request.setAttribute("now", pageNow);
			request.setAttribute("user", userService.showUser(10,pageNow));
			int pageCount=userService.getPageCount(10);
			request.setAttribute("pageCount", pageCount);			
			return mapping.findForward("goUserUI");
		}else{	
			return mapping.findForward("logout");
		}
	}
	
	public ActionForward goTicketUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Users user = (Users) request.getSession().getAttribute("loginUser");
		if(user!=null&&user.getUrole().equals("admin")){
			String s_pageNow=request.getParameter("pageNow");
			int pageNow=1;
			if(s_pageNow!=null){
				pageNow=Integer.parseInt(s_pageNow);
			}
			request.setAttribute("now", pageNow);
			int pageCount=orderService.getPageCount(10);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("allorder", orderService.getOrder(10, pageNow));
			return mapping.findForward("goTicketUI");
		}else{	
			return mapping.findForward("logout");
		}
	}
	
	//delete order by oid
	public ActionForward delTicket(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String oid = request.getParameter("oid");
		Orders od = (Orders) orderService.findById(Orders.class, Integer.valueOf(oid));
		orderService.delete(od);
		String s_pageNow=request.getParameter("pageNow");
		int pageNow=1;
		if(s_pageNow!=null){
			pageNow=Integer.parseInt(s_pageNow);
		}
		request.setAttribute("now", pageNow);
		int pageCount=orderService.getPageCount(10);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("allorder", orderService.getOrder(10, pageNow));
		return mapping.findForward("goTicketUI");
	}
	
	public ActionForward goPwdUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Users user = (Users) request.getSession().getAttribute("loginUser");
		if(user!=null&&user.getUrole().equals("admin")){
			return mapping.findForward("goPwdUI");
		}else{	
			return mapping.findForward("logout");
		}
	}
	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		request.getSession().invalidate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		//set today movie to attribute
		request.getSession().setAttribute("quicktt",timeTableService.getTimetableToday(sdf.format(date)));
		request.setAttribute("film", filmService.getUpcoming());
		request.setAttribute("tt", timeTableService.showTimeTable(8, 1));
		return mapping.findForward("logout");
	}
}