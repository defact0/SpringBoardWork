package com.school.board;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.school.action.BoardListAction;
import com.school.action.MembersAction;
import com.school.bean.Members;
import com.school.bean.ReplyList;
import com.school.dao.MembersDAO;
import com.school.userclass.EncryptionEncoding;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("members") // members라는 세션영역에 저장
							  // jsp에서 ${members.id} 이런식으로 세션영역의 변수를 호출할 수 있다. sessionScope.members.id 대신함
public class HomeController {
	private HttpSession session;
	
	// mybatis-context.xml 에서 연결되었다.
	@Resource(name="membersDao") //@Autowired 도 왼쪽과  같은 자동주입이나 권장하지 않는 방법이다. 가능하면 @Resource를 쓰라
	private MembersDAO membersDao;
	
	@Resource(name="transactionManager")
	private DataSourceTransactionManager txManager;
	
	// 암호화, 자동주입 등록(mybatis-context.xml에서)
	//private EncryptionEncoding ee = new EncryptionEncoding();
	@Resource(name="EncryptionEncoding")
	//@Resource(name="EE") //네임으로 설정으로 가능하다.
	
	private EncryptionEncoding ee;
	
	
// URL	
	@RequestMapping(value="/")
	public String home(){
		return "home";
	}
	@RequestMapping(value="/join")
	public String join(){
		return "join";
	}
	@RequestMapping(value="/memJoin") //회원가입
	public String memJoin(HttpServletRequest request,Model model){
		String result="join";
		Members mb=new Members();
		
		try{
			request.setCharacterEncoding("UTF-8");
			mb.setId(request.getParameter("id"));
			mb.setPwd(ee.TripleDesEncoding(request.getParameter("pwd"))); // 패스워드 인코딩
				//디코딩 테스트
			//mb.setMname(request.getParameter("name"));
			mb.setMname(ee.TripleDesEncoding(request.getParameter("name")));
			mb.setBirth(request.getParameter("birth"));
			mb.setAddr(request.getParameter("addr"));
			mb.setPhone(request.getParameter("phone"));

			MembersAction ma=new MembersAction(membersDao);
			result = ma.memInsert(mb);
			model.addAttribute("check", 1);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
// login에 성공하고 글 목록 출력하기
	@SuppressWarnings("unused") //자바에서 제공하는 경고끄기
	@RequestMapping(value="/access")
	public String mInfo(HttpServletRequest request, Model model){
		String result="home";
		
		Members members=new Members();
		
		try{
			Map<String,String> map=new HashMap<String, String>();
			map.put("id", request.getParameter("id"));
			// 입력받은 패스워드랑 DB에 암호화된 패스워드를 비교해야함, 단 입력받은 패스워드도 암호화해서 비교해야한다.
			map.put("pwd", ee.TripleDesEncoding(request.getParameter("pwd"))); //예외처리 필요
			
			MembersAction ma=new MembersAction(membersDao); // 29line과 관련됨 membersDao
			members=ma.accessMembers(map);
			
			// mname 디코딩 코드
			// mname 크기를 수정해줘야함 nvarchar2(10)인데 암호화하면 10을 넘어감.
			// nvarchar2(100)으로 늘렸음
			members.setMname(ee.TripleDesDecoding(members.getMname())); 
			
			
			if(members!=null){
				session=request.getSession();
				session.setAttribute("uid", members.getId());
				// @SessionAttributes("members") 때문에 아래 코드가 간단해 졌다.
				model.addAttribute("members",members);
				result=boardList(request,model);
			}else{
				if(session != null){ session=null;}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value="/boardlist")
	public String boardList(HttpServletRequest request, Model model){
		String result="home";
		
		try{
			BoardListAction ba=new BoardListAction(membersDao);
			
			if(session!=null && session.getAttribute("uid")!=""){
				session=request.getSession();
				
				int pageNum=(request.getParameter("pageNum")!=null)? Integer.parseInt(request.getParameter("pageNum")):1;
				
				// paging 관련 로직
				model.addAttribute("pageNum", pageNum);
				model.addAttribute("blist",ba.getBoardList(pageNum)); //게시글
				model.addAttribute("paging", ba.getPaging(pageNum)); //[1][2]...<- paging
				result="boardlist";
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	//게시글 보기
	@RequestMapping(value="/contents")
	public String listContents(HttpServletRequest request, Model model){
		String result="boardlist";
		BoardListAction ba=new BoardListAction(membersDao);
		try{
			if(session!=null && session.getAttribute("uid")!=""){
				int bnum= Integer.parseInt(request.getParameter("bnum"));
				model.addAttribute("blist", ba.getContents(bnum)); //원글 보기
				model.addAttribute("rlist", ba.getReplyList(bnum)); //댓글 보기
				result="boardContents";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	@RequestMapping(value= "/boardlistAjax")
	public String boardListAjax(HttpServletRequest request, Model model) {
		System.out.println("진입테스트"+request.getParameter("pageNum"));
		String result= "home";
		
		try {
			BoardListAction ba= new BoardListAction(membersDao);
			
			if(session!=null && session.getAttribute("uid")!=""){
				int pageNum= (request.getParameter("pageNum")!=null)?Integer.parseInt(request.getParameter("pageNum")):1;
				model.addAttribute("pageNum", pageNum);
				model.addAttribute("blist", ba.getBoardList(pageNum));
				result= "boardlistAjax";
			}
		} catch (Exception e){
			e.printStackTrace(); 
		}		
		return result;
	}
	
	
	@RequestMapping(value= "/replyInsert")
	public String rInsert(HttpServletRequest request, Model model) {
		String result= "boardContents";
		try {
			BoardListAction ba= new BoardListAction(membersDao);
			if(session!=null && session.getAttribute("uid")!=""){
				ReplyList rl= new ReplyList();
				int bNum= Integer.parseInt(request.getParameter("bnum"));
				rl.setBnum(bNum);
				rl.setWriter(request.getParameter("uid"));
				rl.setRcontents(URLDecoder.decode(request.getParameter("contents"),"UTF-8"));
				
				if(ba.replyInsert(rl)==1){
					model.addAttribute("rlist", ba.getReplyList(bNum));
					result= "replylistAjax";
				}
			}
		} catch (Exception e){
			e.printStackTrace(); 
		}		
		return result;
	}
}
