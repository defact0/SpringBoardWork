///////////////////////////
//    SpringBoardWork
// Spring 를 이용한 게시판!
//////////////////////////

Spring 이용한 Model2 MVC패턴 게시판 입니다.

* 이 게시판에 필요한 DB Table의 정보는 http://ejungdo.blog.me/50177414135 에서 확인하세요.

* SpringBoardWork 의 시작은 / (root page)이다. root가 home.jsp으로 이동하게 설정되어 있다.
   http://localhost:8088/ (오라클 설치할때 포트를 8088으로 설정된 상태입니다.)

[08/19]
	- Paging.java 게시판 페이지 정리코드 (이해하자)
	- boardlist.jsp 에서 게시판 출력추가, <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 잊지말자!!



* import 에러날때 C:\Users\admin\.m2\repository 안에 있는 파일을 삭제합니다.
Build Path에서 Order and Export 탭에 체크되지 않은 부분을 체크한 다음 ok버튼을 누르면 해결됩니다.

* 이 프로그램을 동작해 보기 위해서는 Oracle 11g Express가 필요합니다.
MEMO.txt 에서 동작에 필요한 SQL 코드가 들어 있으니 꼭 참고하시기 바랍니다.

* 오라클연결은 이클립스에서 톰캣서버-context.xml 코드에서 접속할 계정설정을 하면됩니다.
이 안에 있는 내용은 ServersContext.txt에서 확인하세요.

* 각 폴더마다 하나씩 README.txt 가 있으면 이런 화면이 자동 출력된다.
** txt는 UTF-8 형식으로 저장해야 한글이 깨지지 않는다!
-------------------------------
Update README files.