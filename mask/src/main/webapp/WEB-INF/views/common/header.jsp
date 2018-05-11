<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<!-- header -->
<header class="header">
	<nav class="nav">
		<ul>
			<!-- office -->
			<span>
				<button id="home">Home</button>
				<button id="about">About</button>
				<button id="product">Product</button>
				<button id="report">report</button>
				<button id="report">reportForAudrey</button>
				<button id="forum">forum</button>
				<button id="contact">Contact</button>
			</span>
			<!-- social  -->

			    <c:choose>
			        <c:when test="${sessionScope.result == 'success'}">
						<span style="float: right;">
							您好！<c:out value="${sessionScope.name}"/>
							<button id="out">out</button>
						</span>
			    	</c:when>
				    <c:otherwise>
						<!-- social  -->
						<span style="float: right;">
							<button id="login">Login</button>
							<button id="register">register</button>
						</span>
				    </c:otherwise>
			    </c:choose>
		</ul>
	</nav>
	<!-- 要去common.js寫如果點擊選項後觸發submit -->
	<form:form modelAttribute="product" id="productTarget" action="${pageContext.request.contextPath}/views/product/show" method="post">
	</form:form>	
	<form:form modelAttribute="product" id="indexTarget" action="${pageContext.request.contextPath}/views/index/init" method="get">
	</form:form>	
	<form:form modelAttribute="product" id="registerTarget" action="${pageContext.request.contextPath}/views/index/register" method="get">
	</form:form>	
	<form:form modelAttribute="product" id="loginTarget" action="${pageContext.request.contextPath}/views/index/login" method="get">
	</form:form>	
	<form:form modelAttribute="product" id="outTarget" action="${pageContext.request.contextPath}/views/index/out" method="post">
	</form:form>		
	<form:form modelAttribute="product" id="reportTarget" action="${pageContext.request.contextPath}/views/report/init" method="post">
	</form:form>
	<form:form modelAttribute="product" id="forumTarget" action="${pageContext.request.contextPath}/views/forum/init" method="post">
	</form:form>	
	
	
	
</header>