<%@page import="dev.web.model.Video"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
table {
	border: 1px solid black;
}
tr {
	border: 1px solid black;
}
.outer {
	display: felx;
	flex-direction: column;
}
</style>

<table class="outer" width="100%" cellspacing="0" cellpadding="0">
	<tr>
		<td height="30">
	</tr>
	<tr>
		<td>
			<table align="center" width="710" cellspacing="0" cellpadding="0"
				border="0" style='margin-left: 30px'>
				<tr>
					<td rowspan="7"><img src="${video.description}" /></td>
				</tr>
				<tr>
					<td class="td_default"><font size="5"><b>- Video 정보
								-</b></font> &nbsp;</td>
				</tr>
				<tr>
					<td height="5"></td>
				</tr>
				<tr>
					<td height="10"></td>
				</tr>

				<tr>
					<td class="td_title">제목</td>
					<td class="td_default" colspan="2" style='padding-left: 30px'>
						${video.title}</td>
				</tr>
				<tr>
					<td class="td_title">체널명</td>
					<td class="td_default" colspan="2" style='padding-left: 30px'>
						${video.uploader}</td>
				</tr>
				<tr>
					<td class="td_title">조회수</td>

					<td class="td_red" colspan="2" style='padding-left: 30px'>${video.readcnt}</td>
				</tr>
				<tr>
					<button value="hate">hate</button>: ${video.hate}
					<button value="like">like</button>: ${video.like}
				</tr>
				
			</table>

		</td>
	</tr>
</table>

<script>
	const btns = document.getElementsByTagName("button");
	for (let btn of btns) {
		btn.addEventListener("click", (event) => {const value = event.target.value;
												  location.href = "/VideoLog/" + value + "?videoId=" + ${video.id};});
	}
	
</script>
