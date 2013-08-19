<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Join page</title>
	<script>
		function formCheck(){
			var length=document.forms[0].length-1;
			 
			 for(var i=0; i<length-1; i++){
				 if(document.forms[0][i].value==null||document.forms[0][i].value==""){

					 alert(document.forms[0][i].name+"을/를 입력하세요.");
					 document.forms[0][i].focus();
					 return false;
				 }
			 } 
		}
	</script>


</head>
<body>
		<h1>Account profile page!</h1>
		<form action="/memJoin" name="joinFrm" method="post" onsubmit="return formCheck();">
			<table>
				<tr>
					<td>아이디:</td>
					<td><input type="text" name="id" /></td>
				</tr>
				<tr>
					<td>비밀번호:</td>
					<td><input type="password" name="pwd" /></td>
				</tr>
				<tr>
					<td>이름:</td>
					<td><input type="text" name="name" /></td>
				</tr>
				<tr>
					<td>생일:</td>
					<td><input type="text" name="birth" /></td>
				</tr>
				<tr>
					<td>주소:</td>
					<td><input type="text" name="addr" /></td>
				</tr>
				<tr>
					<td>전화번호:</td>
					<td><input type="text" name="phone" /></td>
				</tr>
				<tr>
					<td colspan=2>
					<input type="submit" value="회원가입" /> 
					<input
						type="button" value="되돌아가기" onClick="javascript:location.href='/'">
					</td>
				</tr>
			</table>
	</form>
</body>
</html>