<%@page import="dev.web.model.Video"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="video" items="${requestScope.videoLog}" varStatus="status">				
	<a href="/VideoLog/videoRetrieve?videoId=${video.id}">
		<table style='padding: 15px'>
			<tr>
				<td><img src="${video.description}" border="0" align="center"
					width="200"></td>
			</tr>
			<tr>

				<td height="10">
			</tr>
			<tr>
				<td class="td_default" align="center"><font color="gray">
						-------------------- </font></td>

			</tr>
			<tr>
				<td height="10">
			</tr>
			<tr>
				<td class="td_gray" align="center"><span><strong>${video.id}.
							${video.title}</strong> </span></td>
			</tr>
			<tr>
				<td height="10">
			</tr>
			<tr>
				<td class="td_red" align="center"><span>작성자:
						${video.uploader} 조회수: ${video.readcnt}</span></td>
			</tr>
		</table>
	</a>
</c:forEach>
