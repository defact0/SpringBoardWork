<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
	<!-- 
		세션 정보는 members테이블이 아닌 minfo라는 뷰에서 얻어옵니다.
	 -->
		<tr>
			<td colspan=2 align="center">Session Info</td>
		</tr>
		<tr>
			<td>id</td>
			<td>${members.id }</td>
		</tr>
		<tr>
			<td>name</td>
			<td>${members.mname }</td>
		</tr>
		<tr>
			<td>grade</td>
			<td>${members.gname }</td>
		</tr>
		<tr>
			<td>point</td>
			<td>${members.point }</td>
		</tr>
	</table>
	<table id="two_table">
		<tr bgcolor="cyan" align="center" height="35">
			<td width="100">번호</td>
			<td width="400">제목</td>
			<td width="150">작성자</td>
			<td width="200">작성일</td>
			<td width="100">조회수</td>
		</tr>
		<c:forEach var="blist" items="${blist}">
		<tr height="25">
			<td width="100" align="center">${blist.bnum }</td>
			<td width="400"><a href="#view_Layer" onclick="articleView('${blist.bnum}')">${blist.btitle }</a></td>
			<td width="150" align="center"> ${blist.mname }</td>
			<td width="200" align="center">${blist.bdate }</td>
			<td width="100" align="center">${blist.bviews }</td>
		</tr>
		</c:forEach>
	</table>
	<center>${paging }<p></center>
</body>
</html>

<div class="articleView_Layer">
	<div class="bg_layer"></div>
	<div id="view_layer"></div>
</div>
