<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html>
<html data-bs-theme="dark">
<head>
<meta charset="UTF-8">
<title>registerForm</title>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
	<%@include file="navi.jsp" %>	
	<div class="container border">
        <div class="row justify-content-center">
            <div class="col-lg-3">
                <div class="jumbotron py-5">
                    <form method="post" action="<c:url value="/register/action"/>" method="post" onsubmit="return formCheck(this)">
                        <h2 style="text-align: center">Register</h2>
                        <div id="msg" class="msg" style="color: red;">${URLDecoder.decode(param.msg, "utf-8") }</div>
                        <div class="mb-3">
                            <input type="text" class="form-control" placeholder="아이디" name="id" maxlength="20" autofocus required/>
                        </div>
                        <div class="mb-3">
                            <input type="password" class="form-control" placeholder="비밀번호" name="pwd" maxlength="20" required/>
                        </div>
                        <div class="mb-3">
                            <input type="password" class="form-control" placeholder="비밀번호 확인" name="passwordCheck" maxlength="20" required/>
                        </div>
                        <div class="mb-3">
                            <input type="text" class="form-control" placeholder="이름" name="name" maxlength="20" required/>
                        </div>
                        <div class="mb-3">
                            <input type="email" class="form-control" placeholder="이메일" name="email" maxlength="20" required/>
                        </div>
                        <div class="mb-3">
                            <input type="text" class="form-control" placeholder="생일 0000/00/00" name="birth" maxlength="20" required/>
                        </div>
                        <div class="mb-2">
                        	<input type="checkbox" name="sns" value="facebook"> 페이스북
                        	<input type="checkbox" name="sns" value="instagram"> 인스타그램
                        	<input type="checkbox" name="sns" value="kakaotalk"> 카카오톡
                        </div>
                        <input type="submit" class="btn btn-success form-control" value="회원가입" />
                    </form>
                </div>
            </div>
        </div>
    </div>
	
	<script>
	function formCheck(frm){
	  var msg='';
	  if(frm.userId.value.length<3){
	      setMessage('id의 길이는 3이상이어야 합니다', frm.userId);
	      return false;
	  }
	  return true;
	}
	
	function setMessage(msg, element){
	  document.getElementById("msg").innerHTML = `${'${msg}'}`;
	  if(element){
	      element.select();
	   }
	}
	</script>

</body>
</html>