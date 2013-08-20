<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

		<c:forEach var="blist" items="${blist}">
		<tr height="25">
			<td width="100" align="center">${blist.bnum}</td>
			<td width="400"><a href="#view_layer" onclick="articleView('${blist.bnum}')">${blist.btitle}</a></td>
			<td width="150" align="center">${blist.mname}</td>
			<td width="200" align="center">${blist.bdate}</td>
			<td width="100" align="center">${blist.bviews}</td>
		</tr>
		</c:forEach>