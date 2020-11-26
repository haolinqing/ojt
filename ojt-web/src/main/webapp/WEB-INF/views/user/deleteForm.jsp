<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<div style="text-align: center;">
	<c:url var="deleteUrl" value="/user/delete?confirm"/>
	<form:form action="${deleteUrl}" modelAttribute="userForm" method="post" >
		<t:messagesPanel />
		<table>
			<tr>
				<td class="td_left">
					<label>ユーザID：</label>
				</td>
				<td class="td_right">
					<form:input class="read_only_input" readonly="true" type="text"  path="userId"/>
				</td>
			</tr>
			 <tr>
				<td class="td_left">
					<label>名前：</label>
				</td>
				<td class="td_right">
					<form:input class="read_only_input" readonly="true" type="text"  path="username"/>
				</td>
			</tr>
			<tr>
				<td class="td_left">
					<label>生年月日：</label>
					
				</td>
				<td class="td_right">
					<form:input class="read_only_input" readonly="true" path="birthday"  pattern='yyyy/MM/dd'/>
				</td>
			</tr>
			<tr>
				<td class="td_left">
					<label>住所：</label>
				</td>
				<td class="td_right">
					<form:input class="read_only_input" readonly="true" path="address" />
				</td>
			</tr>
			<tr>
				<td class="td_left">
					<label>電話番号：</label>
				</td>
				<td class="td_right">
					<form:input class="read_only_input" readonly="true" path="telephone"/>
				</td>
			</tr>
			<tr>
				<td class="td_left">
					<label>権限：</label>
				</td>
				<td class="td_right">
					<%-- <c:forEach items="${userForm.roleNames }" var="roleName" varStatus="index">
						<input class="read_only_input" readonly="readonly" name="roleNames" style="width: 45px;" value="${f:h(roleName)}"/>
						<c:if test='${index.last == false}'>,</c:if>
					</c:forEach> --%>
					<form:checkboxes items="${CL_ROLENAMES}" path="roleNames" onclick="return false;"/>
				</td>
			</tr> 
			<tr>
				<td class="td_center" colspan="2">
					<input type="submit" value="削除"/>				
				</td>
			</tr>
		</table>
	</form:form>
</div>
</body>
</html>