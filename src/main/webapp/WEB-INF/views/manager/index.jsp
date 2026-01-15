<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>DOC5 관리자 페이지</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin.css">
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
            <div class="page-title"> 관리자 메인</div>
            <div class="admin-menu">
               <a>[${adminBranchName.branchName}]</a>
               <a href="${pageContext.request.contextPath}/manager/branchLogin.do" class="logout-link">로그아웃</a>
            </div>
        </div>

        <div class="section">
            <h2> 매출 현황 </h2>
            <table>
                <tr><th>항목</th><th>내용</th></tr>
                <tr>
                    <td>조회 기준 지점</td>
                    <td id="currentBranchName">${adminBranchName.branchName}</td>
                </tr>
                <tr>
                    <td>총 매출액</td>
                    <td id="totalSales">
                        <fmt:formatNumber value="${salesStatus.totalSales}" type="currency" currencySymbol="₩" groupingUsed="true"/>
                    </td>
                </tr>
                <tr>
                    <td>주문 건수 (성공)</td>
                    <td id="totalOrderCount"><fmt:formatNumber value="${salesStatus.totalOrderCount}" pattern="#,###"/>건</td>
                </tr>
            </table>
        </div>
        </div>
</div>
</body>
</html>