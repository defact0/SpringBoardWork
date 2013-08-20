package com.school.action;

import java.util.List;

import com.school.bean.BoardList;
import com.school.bean.ReplyList;
import com.school.dao.MembersDAO;
import com.school.userclass.Paging;

public class BoardListAction {
	MembersDAO dao;
	
	public BoardListAction(MembersDAO membersDao){
		this.dao = membersDao;
	}
	
	public List<BoardList> getBoardList(int pageNum){
		return dao.getBoardList(pageNum); //sqlMapper.xml 갔다옴
	}
	
	public String getPaging(int pagenum){
		//전체 글의 갯수 파악
		int maxNum=dao.getPageCount(); //전체 글의 숫자
		int pageNum=pagenum; //현재페이지
		int listCount= 10; // 한화면에 게시글 수
		int pageCount= 5; //한 그룹
		String pageName="boardlist"; //게시판의 종류
		
		Paging paging=new Paging(maxNum, pageNum, listCount, pageCount, pageName);
		return paging.makePageGroup();
	}

	public BoardList getContents(int bNum) {
		// TODO Auto-generated method stub
		return dao.getContents(bNum);
	}

	public List<ReplyList> getReplyList(int bNum) {
		// TODO Auto-generated method stub
		return dao.getReply(bNum);
	}
}
