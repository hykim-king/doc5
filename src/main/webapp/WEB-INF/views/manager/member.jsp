<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>DOC5 관리자 - 회원관리</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin.css">
    <style>
      .search-area {
            display: flex;
            justify-content: flex-end;
            margin-bottom: 15px;
        }
        .search-area input[type="text"] {
            padding: 8px;
            border: 1px solid #ccc;
            margin-right: 5px;
        }
        .search-area button {
            padding: 8px 15px;
            background-color: #007bff;
            color: white;
            border: none;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="wrapper">
<div class="sidebar">
    <h2>DOC5 관리자 페이지</h2>
    <a href="${pageContext.request.contextPath}/manager/index.do">메인</a> 
    <a href="${pageContext.request.contextPath}/manager/member.do">회원관리</a>
    <a href="${pageContext.request.contextPath}/manager/shop.do">주문 관리</a>
</div>

    <div class="content">
        <div class="header">
            <div class="page-title">회원 관리</div>
<div class="admin-menu">
    <a>[${adminBranchName.branchName}]</a>
    <a href="${pageContext.request.contextPath}/manager/branchLogin.do" class="logout-link">로그아웃</a>
</div>
        </div>

        <div class="section">
            <h2> 전체 회원 목록</h2>
            
            <div class="search-area">
                <form action="${pageContext.request.contextPath}/manager/member.do" method="GET">
                    <input type="text" name="searchWord" placeholder="ID로 검색" value="${param.searchWord}">
                    <button type="submit">검색</button>
                </form>
            </div>

            <table class="member-table">
                <thead>
                    <tr>
                        <th>회원 ID</th>
                        <th>이름</th>
                        <th>전화번호</th>
                        <th>가입일</th>
                        <th>관리</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty memberList}">
                            <c:forEach var="member" items="${memberList}">
                                <tr>
                                    <td>${member.userId}</td>
                                    <td>${member.name}</td>
                                    <td>${member.phone}</td>
                                    <td>${member.regDt}</td>
                                    <td>
                                        <button onclick="confirmDelete('${member.userId}')" class="btn-delete">삭제</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="5">등록된 회원이 없습니다.</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
            
            
            
        </div>
    </div>
</div>

<form id="deleteForm" action="${pageContext.request.contextPath}/manager/deleteMember.do" method="POST">
    <input type="hidden" name="userId" id="deleteUserId">
</form>

<script>
    function confirmDelete(userId) {
        if (confirm("정말로 회원 [" + userId + "] 님을 삭제하시겠습니까?")) {
            document.getElementById('deleteUserId').value = userId;
            document.getElementById('deleteForm').submit();
        }
    }
</script>

</body>
</html>