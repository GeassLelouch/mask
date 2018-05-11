<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/order/main.css" />
<script>
	$(document).ready(function () {
		$("#tableElement").click(function() {
			$('#qForm').prop('action', "./exportPdfOfTableElement");
		  	$('#qForm').submit();	
		});	
		
		$("#city").css( "width","150px");
		$("#district").css( "width","150px");
		
		$("#city").change(function() {
			$.ajax({
				type: "POST",
				url: "./getDistrict",
				dataType: "json",
				data: {"cityId":this.value},
			   	success: function(data) {
					$("#district").empty();
					$("#district").append($("<option></option>")
									.attr("value", "")
									.text("請選擇"));
					$.each(data.district, function() {
							$("#district").append($("<option></option>").attr("value", this.id)
																		.text(this.id + "-" + this.name));
					});
				}
			});
		});
		
		
		$("#exportPdf").click(function() {
			pdfBtnEvent();
		});	
		
		$("#exportCsv").click(function() {
			csvBtnEvent();
		});	
		
		$("#restfulBtn").click(function() {
			restBtnEvent();
		});	
		
	});
	
	function pdfBtnEvent() {
		if (chkColumns()) {
			$('#qForm').prop('action', "./exportPdf");
		  	$('#qForm').submit();		
		}
	}
	
	function csvBtnEvent(){
		$('#qForm').prop('action', "./exportCsv");
	  	$('#qForm').submit();		
	}
	
	function chkColumns(){
		return ok;
	}
	
	function restBtnEvent() {
		$.ajax({
			type: "POST",
			url: "http://127.0.0.1:8080/restful/hello/Messiss",
			data: {},
		   	success: function(data) {
		   		$("#bodyContent").html(data);			
			}
		});
	}
</script>
<fieldset>
	<legend>查詢條件</legend>
	<form:form modelAttribute="productList" id="qForm" action="${pageContext.request.contextPath}/views/report/exportPdf" method="post">
		<div id="conditionBox">
			<table style="width: 100%">
				<br/>
				<br/>
				<tr>
					<td>
						<label>價格：</label>
					</td>
					<td colspan="3">
						<input type="text" name="priceStr" value="500">&nbsp;~&nbsp;<input type="text" name="priceEnd" value="800">
					</td>
				</tr>	
				<tr>
					<td>
						<button id="exportPdf">列印</button>
					</td>
					<td>
						<button id="restfulBtn">串restful</button>
					</td>					
					<td>
						<button id="tableElement">表格元素版本的列印</button>
					</td>		
					<td>
						<button id="exportCsv">下載CSV</button>
					</td>								
				</tr>
				<tr>
					<td>
						<label>縣市：</label>
					</td>
					<td colspan="3">
						<select id="city" name="city">
							<option value="">請選擇</option>
							<c:if test="${!empty sessionScope.city}">
								<c:forEach items="${sessionScope.city}" var="item">
									<option value="${item.id}">${item.id}-${item.name}</option>
								</c:forEach>
							</c:if>
						</select>
						<label id="cityLabel"/>
					</td>		
				</tr>	
				<tr>
					<td>
						<label>行政區：</label>
					</td>				
					<td colspan="3">
						<select id="district" name="district">
							<option value="">請選擇</option>
							<c:if test="${!empty sessionScope.district}">
								<c:forEach items="${sessionScope.district}" var="item">
									<option value="${item.id}">${item.id}-${item.name}</option>
								</c:forEach>
							</c:if>
						</select>
					</td>						
				</tr>					
			</table>
		</div>
	</form:form>	
</fieldset>		