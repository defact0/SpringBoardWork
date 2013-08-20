package com.school.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.school.bean.BoardList;
import com.school.bean.Members;
import com.school.bean.ReplyList;

public class MembersDAO extends SqlSessionDaoSupport implements MemberInterface {

	public boolean accessMember(Members members){
		boolean result=false;
		int count=getSqlSession().selectOne("MembersInterface.getLoginResult", members);
		if(count != 0){
			result=true;
		}
		System.out.println(result);
		return result;
	}
	
	//login 로직 start
	@Override
	public int getLoginResult(Map<String, String> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("MembersInterface.getLoginResult", map);
	}

	@Override
	public Members getMemberInfo(String id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("MembersInterface.getMemberInfo", id);
	}
	//login 로직 end

	@Override
	public List<BoardList> getBoardList(int pageNum) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("MembersInterface.getBoardList", pageNum);
	}

	@Override
	public int getPageCount() {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("MembersInterface.getPageCount");
	}

	@Override
	public BoardList getContents(int bnum) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("MembersInterface.getContents", bnum);
	}


	
	@Override
	public int memInsert(Members mb) {
		// 주의!!!! insert할때는 insert!!!
		// selectlist => 레코드가 여러개일때
		// selectone => 레코드가 한개일때~
		return getSqlSession().insert("MembersInterface.memInsert", mb);
	}

	@Override
	// 리플을 입력
	public int rInsert(ReplyList rl) {
		// TODO Auto-generated method stub
		// insert성공 1 실패 0
		return getSqlSession().insert("MembersInterface.rInsert", rl);
	}
	
	@Override
	// 리플을 읽어 오기
	public List<ReplyList> getReply(int bnum) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("MembersInterface.getReply", bnum);
	}


}
