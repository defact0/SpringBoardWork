<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>스프링 게시판 내용!</title>
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	// 리플레이 전송에 관한
	function replyInsert(id, num) {
		var con= document.rFrm.contents.value; // 답글 내용
		var parameter = escape(encodeURIComponent(con));
		var param= "uid="+id+"&bnum="+num+"&contents="+parameter;
		// load는 url 이다. param에 있는 쿼리로 전송, replyInsert컨트롤러 작성, 저장된 결과를 data로 뿌림
		// data가 replylistajax이다. function(data){}은 필수다!!!!
		$('#rTable').load("replyInsert",param,function(data){ alert(data);});
	}

</script>
</head>

<body>
	<table>
		<tr height="30">
			<td width="100" bgcolor="pink" align="center">NUM</td>
			<td colspan="5">${blist.bnum}</td>
		</tr>
		<tr height="30">
			<td bgcolor="pink" align="center">WRITER</td>
			<td width="150">${blist.mname}</td>
			<td bgcolor="pink" align="center" width="90">DATE</td>
			<td width="100" align="center">${blist.bdate}</td>
			<td bgcolor="pink" align="center" width="80">COUNT</td>
			<td align="center" width="20">${blist.bviews}</td>
		</tr>
		<tr height="30">
			<td bgcolor="pink" align="center">TITLE</td>
			<td colspan="5">${blist.btitle}</td>
		</tr>
		<tr height="200">
			<td bgcolor="pink" align="center">CONTENTS</td>
			<td colspan="5">${blist.bcontents}</td>
		</tr>
	</table>
	<br>
	<form name="rFrm">
		<table align="center">
			<tr>
				<td><textarea rows="5" cols="50" id="contents" name="contents"></textarea>
				</td>
				<td><input type="button"
					onclick="replyInsert('${members.id}','${blist.bnum}')" value="댓글전송"
					style="width: 100px; height: 80px;"></td>
			</tr>
		</table>
	</form>
	<table>
		<tr bgcolor="cyan" align="center" height="35">
			<td width="100">작성자</td>
			<td width="300">댓글내용</td>
			<td width="150">작성일</td>
		</tr>
	</table>
	<table id="rTable">
		<c:forEach var="rlist" items="${rlist}">
			<tr height="25" align="center">
				<td width="100">${rlist.writer}</td>
				<td width="320">${rlist.rcontents}</td>
				<td width="150">${rlist.rdate}</td>
			</tr>
		</c:forEach>
	</table>
	<br>
</body>
</html>