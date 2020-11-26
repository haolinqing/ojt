<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<div>
	<div style="margin-bottom: 25px;">
		<sec:authorize access="hasRole('ROLE_ADMN')">
			<a href="${pageContext.request.contextPath}/user/register?form" id="registerLink"><button onclick="javascript:void(0);">登録</button></a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</sec:authorize>
		<a href="${pageContext.request.contextPath}/user/search?form"><button onclick="javascript:void(0);">検索</button></a>
	</div>
	<%-- <div style="margin-bottom: 40px;">
		<sec:authorize access="hasRole('ROLE_ADMN')">
			<a>
				<button onclick="javascript:void(0);">一括登録</button>
			</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a>
				<button onclick="javascript:void(0);">一括登録結果確認</button>
			</a>	
		</sec:authorize>
	</div> --%>
</div>
</body>
</html>