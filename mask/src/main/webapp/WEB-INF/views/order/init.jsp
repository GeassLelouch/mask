<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp" %>
<!-- 線上免費網頁樣式   所使用的檔案 -->
<script src="${pageContext.request.contextPath}/js/templated/skel.min.js"></script>
<script src="${pageContext.request.contextPath}/js/templated/util.js"></script>
<script src="${pageContext.request.contextPath}/js/templated/main.js"></script>

<form:form modelAttribute="productList" id="productCheckout" action="${pageContext.request.contextPath}/views/order/save" method="post">
	<!-- Table -->
	<section>
		<h3>&nbsp;&nbsp;結帳</h3>
		<h4>&nbsp;&nbsp;商品列表</h4>
		<div class="table-wrapper">
			<table>
				<thead>
					<tr>
						<th>商品名稱</th>
						<th>膚質</th>
						<th>效果</th>
						<th>銷售量</th>
						<th>庫存量</th>
						<th>價格</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="product" items="${productList}" varStatus="status">
						<tr>
							<td>${product.name}</td>
							<td>${product.skinType}</td>
							<td>${product.feature}</td>
							<td>${product.sales}</td>
							<td>${product.stock}</td>
							<td>${product.price}</td>
						</tr>
						<input type="text" name="idList[${status.count-1}]" value="${product.id}" style="display:none">
						<input type="text" name="sumAmount" value="${sumAmount}" style="display:none">
						<c:if test="${status.last}">
						   <input type="text" name="count" value="${status.count-1}" style="display:none">
						</c:if>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="5"></td>
						<td>${sumAmount}</td>
					</tr>
					<tr>
						<td colspan="5"></td>
						<td><input type="submit" value="送出訂單" class="special" /></td>
					</tr>
				</tfoot>
			</table>
		</div>
	</section>
</form:form>	