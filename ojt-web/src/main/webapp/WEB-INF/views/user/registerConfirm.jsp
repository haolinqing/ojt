<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<div>
	<c:url var="register" value="/user/register"/>
	<form:form method="post" id="userForm" action="${register}">
		<input type="hidden" name="password" value="${userForm.password}"/>
		<table>
			<tr>
				<td class="td_left">
					<label>ユーザID：</label>
				</td>
				<td class="td_right">
					<input class="read_only_input" readonly="readonly" type="text" name="userId" value="${f:h(userForm.userId)}" placeholder="自動採番"/>
				</td>
			</tr>
			<tr>
				<td class="td_left">
					<label>名前：</label>
				</td>
				<td class="td_right">
					<input class="read_only_input" readonly="readonly" type="text" name="username" value="${f:h(userForm.username)}"/>
				</td>
			</tr>
			<tr>
				<td class="td_left">
					<label>生年月日：</label>
				</td>
				<td class="td_right">
					<input class="read_only_input" readonly="readonly" name="birthday" value="<fmt:formatDate value='${userForm.birthday}' pattern='yyyy/MM/dd'/>"/>
				</td>
			</tr>
			<tr>
				<td class="td_left">
					<label>住所：</label>
				</td>
				<td class="td_right">
					<input class="read_only_input" readonly="readonly" name="address" value="${f:h(userForm.address) }"/>
				</td>
			</tr>
			<tr>
				<td class="td_left">
					<label>電話番号：</label>
				</td>
				<td class="td_right">
					<input class="read_only_input" readonly="readonly" name="telephone" value="${f:h(userForm.telephone) }"/>
				</td>
			</tr>
			<tr>
				<td class="td_left">
					<label>権限：</label>
				</td>
				<td class="td_right">
					<c:forEach items="${userForm.roleNames }" var="roleName" varStatus="index">
						<input class="read_only_input" readonly="readonly" name="roleNames" style="width: 45px;" value="${f:h(roleName)}"/>
						<c:if test='${index.last == false}'>,</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="td_left">
					<input type="submit" class="submit_button" onclick="disabled(this);" value="登録"/>
				</td>
				<td class="td_right">
      				<button class="submit_button" onclick="registerRedo();">やり直し</button>
				</td>
			</tr>
		</table>
	</form:form>
</div>
</body>
</html>