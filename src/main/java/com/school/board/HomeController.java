package com.school.board;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.school.action.MembersAction;
import com.school.bean.Members;
import com.school.dao.MembersDAO;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("members")
public class HomeController {
	private HttpSession session;
	
	@Resource(name="membersDao")
	private MembersDAO memberDao;
	@Resource(name="transactionManager")
	private DataSourceTransactionManager txManager;
	
// URL	
	@RequestMapping(value="/")
	public String home(){
		return "home";
	}
	@RequestMapping(value="/join")
	public String join(){
		return "join";
	}
	@RequestMapping(value="/memJoin")
	public String memJoin(HttpServletRequest request,Model model){
		String result="join";
		Members mb=new Members();
		
		try{
			request.setCharacterEncoding("UTF-8");
			mb.setId(request.getParameter("id"));
			mb.setPwd(request.getParameter("pwd"));
			mb.setMname(request.getParameter("name"));
			mb.setBirth(request.getParameter("birth"));
			mb.setAddr(request.getParameter("addr"));
			mb.setPhone(request.getParameter("phone"));
			System.out.println(mb.getAddr());
			System.out.println(mb.getBirth());
			System.out.println(mb.getId());
			System.out.println(mb.getMname());
			System.out.println(mb.getPhone());
			System.out.println(mb.getPwd());
			MembersAction ma=new MembersAction(memberDao);
			result = ma.memInsert(mb);
			model.addAttribute("check", 1);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
}
