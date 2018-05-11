<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp" %>
<!-- car -->
<!-- Google Fonts API 使用google所寫好的字體-->
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600,700" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/car/reset.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/car/htmleaf-demo.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/car/style.css"> <!-- Resource style -->
<!-- 網站設計 -->
<!-- Scripts -->
<!-- 整個網站所共用匯入的javascript檔案 -->
<%@ include file="/WEB-INF/views/common/script.jsp"%>
<!--<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>-->
<script src="${pageContext.request.contextPath}/js/car/main.js"></script>
<script src="${pageContext.request.contextPath}/js/car/jquery.poptrox.min.js"></script>
<script src="${pageContext.request.contextPath}/js/car/jquery.scrolly.min.js"></script>
<script>
	$(document).ready(function() {
		$("#checkout").click(function() {
			  $("#productCheckout").submit();
		});	
	});
</script>
<a href="#" id="header">Header</a>
<!-- Main -->
<div id="main">
	<div class="inner">
	<!-- Boxes -->
		<div class="thumbnails">
			<c:forEach var="product" items="${productList}" >
				<div class="box">
					<!-- 原本有class="image fit"，但用途不明，可以先拿掉 -->
					<a href="https://youtu.be/s6zR2T9vn2c"><img src="${pageContext.request.contextPath}/img/${product.id}.jpg"/></a>
					<div class="inner">
						<h3>${product.name}</h3>
						<h5>${product.skinType}</h5><br/>
						<button class="cd-add-to-cart" data-price="${product.price}" value="${product.name}" lang="${product.id}" type="button">加入購物車</button>
						<!-- <a href="https://youtu.be/s6zR2T9vn2c" class="button fit" data-poptrox="youtube,800x400">Watch</a>  -->
					</div>
				</div>							
			
			</c:forEach>
		</div>
	</div>
</div>
<form:form modelAttribute="productList" id="productCheckout" action="${pageContext.request.contextPath}/views/order/init" method="post">
	<div class="cd-cart-container empty">
		<a href="#0" class="cd-cart-trigger">
			Cart
			<ul class="count"> <!-- cart items count -->
				<li>0</li>
				<li>0</li>
			</ul> <!-- .count -->
		</a>
		<div class="cd-cart">
			<div class="wrapper">
				<header>
					<h2>Cart</h2>
					<span class="undo">商品刪除. <a href="#0">取消刪除</a></span>
				</header>
				
				<div class="body">
					<ul>
						<!-- products added to the cart will be inserted here using JavaScript -->
					</ul>
				</div>

				<footer>
					<a href="#0" id="checkout" class="checkout btn"><em>結帳 - $<span>0</span></em></a>
				</footer>
			</div>
		</div> <!-- .cd-cart -->
	</div> <!-- cd-cart-container -->
</form:form>		