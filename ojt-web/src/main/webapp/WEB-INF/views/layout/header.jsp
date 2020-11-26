<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<table id="head_table">
	<tr>
		<td id="td_head_left">
			ユーザ管理システム&nbsp;&nbsp;&nbsp;&nbsp;
			<sec:authorize access="isAuthenticated()">
				<%-- <a href="'${pageContext.request.contextPath }/top'"><button style="width:110px;">トップページへ</button></a> --%>
				<input type="button" style="width:110px; height:30px;"
					onclick="location.href='${pageContext.request.contextPath }/top'"
					class="button" name="top" value="トップページへ" />
			</sec:authorize>
		</td>
		<td id="td_head_right">
			<sec:authorize access="isAuthenticated()">
				<c:url var="logoutUrl" value="/logout"/>
				<form:form action="${logoutUrl }" method="post">
				<sec:authentication property="principal.username"/>ログイン中&nbsp;&nbsp;
					<input id="layout" type="submit" value="ログアウト"/>
				</form:form>
			</sec:authorize>
		</td>
	</tr>
</table>
</body>
</html>