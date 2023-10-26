<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html data-bs-theme="dark">

<head>
    <meta charset="UTF-8">
    <title>edit</title>
</head>

<body>
    <%@include file="navi.jsp" %>
    <div class="container border p-5">
        <div class="row">
            <form method="post" action="<c:url value='/board/modify'/>">
            	<h2 style="text-align: center">edit.jsp</h2>
            	<input type="hidden" name="bno" value="${boardDto.bno }"/>
	            <table class="table table-striped text-center border">
	                <thead class="bg-light">
	                </thead>
	                <tbody>
	                    <tr>
	                        <td><input type="text" class="form-control" name="title" maxlength="50" value="${boardDto.title }"></td>
	                    </tr>
	                    <tr>
	                        <td><textarea class="form-control" name="content" maxlength="2048" style="height:350px">${boardDto.getContentView() }</textarea></td>
	                    </tr>
	                </tbody>
	            </table>
                <a href="<c:url value='board/list'/>" class="btn btn-outline-light btn-sm float-end">목록</a>
                <input type="submit" class="btn btn-outline-light btn-sm float-end me-1" value="수정 완료">
            </form>
        </div>
	</div>
	<script>
		let msg=${msg };
		if(msg=="modify_error") alert("수정 실패");
	</script>
</body>

</html>