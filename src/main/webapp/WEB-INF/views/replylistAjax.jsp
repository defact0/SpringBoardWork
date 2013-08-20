<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

		<c:forEach var="rlist" items="${rlist}">
			<tr height="25" align="center">
				<td width="100">${rlist.writer}</td>
				<td width="320">${rlist.rcontents}</td>
				<td width="150">${rlist.rdate}</td>
			</tr>
		</c:forEach>