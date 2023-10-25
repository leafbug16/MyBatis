<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html data-bs-theme="dark">
<head>
<meta charset="UTF-8">
<title>Index</title>
<style>
	#content {
		text-align: center;
		margin-top: 50px;
	}
</style>
</head>
<body>
	<%@include file="navi.jsp" %>
    <div class="container border p-5">
       <div class="row justify-content-center">
           <div class="col-lg-6" style="text-align: center;">
			<h2>index.jsp</h2>
			The time on the server is ${serverTime}           	
           </div>
       </div> 
    </div>
</body>
</html>