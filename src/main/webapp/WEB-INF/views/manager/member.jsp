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
        /* 기본 레이아웃 및 배경 */
        .content { padding: 30px; background-color: #f8f9fa; min-height: 100vh; }
        .section { background: white; padding: 25px; border-radius: 12px; box-shadow: 0 4px 6px rgba(0,0,0,0.05); }
        
        /* 헤더 타이틀 */
        .section-title { font-size: 20px; font-weight: 700; color: #333; margin-bottom: 20px; display: flex; align-items: center; justify-content: space-between; }

        /* 검색 영역 디자인 */
        .search-area { margin-bottom: 20px; }
        .search-form { display: flex; gap: 8px; justify-content: flex-end; }
        .search-input { 
            padding: 10px 15px; border: 1px solid #ddd; border-radius: 8px; width: 250px; 
            font-size: 14px; outline: none; transition: 0.3s;
        }
        .search-input:focus { border-color: #007bff; box-shadow: 0 0 0 3px rgba(0,123,255,0.1); }
        .btn-search { 
            padding: 10px 20px; background-color: #007bff; color: white; border: none; 
            border-radius: 8px; cursor: pointer; font-weight: 600; transition: 0.2s;
        }
        .btn-search:hover { background-color: #0056b3; }

        /* 테이블 디자인 */
        .member-table { width: 100%; border-collapse: collapse; margin-top: 10px; }
        .member-table th { background-color: #f1f3f5; padding: 15px; text-align: center; font-weight: 600; color: #495057; border-bottom: 2px solid #dee2e6; }
        .member-table td { padding: 15px; text-align: center; border-bottom: 1px solid #eee; vertical-align: middle; color: #555; }
        .member-table tbody tr:hover { background-color: #fcfcfc; }

        /* 버튼 디자인 */
        .btn-delete { 
            padding: 6px 14px; background-color: #fff5f5; color: #fa5252; border: 1px solid #ffa8a8; 
            border-radius: 6px; cursor: pointer; font-size: 13px; font-weight: 600; transition: 0.2s;
        }
        .btn-delete:hover { background-color: #fa5252; color: white; }

        /* 페이징 (주문관리와 동일하게 필요시 추가) */
        .pagination { display: flex; justify-content: center; gap: 5px; margin-top: 30px; }
        .pagination a, .pagination strong { 
            display: inline-block; width: 35px; height: 35px; line-height: 35px; 
            text-align: center; border-radius: 6px; border: 1px solid #dee2e6;
            text-decoration: none; color: #495057; font-size: 14px;
        }
        .pagination strong { background-color: #007bff; color: white; border-color: #007bff; }
    </style>
</head>
<body>
<div class="wrapper">
    <div class="sidebar">
        <h2>DOC5 Admin</h2>
        <a href="${pageContext.request.contextPath}/manager/index.do">메인 대시보드</a> 
        <a href="${pageContext.request.contextPath}/manager/member.do" class="active">회원 관리 시스템</a>
        <a href="${pageContext.request.contextPath}/manager/shop.do">실시간 주문 관리</a>
    </div>

    <div class="content">
        <div class="header" style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 30px;">
            <h1 class="page-title" style="margin: 0; font-size: 24px;">회원 관리</h1>
            <div class="admin-menu">
                <span style="font-weight: 600; margin-right: 15px;">📍 ${adminBranchName.branchName}</span>
                <a href="${pageContext.request.contextPath}/manager/branchLogin.do" style="color: #fa5252; text-decoration: none; font-weight: 600;">로그아웃</a>
            </div>
        </div>

        <div class="section">
            <div class="section-title">
                전체 회원 목록
                <div class="search-area">
                    <form action="${pageContext.request.contextPath}/manager/member.do" method="GET" class="search-form">
                        <input type="text" name="searchWord" class="search-input" placeholder="ID 또는 이름으로 검색" value="${param.searchWord}">
                        <button type="submit" class="btn-search">검색</button>
                    </form>
                </div>
            </div>

            <table class="member-table">
                <thead>
                    <tr>
                        <th>회원 ID</th>
                        <th>이름</th>
                        <th>전화번호</th>
                        <th>가입일</th>
                        <th>계정 관리</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty memberList}">
                            <c:forEach var="member" items="${memberList}">
                                <tr>
                                    <td style="font-weight: 600; color: #333;">${member.userId}</td>
                                    <td>${member.name}</td>
                                    <td>${member.phone}</td>
                                    <td style="color: #888; font-size: 13px;">${member.regDt}</td>
                                    <td>
                                        <button onclick="confirmDelete('${member.userId}')" class="btn-delete">회원 삭제</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="5" style="padding: 50px 0; color: #999;">검색 결과 또는 등록된 회원이 없습니다.</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>

            <c:if test="${not empty vo}">
                <div class="pagination">
                    <c:if test="${vo.pre}"><a href="member.do?pageNo=${vo.startNo - 1}&searchWord=${param.searchWord}">&laquo;</a></c:if>
                    <c:forEach var="i" begin="${vo.startNo}" end="${vo.endNo}">
                        <c:choose>
                            <c:when test="${i == vo.pageNo}"><strong>${i}</strong></c:when>
                            <c:otherwise><a href="member.do?pageNo=${i}&searchWord=${param.searchWord}">${i}</a></c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${vo.next}"><a href="member.do?pageNo=${vo.endNo + 1}&searchWord=${param.searchWord}">&raquo;</a></c:if>
                </div>
            </c:if>
        </div>
    </div>
</div>

<form id="deleteForm" action="${pageContext.request.contextPath}/manager/deleteMember.do" method="POST">
    <input type="hidden" name="userId" id="deleteUserId">
</form>

<script>
    function confirmDelete(userId) {
        if (confirm(`정말로 회원 [\${userId}] 님을 영구적으로 삭제하시겠습니까?\n삭제된 데이터는 복구할 수 없습니다.`)) {
            document.getElementById('deleteUserId').value = userId;
            document.getElementById('deleteForm').submit();
        }
    }
</script>

</body>
</html>