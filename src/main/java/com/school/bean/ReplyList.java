package com.school.bean;

import java.sql.Date;

public class ReplyList {
	private int bnum;
	private int rnum;
	private String rcontents;
	private Date rdate;
	private String writer;
	
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public String getRcontents() {
		return rcontents;
	}
	public void setRcontents(String rcontents) {
		this.rcontents = rcontents;
	}
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	
}
