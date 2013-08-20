<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
 <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.2.js"></script>
<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>

<script>
	function formCheck() {
		var length = document.forms[0].length - 1;

		for ( var i = 0; i < length - 1; i++) {
			if (document.forms[0][i].value == null
					|| document.forms[0][i].value == "") {

				alert(document.forms[0][i].name + "을/를 입력하세요.");
				document.forms[0][i].focus();
				return false;
			}
		}
	}
	
// submit과 button에 jQuery UI 적용! - 0820
// http://pgm-progger.blogspot.kr/2012/11/jquery-button-jquery-ui.html
	$(function(){
	  $( ":submit" ).button().click(function( event ) {
			var url = "access";    
			$(location).attr('href',url);
		  });
	  $( ":button" ).button().click(function( event ) {
	    //그냥 이대로 두면, 알아서 버튼 등에 onclick 등으로 설정한대로 버튼이 동작합니다.
	    event.preventDefault(); 
	  });
	  //$( ":button, :submit" ).css({"padding":"2px 5px 2px 5px", "font-size":"9pt"});
	  // padding : 북, 서, 남, 동 순서
	  $( ":button, :submit" ).css({"padding":"8px 10px 6px 10px", "font-size":"9pt"});
	});
</script>

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
				<td colspan=2><input type="submit" value="로그인" class="myfont" /> <input
					type="button" value="회원가입"
					onClick="javascript:location.href='/join'"></td>
			</tr>
		</table>
	</form>
</body>
</html>
<!--
유효성 체크 코드를 넣었음. 하나라도 입력이 안됐다면 다음으로 넘어가지 않는다.
/SpringBoard/src/main/java/com/school/board/HomeController.java
으로가서 /join에 대한 처리를 작성한다.

-->