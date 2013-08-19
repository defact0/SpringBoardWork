<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</body>
</html>