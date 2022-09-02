<%@page import="dev.web.model.Video"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
	table {
		border: 1px solid black;
	}
</style>

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
						<td rowspan="7">
						<img src="http://www.tizag.com/pics/htmlT/sunset.gif" /></td>
						<img src="https://media2.giphy.com/media/f3CtEsJ72j86DIumaJ/200w.webp?cid=ecf05e47e8ctkotgn5zdlz5l8qsu73lzelajs708vjmej2d3&rid=200w.webp&ct=g" /></td>
						<img src="https://media4.giphy.com/media/MeJgB3yMMwIaHmKD4z/200.webp?cid=ecf05e47bwkah0q4e0327umzwivj0c0ez9851g813g94k89s&rid=200.webp&ct=g" /></td>
						<img src="https://media2.giphy.com/media/LmxS3CI4Pqk7aPviYO/200w.webp?cid=ecf05e47bwkah0q4e0327umzwivj0c0ez9851g813g94k89s&rid=200w.webp&ct=g" /></td>
						<img src="https://media0.giphy.com/media/A06UFEx8jxEwU/200.webp?cid=ecf05e47swd5k726wslzjp01eqhc41ddijyh6svscs380pz8&rid=200.webp&ct=g" /></td>
						<img src="https://media0.giphy.com/media/zgduo4kWRRDVK/200w.webp?cid=ecf05e47swd5k726wslzjp01eqhc41ddijyh6svscs380pz8&rid=200w.webp&ct=g" /></td>
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