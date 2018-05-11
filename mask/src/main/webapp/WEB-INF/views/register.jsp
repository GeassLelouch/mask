<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/order/main.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css">
		<script src="${pageContext.request.contextPath}/js/common/common.js" type="text/javascript"></script>
		<style type="text/css">
			.header {
				height: 60px;
				width: 100%;
				top: 0;
				position: fixed;
				background-color: black;
			}
			.nav {
				height: 100px;
				padding: 10px;
				background-attachment: fixed;
				line-hight: 29px;
				/*text-align: left;*/
			}
			#gotop {
			    display: none;
			    position: fixed;
			    right: 20px;
			    bottom: 20px;    
			    padding: 10px 15px;    
			    font-size: 20px;
			    background: #777;
			    color: white;
			    cursor: pointer;
			}
		</style>	
			<!-- Scripts -->
		<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
		<script src="${pageContext.request.contextPath}/js/skel.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/util.js"></script>
		<script src="${pageContext.request.contextPath}/js/main.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
		<script>
			$(document).ready(function() {
			  	$( function() {
				    $( "#datepicker" ).datepicker();
				} );
			  	
				$("#registerSubmit").click(function() {
					$("#registerForm").validate();
					$("#registerForm").submit();
				});	
			});	
		</script>
	</head>
	<body id="top">
		<!-- header -->
		<%@ include file="/WEB-INF/views/common/header.jsp"%>
		
			<br/>
			<!-- Form -->
			<form:form id="registerForm" modelAttribute="member" action="${pageContext.request.contextPath}/views/index/registerConfirm" method="post">
				<div class="row uniform 50%">
					<div class="2u$ 12u$(xsmall)" style="margin:0px auto;">
						帳號：<input type="text" name="userName" id="userName" class="required" value="" style="margin:0px auto;" placeholder="帳號" />
					</div>
					<div class="2u$ 12u$(xsmall)">
						密碼：<input type="password" class="required" name="mPassword" placeholder="密碼"/>
					</div>					
					<div class="2u$ 12u$(xsmall)">
						信箱：<input type="text" class="email" name="email" placeholder="信箱"/>
					</div>
					<div class="2u$ 12u$(xsmall)">
						姓名：<input type="text" class="required" name="mName" placeholder="姓名"/>
					</div>
					<div class="2u$ 12u$(xsmall)">
						生日：<input type="text" id="datepicker" name="birthday">
					</div>					
					<div class="2u$ 12u$(xsmall)">
						電話：<input type="text" class="number" name="phone" placeholder="電話"/>
					</div>
					<div class="2u$ 12u$(xsmall)">
						地址：<input type="text" name="address" placeholder="地址"/>
					</div>
					<div class="1u 12u$(xsmall)">
						<input type="radio" id="boy" name="gender" value="1" checked>
						<label for="boy">男</label>
					</div>
					<div class="1u$ 12u$(xsmall)">
						<input type="radio" id="girl" name="gender" value="0">
						<label for="girl">女</label>
					</div>
					<div class="12u$">
						<ul class="actions">
							<li><input id="registerSubmit" type="submit" value="註冊" class="special" /></li>
							<li><input type="reset" value="清除" /></li>
						</ul>
					</div>
				</div>
			</form:form>	
					
		<!-- Footer -->
		<%@ include file="/WEB-INF/views/common/footer.jsp"%>	
	</body>
</html>