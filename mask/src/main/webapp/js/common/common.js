$(document).ready(function() {
	
	/*  login 彈現  對話視窗 ，應該還未實作，看是否要做成彈出視窗*/
	$('#Login').click(function() {
		$.blockUI({
			message : $('#form-login')
		});

		setTimeout($.unblockUI, 2000);
	});
	/*login end*/
	
	
	$("#home").click(function() {
		  $("#indexTarget").submit();
	});	
	
	$("#product").click(function() {
		  $("#productTarget").submit();
	});	
	
	$("#report").click(function() {
		  $("#reportTarget").submit();
	});	
	
	$("#register").click(function() {
		  $("#registerTarget").submit();
	});	
	
	$("#login").click(function() {
		  $("#loginTarget").submit();
	});	
	
	$("#out").click(function() {
		  $("#outTarget").submit();
	});	
	
	$("#forum").click(function() {
		  $("#forumTarget").submit();
	});		
	
	$("#formLogin").hide();
	$("#registerDiv").hide();	
	
	$(function() {
		$("#gotop").click(function() {
			jQuery("html,body").animate({
				scrollTop : 0
			}, 1000);
		});
		$(window).scroll(function() {
			if ($(this).scrollTop() > 300) {
				$('#gotop').fadeIn("fast");
			} else {
				$('#gotop').stop().fadeOut("fast");
			}
		});
	});
	
	
	/*  這幾段寫在index.jsp
	$('#login').click(function() {
		$.blockUI({
			message : $('#formLogin')
		});
	});
	
	$('#loginButton').click(function() {
		$.blockUI({
			message : $('#formLogin')
		});
	});				
	
	$('#registerButton').click(function() {
		$.blockUI({
			message : $('#registerDiv')
		});
	});
	
	$('#cancel').click(function() {
		$.unblockUI(); 
	});
	
	$('#cancelRegister').click(function() {
		$.unblockUI(); 
	});
	
	$("#registerSubmit").click(function() {
		$("#registerForm").validate();
		$("#registerForm").submit();
	});	
	*/
	
	/* 原本是設計按了以後會直接在同一個畫面滑動到另一個位置 */
	/* 下拉 連接畫面 
	$('#About').click(function() {
		$('html,body').animate({
			scrollTop : $('#AboutTitle').offset().top
		}, 800);
	});

	$('#Product').click(function() {
		$('html,body').animate({
			scrollTop : $('#ProductTitle').offset().top
		}, 800);
	});

	$('#Shop').click(function() {
		$('html,body').animate({
			scrollTop : $('#ShopTitle').offset().top
		}, 800);
	});

	$('#Contact').click(function() {
		$('html,body').animate({
			scrollTop : $('#ContactTitle').offset().top
		}, 800);
	});
	 */
	/*
	$(function(){
		// 幫 a.abgne_gotoheader 加上 click 事件
		$('a.abgne_gotoheader').click(function(){
			// 讓捲軸用動畫的方式移動到 0 的位置
			// 感謝網友 sam 修正 Opera 問題
			var $body = (window.opera) ? (document.compatMode == "CSS1Compat" ? $('html') : $('body')) : $('html,body');
			$body.animate({
				scrollTop: 0
			}, 600);
	 
			return false;
		});
	});
	*/
	
});