<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="loginOutlink2" value="${empty sessionScope.id? '/login/form':'/login/logout' }"/>
<c:set var="loginOut2" value="${empty sessionScope.id? 'Login' : 'Logout' }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>navi</title>
<style>
	#content {
		text-align: center;
		margin-top: 50px;
	}
</style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<body>
	<!-- 네비게이션 바 -->
    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="container-xxl border p-2 bg-success">
            <!-- 로고 -->
            <a class="navbar-brand text-white" href="<c:url value='/'/>">
            <img src="<c:url value='/img/spring-icon.svg'/>" alt="Logo" width="30" height="24" class="d-inline-block align-text-center">
            SPRING</a>

            <!-- 햄버거 아이콘 (작은 화면에서 보여질 버튼) -->
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="#navbarSupportedContent"
                aria-expanded="#navbarSupportedContent" aria-label="#NavbarToggleBtn">
                <span class="navbar-toggler-icon"></span>
            </button>

            <!-- 메뉴 항목들을 감싸는 div (작은 화면에서 접혀서 보여질 영역) -->
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <!-- 메뉴 항목들의 리스트 -->
                <ul class="navbar-nav mb-2 mb-lg-0">
                    <!-- 각 메뉴 항목 -->
                    <li class="nav-item">
                        <a class="nav-link text-white" aria-current="page" href="<c:url value='/'/>">메인</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-white" href="<c:url value='/board/list'/>">게시판</a>
                    </li>
                    <li class="nav-item text-white">
                        <a class="nav-link text-white" href="<c:url value='/registeList'/>">회원목록</a>
                    </li>
                </ul>
                <c:choose>
                	<c:when test="${empty sessionScope.id }">
                		<span class="ms-auto">로그인 해주세요</span>
                	</c:when>
                	<c:otherwise>
		                <span class="ms-auto" style="color: blue;">${sessionScope.id }님</span>
                	</c:otherwise>
                </c:choose>
                <ul class="navbar-nav">
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle text-white" id="navbarDropdown" role="button"
                            data-bs-toggle="dropdown" aria-expanded="false">접속하기
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        	<c:choose>
                        		<c:when test="${empty sessionScope.id }">
		                            <li><a href="<c:url value='/login/form'/>" class="dropdown-item">로그인</a></li>
		                            <li><a href="<c:url value='/register/form'/>" class="dropdown-item">회원가입</a></li>                        		
                        		</c:when>
                        		<c:otherwise>
                        			<li><a href="<c:url value='/login/logout'/>" class="dropdown-item">로그아웃</a></li>
                        		</c:otherwise>
                            </c:choose>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>