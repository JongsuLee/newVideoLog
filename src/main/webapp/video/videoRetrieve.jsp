<%@page import="dev.web.model.Video"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form name="videoRetrieveForm" method="get" action="retrieve">

	<input type="hidden" name="title" value="${videoRetrieve.title}">
	<input type="hidden" name="uploader" value="${videoRetrieve.uploader}">
	<input type="hidden" name="readcnt" value="${videoRetrieve.readcnt}">
	<input type="hidden" name="like" value="${videoRetrieve.like}">
	<input type="hidden" name="hate" value="${videoRetrieve.hate}">

	<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30">
		</tr>
		<tr>
			<td>
				<table align="center" width="710" cellspacing="0" cellpadding="0"
					border="0" style='margin-left: 30px'>
					<tr>
						<td class="td_default"><font size="5"><b>- Video
									정보 -</b></font> &nbsp;</td>
					</tr>
					<tr>
						<td height="5"></td>
					</tr>
					<tr>
						<td height="1" colspan="8" bgcolor="CECECE"></td>
					</tr>
					<tr>
						<td height="10"></td>
					</tr>

					<tr>
						<td rowspan="7"><img
							src="images/items/${videoRetrieve.gImage}.gif" border="0"
							align="center" width="300" /></td>
						<td class="td_title">제목</td>
						<td class="td_default" colspan="2" style='padding-left: 30px'>
							${videoRetrieve.title}</td>
					</tr>
					<tr>
						<td class="td_title">체널명</td>
						<td class="td_default" colspan="2" style='padding-left: 30px'>
							${videoRetrieve.uploader}</td>
					</tr>
					<tr>
						<td class="td_title">조회수</td>

						<td class="td_red" colspan="2" style='padding-left: 30px'><fmt:formatNumber
								value="${videoRetrieve.readcnt}" type="currency" /></td>
					</tr>
				</table>

			</td>
		</tr>
	</table>

	<br> <input type="button" value="like"> <input
		type="submit" value="hate">
</form>