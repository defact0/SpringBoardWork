<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script>
	$(function() {
		$("input[type=submit]").button().click(function(event) {
			var url = "access";    
			$(location).attr('href',url);
		});
	});
	

</script>

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

<style type="text/css">
.myfont {
	color: #022F4C;
	font-family: 굴림;
	font-size: 9pt;
	line-height: 150%;
}
</style>


</head>
<body>
		<h1>Login page!</h1>

		<form action="access" name="LogFrm" method="post"
			onsubmit="return formCheck();">
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
					<td colspan=2>
					<input type="submit" value="로그인" class="myfont"/> 
					
					<input type="button" value="회원가입"
						onClick="javascript:location.href='/join'"></td>
				</tr>
			</table>
			
			<a href="/join" class="myfont"> 회원가입</a>
			
			<button id="bt">Click</button>
<a href="example.com" id="ex">Triggerable link</a>

<script type="text/javascript">
  $('#bt').click(function() {
       $('#ex').click();
  });
</script>
				
	</form>
</body>
</html>
<!-- 
	유효성 체크 코드를 넣었음. 하나라도 입력이 안됐다면 다음으로 넘어가지 않는다.
	
	/SpringBoard/src/main/java/com/school/board/HomeController.java
	으로가서 /join에 대한 처리를 작성한다.

 -->
