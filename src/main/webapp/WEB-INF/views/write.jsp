<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html data-bs-theme="dark">

<head>
    <meta charset="UTF-8">
    <title>write</title>
</head>

<body>
    <%@include file="navi.jsp" %>
    <div class="container border p-5">
        <div class="row">
            <h2 style="text-align: center">write.jsp</h2>
            <form method="post" action="<c:url value='/board/write'/>" onsubmit="return formCheck(this)">
	            <table class="table table-striped text-center border">
	                <thead class="bg-light">
	                </thead>
	                <tbody>
	                    <tr>
	                        <td><input type="text" class="form-control" placeholder="글 제목" name="title" maxlength="50"></td>
	                    </tr>
	                    <tr>
	                        <td><textarea class="form-control" placeholder="글 내용" name="content" maxlength="2048" style="height:350px"></textarea></td>
	                    </tr>
	                </tbody>
	            </table>
                <input type="submit" class="btn btn-outline-light btn-sm float-end" value="등록">
            </form>
        </div>
	</div>
	<script>
		let msg = ${msg };
		if (msg=="write_error") alert("게시글 작성에 실패했습니다");
		
		function formCheck(frm) {
	      if (frm.title.value.trim() === "" || frm.content.value.trim() === "") {
	          alert("제목과 내용을 입력해주세요");
	          return false;
	      }
	      return true;
	  	}
	</script>
</body>

</html>