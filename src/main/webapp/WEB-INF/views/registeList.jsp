<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html data-bs-theme="dark">

<head>
    <meta charset="UTF-8">
    <title>RegisteList</title>
</head>

<body>
    <%@include file="navi.jsp" %>
    <div class="container border p-5">
        <div class="row">
            <h2 style="text-align: center">registeList.jsp</h2>
            <table class="table table-striped text-center border">
                <thead class="bg-light">
                    <tr>
                        <th scope="col">id</th>
                        <th scope="col">pwd</th>
                        <th scope="col">name</th>
                        <th scope="col">email</th>
                        <th scope="col">birth</th>
                        <th scope="col">sns</th>
                        <th scope="col">reg_date</th>
                        <th scope="col">delete</th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach var="list" items="${userList }">
                    <tr>
                        <td>${list.id }</td>
                        <td>${list.pwd }</td>
                        <td>${list.name }</td>
                        <td>${list.email }</td>
                        <fmt:formatDate value="${list.birth }" type="date" pattern="yyyy-MM-dd" var="birth" />
                        <td>${birth }</td>
                        <td>${list.sns }</td>
                        <fmt:formatDate value="${list.reg_date }" type="date" pattern="yyyy-MM-dd | HH:mm" var="reg_date" />
                        <td>${reg_date }</td>
                        <td><a href="<c:url value='deleteUser?id=${list.id }'/>" class="btn btn-outline-danger btn-sm">삭제</a></td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
	</div>
</body>

</html>