<%@page import="dev.web.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${empty login}">
	<a href="member/loginForm.jsp">로그인</a>&nbsp;&nbsp;
 <a href="memberForm.jsp">회원가입</a>&nbsp;&nbsp;
</c:if>

<c:if test="${! empty login}">
 안녕하세요.${login.userId}님<br>
	<a href="../userForm.jsp">mypage</a>&nbsp;&nbsp;
 <a href="../main.jsp">로그아웃</a>&nbsp;&nbsp;
</c:if>
