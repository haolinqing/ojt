<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width" />
<script type="text/javascript">
    
</script>
<c:set var="titleKey">
    <tiles:insertAttribute name="title" ignore="true" />
</c:set>
<title><spring:message code="${titleKey}" text="ojt" /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/js/funcation.js"></script>
</head>
<body>
    <table id="main_table">
    	<tr style="height: 56px;">
        	<td class= "header main_table_td"><tiles:insertAttribute name="header" /></td>
        </tr>
        <tr>
		    <td class="body main_table_td"><tiles:insertAttribute name="body" /></td>    
	    </tr>
	    <tr>
        	<td class="footer">Terasoluna5 勉強</td>
        </tr>
    </table>
</body>
</html>