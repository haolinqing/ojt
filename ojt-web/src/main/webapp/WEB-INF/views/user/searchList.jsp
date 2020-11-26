<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/js/funcation.js"></script>
<script src="${pageContext.request.contextPath}/resources/app/vendor/js/jquery.min.js"></script>
</head>
<body>
<div>
	<div>
		<t:pagination page="${page}" outerElementClass="pagination" criteriaQuery="${f:query(userSearchForm)}"/>
	</div>
	<div style="text-align: right;">
		${f:h(page.number + 1)} / ${f:h(page.totalPages)}ページ&nbsp;&nbsp;（${f:h(page.totalElements)}件）
	</div>
	<div id="result_table_div">
		<table id="result_table">
			<tr class="result_table_th_tr">
				<sec:authorize access="hasRole('ROLE_ADMN')">
				<th class="result_table_td">選択</th>
				</sec:authorize>
				<th class="result_table_td">ユーザID</th>
				<th class="result_table_td">名前</th>
				<th class="result_table_td">生年月日</th>
				<th class="result_table_td">住所</th>
				<th class="result_table_td">電話番号</th>
				<th class="result_table_td">権限</th>
				<th class="result_table_td">状態</th>
			</tr>
		<c:forEach items="${page.content }" var="user">
			<tr class="result_table_td_tr">
				<sec:authorize access="hasRole('ROLE_ADMN')">
					<td class="result_table_td">
							<c:set var="RMVD" value="<%=jp.co.ntt.ojt.domain.model.User.RMVD%>"/>
							<c:if test="${user.status != RMVD}">
								<input type="radio" name="userId" value="${f:h(user.userId) }">					
							</c:if>
					</td>
				</sec:authorize>
				<td class="result_table_td">${f:h(user.userId)}</td>
				<td class="result_table_td">${f:h(user.username)}</td>
				<td class="result_table_td">
					<joda:format value="${user.birthday}" pattern="yyyy/M/d"/>
				</td>
				<td class="result_table_td">${f:h(user.address)}</td>
				<td class="result_table_td">${f:h(user.telephone)}</td>
				<td class="result_table_td">
					<c:forEach items="${user.roleNames }" var="roleName">
						${f:h(roleName)}<br/>
					</c:forEach>
				</td>
				<%-- <td class="result_table_td">${f:h(user.statusStr)}</td> --%>
				<c:if test="${user.status == 'INIT'}">
					<td class="result_table_td">初期状態</td>
				</c:if>
				<c:if test="${user.status == 'ACTV'}">
					<td class="result_table_td">有効状態</td>
				</c:if>
				<c:if test="${user.status == 'RMVD'}">
					<td class="result_table_td">削除済み</td>
				</c:if>
			</tr>
		</c:forEach>
		</table>
	</div>
	<br/>
	<div>
		<sec:authorize access="hasRole('ROLE_ADMN')">
			<button onclick="updateForm();">更新</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button onclick="deleteForm();">削除</button>
		</sec:authorize>
	</div>
	<br/>
</div>
</body>
</html>