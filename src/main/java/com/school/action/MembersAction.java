package com.school.action;

import com.school.bean.Members;
import com.school.dao.MembersDAO;

public class MembersAction {
	MembersDAO mDao;

	public MembersAction(MembersDAO dao) {
		this.mDao = dao;
	}

	public String memInsert(Members mb) {
		String result = "join";
		if (mDao.memInsert(mb) == 1) { // DB에 insert 성공시 1을 반환
			result = "home"; // 첫페이지 "/"
		}
		return result;
	}
}
