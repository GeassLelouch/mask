<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp" %>
<% 
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1 
	response.setHeader("Pragma","no-cache"); //HTTP 1.0 
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server  
%>
<!DOCTYPE>
<html>
	<head>
		<!-- header -->
		<!-- 存放<title>網站標題，各種taglib，ex:spring，及meta資訊 -->
		<%@ include file="/WEB-INF/views/common/head.jsp"%>
		<!-- css -->
		<!-- 整個網站共用的css -->
		<%@ include file="/WEB-INF/views/common/css.jsp"%>
		<!-- script -->
		<!-- 整個網站所共用匯入的javascript檔案 -->
		<%@ include file="/WEB-INF/views/common/script.jsp"%>
		<script>
			$(document).ready(function() {
				
			});
		</script>
		
	</head>
	<body>
		<!-- header 頁首標題列-->
		<%@ include file="/WEB-INF/views/common/header.jsp"%>
		
		<!-- body-->
		<jsp:include page="${partial}" />
		<!-- body end-->
		
		<!-- Footer 共用頁尾-->
		<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	</body>
</html>