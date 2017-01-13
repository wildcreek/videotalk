<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
	<style type="text/css">
		*{
			margin:0px;
			padding:0px;
		}
		body{ text-align:center} 
		a{
			text-decoration:none;
		}
		html,body{
			height:100%;
		}
		#main{
			font-size:50px;
			top:90px;
			width:100%;
			height:100px;
			position:absolute;
			display:none;
		}
		#downId{
			font-size:80px;
			width:100%;
			height:60px; 
			top:400px;
			position:absolute;
		}
	</style>
<body>
		<div id="main">
		微信内无法下载，请点击<font color="green"><b>右上角</b></font>按钮<br>
			<font color="green"><b>[在浏览器打开]</b></font>即可正常下载
		</div>
		<div id="downId">
		</div>
</body>
</html>
<script>
	window.onload=function(){
		var oMain=document.getElementById("main");
		var oDownId=document.getElementById("downId");
		var ua = navigator.userAgent.toLowerCase();  
	    if(ua.match(/MicroMessenger/i)=="micromessenger") {  
			oDownId.innerHTML="点击此链接下载APK..";
			oMain.style.display="block";
	    } else {
	    	oDownId.innerHTML="<a href='http://192.168.1.102:8585/videotalk/downloadApk'>点击此链接下载APK..</a>";
	    	oMain.style.display="none";
	    }
	}
</script>











