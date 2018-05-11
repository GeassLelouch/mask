<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp" %>

<!-- body-->
<div id="AboutTitle" class="cd-fixed-bg1" style="text-align: center;">
	</br>
	</br>
	</br>
	</br>
	</br>
	</br>
	</br>
	</br>
	</br>
	<font face="monospace" size="10" color="white" ><b>卸下你的。你的真面目(底下的index).....</b></font>
	<img src="${pageContext.request.contextPath}/img/icon/mask.ico"	height="150"/>
</div>

<div class="cd-scrolling-bg cd-color1">
	<div class="cd-container" style="text-align: center;">
		</br>
		</br>
		</br>
		<h2 style="color: #33CCFF;">About Us</h2>
		<p style="color: grey;">
			有實力。經得起考驗~~不怕被懷疑!!</br> 
			法國上架的必備條件－需經過最嚴格的檢驗證實效果。 </br> 
			Institutd‘ExpertiseClinique跟EvicFrance是法國專門測試化妝保養品品質的檢驗室。</br>
			卸下你的。你的真面目面膜與許多國際知名化妝保養品，都與IEC或EvicFrance合作多次經驗。</br>
			由英國領導美妝雜誌所舉辦，邀請專業美容顧問、美容俱樂部會員與讀者們共同票選，</br>
			卸下你的。你的真面目面膜在2016年首次入圍最佳新秀面膜獎，打敗來自各國的國際品牌，成為2016年的新秀面膜獎冠軍！</br>
			天下沒有醜女人，只有懶女人!</br> 
			你準備好了嗎 !!
		</p>
	</div>
</div>

<div id="ProductTitle" class="cd-fixed-bg2">
	</br>
	</br>
	</br>
	<h1/>
</div>

<div class="cd-scrolling-bg cd-color-2">
	<div class="cd-container" style="text-align: center;">
		</br>
		</br>
		</br>
		<h2 style="color: #33CCFF;">Inspection Report</h2>
		<p style="color: grey;">
			有實力。經得起考驗~~不怕被懷疑!!</br> 
			法國上架的必備條件－需經過最嚴格的檢驗證實效果。 </br> 
			Institutd‘ExpertiseClinique跟EvicFrance是法國專門測試化妝保養品品質的檢驗室。</br>
			卸下你的。你的真面目面膜與許多國際知名化妝保養品，都與IEC或EvicFrance合作多次經驗。</br>
			由英國領導美妝雜誌所舉辦，邀請專業美容顧問、美容俱樂部會員與讀者們共同票選，</br>
			卸下你的。你的真面目面膜在2016年首次入圍最佳新秀面膜獎，打敗來自各國的國際品牌，成為2016年的新秀面膜獎冠軍！</br>
			天下沒有醜女人，只有懶女人!</br> 
			你準備好了嗎 !!
		</p>
	</div>
</div>

<div id="ShopTitle" class="cd-fixed-bg3">
	<h1></h1>
</div>
<div class="cd-scrolling-bg cd-color-3">
<div class="cd-container" style="text-align: center;">
		</br>
		</br>
		</br>
		<h2 style="color: #33CCFF;">Future Prospects</h2>
		<p style="color: grey;">
			有實力。經得起考驗~~不怕被懷疑!!</br> 
			法國上架的必備條件－需經過最嚴格的檢驗證實效果。 </br> 
			Institutd‘ExpertiseClinique跟EvicFrance是法國專門測試化妝保養品品質的檢驗室。</br>
			卸下你的。你的真面目面膜與許多國際知名化妝保養品，都與IEC或EvicFrance合作多次經驗。</br>
			由英國領導美妝雜誌所舉辦，邀請專業美容顧問、美容俱樂部會員與讀者們共同票選，</br>
			卸下你的。你的真面目面膜在2016年首次入圍最佳新秀面膜獎，打敗來自各國的國際品牌，成為2016年的新秀面膜獎冠軍！</br>
			天下沒有醜女人，只有懶女人!</br> 
			你準備好了嗎 !!
		</p>
	</div>
</div>
<div id="ContactTitle" class="cd-fixed-bg4">
	<br> <br> <br> <br> <br> <br> <br>
	<br> <br> <br> <br> <br> <br> <br>
</div>	
<div id="formLogin">
	<form:form id="login" modelAttribute="member" action="${pageContext.request.contextPath}/views/index/login" method="post">
		<p>
			<input type="text" name="mName" place="帳號" />
		</p>
		<p>
			<input type="password" name="mPassword" require="true" label="密碼" place="密碼" />
		</p>
		<p>
			<button class="contactTitleClass" type="button">送出</button>
			<button id="registerButton" type="button">註冊</button>
			<button id="cancel" type="button">取消</button>
		</p>
	</form:form>
</div>
<div id="registerDiv">
	<form:form id="registerForm" modelAttribute="member" action="${pageContext.request.contextPath}/views/index/register" method="post">
		<input type="text" class="required" name="userName" />
		<input type="text" class="required" name="mName"/>
		<input type="text" class="required email" name="email"/>
		<input type="text" class="number" name="phone"/>
		<input type="text" class="required" name="gender"/>
		<input type="text" class="date" name="birthday"/>
		<input type="text" name="address"/>
		<input type="password" class="required" name="mPassword"/>
		<input type="password" class="required" name="mPasswordMatch"/>
		<input id="loginButton" type="button" value="登入" />
		<input id="registerSubmit" type="button" value="註冊" />
		<input id="cancelRegister" type="button" value="取消" />
	</form:form>
</div>
<!-- body end-->
		
