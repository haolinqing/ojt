<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<div>
	<c:url var="changeUrl" value="/password/change"/>	
	<form:form action="${changeUrl}" modelAttribute="userForm" method="post">
    	<t:messagesPanel />    	
    	<c:set var="lastUpdateTime"><joda:format value='${user.lastUpdateTime}' pattern='yyyy/MM/dd HH:mm:ss'/></c:set>
		<form:hidden path="lastUpdateTime" value="${lastUpdateTime}"/>
		<form:errors path="lastUpdateTime" cssClass="error"/>
		<form:hidden path="userId" value="${user.userId}"/>
		<form:errors path="userId" cssClass="error"/>
		<table>
			<tr>
				<td class="td_left">パスワード：</td>
				<td class="td_right">
					<form:input type="password" path="password" onfocusout="trim(this)"/>
					<form:errors path="password" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<td class="td_left">パスワード確認：</td>
				<td class="td_right">
					<form:input type="password" path="repassword" onfocusout="trim(this)"/>
				</td>
			</tr>
			<tr>
				<td class="td_left"></td>
				<td class="td_right">
					<input style="width: 120px;" type="submit" value="パスワード変更"/>
				</td>
			</tr>
		</table>
	</form:form>
	<br/><br/><br/><br/><br/>
</div>
</body>
</html>