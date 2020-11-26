<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<div>
    <c:if test="${param.error}">
      <t:messagesPanel/>
    </c:if>
	<c:url var="userUrl" value="/user/search"/>
	<form:form action="${userUrl}" modelAttribute="userSearchForm">
		<t:messagesPanel />
		<table>
			<tr>
				<td class="td_left">
					<form:label path="userId">ユーザID：</form:label>
				</td>
				<td class="td_right">
					<form:input path="userId" onfocusout="trim(this)"/>
					<form:errors path="userId"  cssClass="error"/>
				</td>
			</tr>
			<tr>
				<td class="td_left">
					<form:label path="username">名前：</form:label>
				</td>
				<td class="td_right">
					<form:input path="username" onfocusout="trim(this)"/>
					<form:errors path="username" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<td class="td_left">
					<form:label path="birthday">生年月日：</form:label>
				</td>
				<td class="td_right">
					<form:input path="birthday" onfocusout="trim(this)"/>
					(yyyy/MM/dd 形式)
					<form:errors path="birthday" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<td class="td_left">
					<form:label path="address">住所：</form:label>
				</td>
				<td class="td_right">
					<form:input path="address" onfocusout="trim(this)"/>
					<form:errors path="address" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<td class="td_left">
					<form:label path="telephone">電話番号：</form:label>
				</td>
				<td class="td_right">
					<form:input path="telephone" onfocusout="trim(this)"/>
					<form:errors path="telephone" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<td class="td_left">
					<form:label path="roleNames">権限：</form:label>
				</td>
				<td class="td_right">
					<form:select path="roleNames"  items="${CL_ROLENAMES}" multiple="false">
						<%-- <form:option value=""></form:option>
						<form:options items="${CL_ROLENAMES}"/> --%>
					</form:select>
					<form:errors path="roleNames" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<td class="td_left">
					<form:label path="status">状態：</form:label>
				</td>
				<td class="td_right">
					<form:select path="status" items="${CL_STATUS}">
						<%-- <form:option value=""></form:option>
						<form:options items="${CL_STATUS}"/> --%>
					</form:select>
					<form:errors path="status" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<td class="td_left"></td>
				<td class="td_right">
					<input type="submit" value="検索"/>
				</td>
			</tr>
		</table>
	</form:form>
</div>
</body>
</html>