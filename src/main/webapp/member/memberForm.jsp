<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form action="userAdd" method="get">
	아이디 : <input type="text" name="userId" id="userId"> <span
		id="result" style="color: red"></span><br> 비밀번호 : <input
		type="text" name="passwd" id="passwd"><br> <span
		id="result2" style="color: red"></span> 이름 : <input type="text"
		name="username"><br> 성별 : <input type="text"
		name="gender"><br> 나이 : <input type="text" name="age"><br>
	<input type="submit" value="회원가입"> <input type="reset"
		value="취소"> <a href="main.jsp">home</a>&nbsp;&nbsp;
</form>

