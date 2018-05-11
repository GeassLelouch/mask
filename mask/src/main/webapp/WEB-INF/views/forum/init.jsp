<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp" %>
<!-- car -->
<!-- Google Fonts API 使用google所寫好的字體-->
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600,700" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.theme.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css">
<!-- 網站設計 -->
<!-- Scripts -->
<!-- 整個網站所共用匯入的javascript檔案 -->
<%@ include file="/WEB-INF/views/common/script.jsp"%>
<!--<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>-->
<script src="https://cdn.ckeditor.com/4.9.2/standard/ckeditor.js"></script>

<script>
	$(document).ready(function() {
		$("#checkout").click(function() {
			  $("#productCheckout").submit();
		});	
		
		CKEDITOR.replace( 'editor' );
		
		//$('textarea[name=editorDemo]').val();
		//console.log(CKEDITOR.instances.editorDemo.getData()); // editorDemo是textarea的name
		
	 	//CKEDITOR.instances.editorDemo.updateElement();
	    // 因為先把編輯器更新回textarea了，這裡就可以抓到目前最新的內容
		//console.log($('textarea[name=editorDemo]').val());
	});
	
	
	$(function(){
		
		//console.log(); // editorDemo是textarea的name
		
		$("#submitbtn").click(function() {
			 alert(CKEDITOR.instances.editor.getData());
		});	
		
		$("#exportPdf").click(function() {
			$('#qForm').prop('action', "./exportPdf");
		  	$('#qForm').submit();
		});	
	});
	
</script>
<a href="#" id="header">Header</a>
<!-- Main -->
<div id="main">
<form:form modelAttribute="productList" id="qForm" action="${pageContext.request.contextPath}/views/forum/init" method="post">
	<div class="inner">
	<!-- Boxes -->
		<textarea name="editor"></textarea>
		<input type="button" class="btn" value="送出" id="submitbtn">
	</div>
	
	</form:form>	
</div>
