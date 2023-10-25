<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html data-bs-theme="dark">

<head>
    <meta charset="UTF-8">
    <title>MyBatis</title>
</head>

<body>
    <%@include file="navi.jsp" %>
    <div class="container border p-5">
        <div class="row">
            <h2 style="text-align: center">board.jsp</h2>
            <!-- 검색폼 -->
		    <!-- searchField값, searchWord값을 list 컨트롤러로 보내는 역할 -->
		    <form action="<c:url value='/board/list'/>" method="get" onsubmit="return searchCheck(this)" id="searchForm" class="mb-3">
		      <table id="tsearch">
		        <tr>
		          <td>
		            <select class="form-select form-select-sm" name="searchField" style="width: 130px; display: inline-block">
		              <option value="title" ${param.searchField eq "title" ? "selected" : "" }>제목+내용</option>
		              <option value="content" ${param.searchField eq "content" ? "selected" : "" }>제목</option>
		              <option value="writer" ${param.searchField eq "writer" ? "selected" : "" }>글쓴이</option>
		            </select>   
		            <input class="form-control form-control-sm" type="text" name="searchWord" id="search"
		              value='${ empty param.searchWord ? "" : param.searchWord }' style="width: 300px; display: inline-block">
		            <button type="button" class="btn btn-outline-light btn-sm">검색</button>
		          </td>
		        </tr>
		      </table>
		    </form>
            <table class="table text-center border table-hover">
                <thead>
                    <tr style="background-color: white;">
                        <th scope="col" class="col-1">번호</th>
                        <th scope="col" class="col-7">제목</th>
                        <th scope="col" class="col-1">글쓴이</th>
                        <th scope="col" class="col-2">등록일</th>
                        <th scope="col" class="col-1">조회</th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach var="board" items="${list }">
                    <tr>
                        <td>${board.bno }</td>
                        <td><a href="<c:url value='/board/read?bno=${board.bno }&page=${page }&pageSize=${pageSize }'/>" style="text-decoration: none;">${board.title }</a></td>
                        <td>${board.writer }</td>
                        <fmt:formatDate value="${board.reg_date }" type="date" pattern="yyyy-MM-dd HH:mm" var="reg_date" />
                        <td>${reg_date }</td>
                        <td>${board.view_cnt }</td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <!-- 페이지네이션 시작 --> 
		<a href="<c:url value='/board/write'/>" class="btn btn-outline-light btn-sm float-end">글쓰기</a>
        <nav aria-label="Page navigation example">
		  <ul class="pagination justify-content-center mt-5">
		  	<c:if test="${ph.showPrev }">
			    <li class="page-item">
			      <a class="page-link" href="<c:url value='/board/list?page=${ph.beginPage-1 }&pageSize=${ph.pageSize }'/>" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
			    </li>
		    </c:if>
		    <c:forEach var="i" begin="${ph.beginPage }" end="${ph.endPage }">
		    	<li class="page-item ${ph.page==i? 'active':'' }"><a class="page-link" href="<c:url value='/board/list?page=${i }&pageSize=${ph.pageSize }' />">${i }</a></li>
		    </c:forEach>
		    <c:if test="${ph.showNext }">
			    <li class="page-item">
			      <a class="page-link" href="<c:url value='/board/list?page=${ph.endPage+1 }&pageSize=${ph.pageSize }'/>" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
		    </c:if>
		  </ul>
		</nav>
		<!-- 페이지네이션 끝 -->
	</div>
	<script>
		let msg="${msg}";
		if(msg=="del") alert("성공적으로 삭제되었습니다");
		if(msg=="error") alert("삭제를 실패했습니다");
		if(msg=="write_ok") alert("성공적으로 등록되었습니다");
		if(msg=="modify_error") alert("작성자만 수정할 수 있습니다");
		if(msg=="modify_ok") alert("수정 성공");
	</script>
</body>

</html>