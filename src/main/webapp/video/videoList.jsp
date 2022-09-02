<%@page import="dev.web.model.Video"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table width="100%" cellspacing="0" cellpadding="0">
	<tr>
		<td>
			<table align="center" width="710" cellspacing="0" cellpadding="0"
				border="0">
				
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
<c:forEach var="video" items="${requestScope.videoLog}" varStatus="status">				
 						<td>
							<table style='padding:15px'>
								<tr>
									<td>
										<a href=""> 
											<img src="https://media2.giphy.com/media/f3CtEsJ72j86DIumaJ/200w.webp?cid=ecf05e47e8ctkotgn5zdlz5l8qsu73lzelajs708vjmej2d3&rid=200w.webp&ct=g" border="0" align="center" width="200" >
										</a>
									</td>
								</tr>
								<tr>
								
									<td height="10">
								</tr>
								<tr>
									<td class= "td_default" align ="center">
										<a class= "a_black" href="videoRetrieve?videoId=${video.id}"> 
										video1<br>
										</a>
										<font color="gray">
										 --------------------
										</font>
									</td>
									
								</tr>
								<tr>
									<td height="10">
								</tr>
								<tr>
									<td class="td_gray" align ="center">
										ert
									</td>
								</tr>
								<tr>
									<td height="10">
								</tr>
								<tr>
									<td class="td_red" align ="center"><font color="red"><strong>
									dfg	</strong></font></td>
								</tr>
							</table>
						</td>

  					<c:if test="zxcv">
  					   	     <tr>
								<td height="10">
							</tr>

  					</c:if>
  			

</c:forEach>
			</table>
		</td>
	</tr>
	<tr>
		<td height="10">
	</tr>
</table>