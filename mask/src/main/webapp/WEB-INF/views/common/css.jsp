<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css" />
<style type="text/css">
	/* index.jsp跟show.jsp 共用 */
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
	.header {
		height: 60px;
		width: 100%;
		top: 0;
		position: fixed;
		background-color: black;
	}
	/*-- header */
	.nav {
		height: 100px;
		padding: 10px;
		background-attachment: fixed;
		line-hight: 29px;
		/*text-align: left;*/
	}
	
	
	
	/* 只有index.jsp跟template.jsp有用到的css */
		/* body */
		.cd-fixed-bg1 {
			background-image:
				url("${pageContext.request.contextPath}/img/cd-bg1.jpg");
			background-size: cover;
			background-attachment: fixed;
			background-repeat: no-repeat;
			background-position: center center;
			height: 700px;
		}
		
		.cd-fixed-bg2 {
			background-image:
				url("${pageContext.request.contextPath}/img/cd-bg2.jpg");
			background-size: cover;
			background-attachment: fixed;
			background-repeat: no-repeat;
			background-position: center center;
			height: 700px;
		}
		
		.cd-fixed-bg3 {
			background-image:
				url("${pageContext.request.contextPath}/img/cd-bg3.jpg");
			background-size: cover;
			background-attachment: fixed;
			background-repeat: no-repeat;
			background-position: center center;
			height: 700px;
		}
		
		.cd-fixed-bg4 {
			background-image:
				url("${pageContext.request.contextPath}/img/cd-bg4.jpg");
			background-size: cover;
			background-attachment: fixed;
			background-repeat: no-repeat;
			background-position: center center;
			height: 700px;
		}
		
		.cd-scrolling-bg {
			height: 500px;
			background-color: white;
		}
		
		.cd-container {
			text-align: justify;
		}
		
		a {
			color: #666666;
			font-size: 1em;
			padding: 5px;
		}
		
		.sub-right {
			text-align: right;
		}
		/*contact us*/
		input[type=text] {
			width: 300px;
			height: 30px;
			padding: 12px 20px;
			margin: 8px 0;
			display: inline-block;
			border: 1px solid #ccc;
			border-radius: 4px;
			box-sizing: border-box;
		}
		
		textarea {
			width: 300px;
			height: 150px;
			padding: 12px 20px;
			box-sizing: border-box;
			border: 2px solid #ccc;
			border-radius: 4px;
			background-color: #f8f8f8;
			font-size: 16px;
			resize: none;
		}
		
		input[type=submit], input[type=reset] {
			width: 100px;
			background-color: dodgerblue;
			color: white;
			padding: 14px 20px;
			margin: 8px 0;
			border: none;
			border-radius: 4px;
			cursor: pointer;
		}
		
		input[type=submit]:hover, input[type=reset]:hover {
			background-color: blue;
		}
		
		.contactTitle {
			position: relative;
			left: 40%;
			top: 50%;
			border: 10px solid grey;
		}	
	
	
</style>