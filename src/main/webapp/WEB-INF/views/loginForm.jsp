<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html data-bs-theme="dark">
<head>
<meta charset="UTF-8">
<title>loginForm</title>
<style>
	
</style>
</head>
<body>
	<%@include file="navi.jsp" %>	
	<div class="container border">
        <div class="row justify-content-center">
            <div class="col-lg-3">
                <div class="jumbotron py-5">
                    <form method="post" action="<c:url value='/login/action'/>">
                    	<input type="hidden" name="toURL" value="${param.toURL }">
                        <h2 style="text-align: center">Login</h2>
                        <div id="msg" style="color: red;">${URLDecoder.decode(param.msg, "utf-8") }</div>
                        <div class="mb-3">
                            <input type="text" class="form-control" placeholder="아이디" name="id" maxlength="20" value="${cookie.id.value }"/>
                        </div>
                        <div class="mb-3">
                            <input type="password" class="form-control" placeholder="비밀번호" name="password" maxlength="20" />
                        </div>
                        <input type="submit" class="btn btn-success form-control" value="로그인" />
                        <div class="mb-3" style="padding-top: 10px;">
                        	<input type="checkbox" name="rememberId" ${empty cookie.id.value ? "" : "checked" }> 아이디 기억
                        	<a href="#">비밀번호 찾기</a>
                        	<a href="<c:url value='/register/form'/>">회원가입</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
	<script>
		function formCheck(frm) {
			let msg = '';
			if (frm.userId.value.length == 0) {
				setMessage('id를 입력해주세요', frm.userId);
				return false;
			}
			if (frm.password.value.length == 0) {
				setMessage('password를 입력해주세요', frm.password);
				return false;
			}
			return true;
		}

		function setMessage(msg, element) {
			document.getElementById("msg").innerHTML = `${'${msg}'}`;
			if (element) {
				element.select();
			}
		}
	</script>
</body>
</html>