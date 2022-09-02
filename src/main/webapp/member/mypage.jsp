<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="user" method="get" >
아이디:${sessionScope.login.userId}<br>
이름:${sessionScope.login.userName}<br>
성별:${sessionScope.login.gender}<br>
나이:${sessionScope.login.age}<br>


영상목록:
<c:forEach var="video" items="${requestScope.videoLog}">
	${video.id}<br> 
</c:forEach>
<jsp:include page="../video/videoList.jsp" flush="true" />

</form>