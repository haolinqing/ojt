<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<div>
    <c:if test="${param.containsKey('error')}">
    	<t:messagesPanel messagesType="error" messagesAttributeName="SPRING_SECURITY_LAST_EXCEPTION"/>
    	<!-- <div class="alert-error">ログインに失敗しました。ユーザー名とパスワードを確認してください</div> -->
    </c:if>
	<form:form action="${pageContext.request.contextPath }/login" method="post" modelAttribute="userForm">
		<table>
			<tr>
				<td class="td_left">ユーザID：</td>
				<td class="td_right">
					<form:input type="text" path="userId" onfocusout="trim(this)"/>
					<form:errors path="userId" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<td class="td_left">パスワード：</td>
				<td class="td_right">
					<form:input type="password" path="password" onfocusout="trim(this)"/>
					<form:errors path="password" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<td class="td_left"></td>
				<td class="td_right">
					<input type="submit" value="ログイン"/>
				</td>
			</tr>
		</table>
	</form:form>
	<br/><br/><br/><br/><br/>

</div>
</body>
</html>