<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<div style="text-align: center;">
	<c:url var="registerUrl" value="/user/register?confirm"/>
	<form:form action="${registerUrl}" method="post" modelAttribute="userForm">
		<t:messagesPanel />
		<table>
			<tr>
				<td class="td_left">
					<label>ユーザID：</label>
				</td>
				<td class="td_right">
					<form:input type="text" path="userId" placeholder="自動採番" onfocusout="isExistedOfUserId(this);"/>
					<form:errors path="userId"  cssClass="error"/>
					<span id="userIdError" class="error"></span>
				</td>
			</tr>
			<tr>
				<td class="td_left">
					<label>名前：</label>
				</td>
				<td class="td_right">
					<form:input type="text" path="username" onfocusout="trim(this);"/>
					<form:errors path="username"  cssClass="error"/>
				</td>
			</tr>
			<tr>
				<td class="td_left">
					<label>生年月日：</label>
				</td>
				<td class="td_right">
					<c:set var="birthday"><fmt:formatDate value='${userForm.birthday}' pattern='yyyy/MM/dd'/></c:set>
					<form:input path="birthday" value="${birthday }" onfocusout="trim(this);"/>(yyyy/MM/dd 形式)
					<form:errors path="birthday"  cssClass="error"/>
				</td>
			</tr>
			<tr>
				<td class="td_left">
					<label>住所：</label>
				</td>
				<td class="td_right">
					<form:input path="address" onfocusout="trim(this);"/>
					<form:errors path="address"  cssClass="error"/>
				</td>
			</tr>
			<tr>
				<td class="td_left">
					<label>電話番号：</label>
				</td>
				<td class="td_right">
					<form:input path="telephone" onfocusout="trim(this);"/>
					<form:errors path="telephone"  cssClass="error"/>
				</td>
			</tr>
			<tr>
				<td class="td_left">
					<label>権限：</label>
				</td>
				<td class="td_right">
					<form:checkboxes items="${CL_ROLENAMES }" path="roleNames" />
					<form:errors path="roleNames"  cssClass="error"/>
				</td>
			</tr>
			<tr>
				<td class="td_left">
					<label>パスワード：</label>
				</td>
				<td class="td_right">
					<form:password path="password" onfocusout="trim(this);"/>
					<form:errors path="password"  cssClass="error"/>
				</td>
			</tr>
			<tr>
				<td class="td_left">
					<label>パスワード確認：</label>
				</td>
				<td class="td_right">
					<form:password path="repassword" onfocusout="trim(this);"/>
				</td>
			</tr>
			<tr>
				<td class="td_center" colspan="2">
					<input type="submit" value="登録"/>				
				</td>
			</tr>
		</table>
	</form:form>
</div>
</body>
</html>