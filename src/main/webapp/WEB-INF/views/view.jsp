<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html data-bs-theme="dark">

<head>
    <meta charset="UTF-8">
    <title>view</title>
</head>

<body>
    <%@include file="navi.jsp" %>
    <div class="container border p-5">
        <div class="row">
        	<form name="writeFrm">
            <h2 style="text-align: center">view.jsp</h2>
            <table class="table text-center border">
                <thead class="bg-light">
                	<tr>
                		<th scope="col" class="col-3"></th>
                        <th scope="col" class="col-1">${boardDto.writer }</th>
                        <fmt:formatDate value="${boardDto.reg_date }" type="date" pattern="yyyy-MM-dd HH:mm" var="reg_date" />
                        <th scope="col" class="col-3">${reg_date }</th>
                        <th scope="col" class="col-1">조회 ${boardDto.view_cnt }</th>
                        <th scope="col" class="col-1">추천 5</th>
                        <th scope="col" class="col-3"></th>
                   	</tr>
                </thead>
                <tbody>
                    <tr>
                        <td colspan="6"><input type="text" class="form-control" name="title" maxlength="50" value="${boardDto.title }" readonly></td>
                    </tr>
                    <tr>
                        <td colspan="6"><textarea class="form-control" name="title" maxlength="2048" style="height:350px" readonly>${boardDto.getContentView() }</textarea></td>
                    </tr>
                </tbody>
            </table>
            </form>
        </div>
        <c:choose>
        	<c:when test="${sessionId eq boardDto.writer }">
		        <a href="<c:url value='/board/list?${searchCondition.queryString }'/>" class="btn btn-outline-light btn-sm float-end">목록</a>
		        <a class="btn btn-outline-danger btn-sm float-end me-1" onclick="deletePost()">삭제</a>
		        <a href="<c:url value='/board/modify?bno=${boardDto.bno }&page=${page }&pageSize=${pageSize }'/>" class="btn btn-outline-light btn-sm float-end me-1">수정</a>
        	</c:when>
        	<c:otherwise>
        		<a href="<c:url value='/board/list?page=${page }&pageSize=${pageSize }'/>" class="btn btn-outline-light btn-sm float-end">목록</a>
        	</c:otherwise>
        </c:choose>
        <%@include file="comment.jsp"%>
	</div>
	
	<script>
		function deletePost(){
			var confirmed = confirm("정말 삭제하시겠습니까?");
			if(confirmed){
				var form = document.writeFrm;
				form.method = "post";
				form.action = "<c:url value='/board/remove'/>?bno=${boardDto.bno }&page=${page }&pageSize=${pageSize }";
				form.submit();
			}
		}
	</script>
</body>

</html>