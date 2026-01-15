<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <!DOCTYPE html>

<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>DOC5 관리자 - 주문 관리</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin.css">
    <style>
       
        /* 버튼 공통 */
      .btn { padding: 5px 12px; cursor: pointer; border: none; border-radius: 4px; color: white; font-weight: bold; margin-right: 2px; }
    
      /* 버튼 색상 */

       .btn-accept  { background-color: #007bff; } /* 접수(파랑) */
       .btn-ready   { background-color: #28a745; } /* 준비완료(초록) */
       .btn-cancel  { background-color: #dc3545; } /* 취소(빨강) */
       
       
        .btn-success { background-color: #28a745; color: white; border: none; padding: 5px 10px; cursor: pointer; }
        .btn-cancel { background-color: #dc3545; color: white; border: none; padding: 5px 10px; cursor: pointer; }
       
       
    /* 상태 텍스트 색상 */
       .order-step-P { color: #fd7e14; font-weight: bold; } /* 준비중 */
       .order-step-S { color: #28a745; font-weight: bold; } /* 완료 */
       .order-step-C { color: #dc3545; font-weight: bold; } /* 취소 */
       
       
        .tab-menu { margin-bottom: 20px; }
        .tab-menu button { padding: 10px 20px; cursor: pointer; border: 1px solid #ccc; background-color: #f8f9fa; }
        .tab-menu button.active { background-color: #007bff; color: white; border-color: #007bff; }
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
            <div class="page-title">주문 관리</div>
<div class="admin-menu">
    <a>[${adminBranchName.branchName}]</a>
    <a href="${pageContext.request.contextPath}/manager/branchLogin.do" class="logout-link">로그아웃</a>
</div>
        </div>

        <div class="section">
            <h2>주문 현황</h2>

            <div class="tab-menu">
                <button id="pendingTab" class="active" onclick="showOrders('pending')">미처리 주문 (${fn:length(pendingOrders)})</button>
                <button id="allTab" onclick="showOrders('all')">전체 주문</button>
            </div>

            <div id="pendingOrdersDiv">
                <h3>미처리 주문 (대기/(P or O))</h3>
                <table class="order-table">
                    <thead>
                        <tr>
                            <th>주문 번호</th>
                            <th>주문 일시</th>
                            <th>상품명</th>
                            <th>주문 금액(총)</th>
                            <th>상태</th>
                            <th>처리</th>
                        </tr>
                    </thead>
<tbody>
    <c:choose>
        <c:when test="${not empty pendingOrders}">
            <c:forEach var="order" items="${pendingOrders}">
                <tr>
                    <td><a href="${pageContext.request.contextPath}/manager/shopDetail.do?orderNo=${order.orderNo}">${order.orderNo}</a></td>
                    <td>${order.regDt}</td>
                    <td>${order.orderName}</td>
                    <td>
                        <fmt:formatNumber value="${order.settleTotalPrice}" type="currency" currencySymbol="₩" groupingUsed="true"/>
                    </td>
                    <td class="order-step-${order.orderStep}">
                        <c:choose>
                            <c:when test="${order.orderStep == 'O'}">대기</c:when>
                            <c:when test="${order.orderStep == 'P'}">준비중</c:when>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <%-- 상태가 'O' (주문완료/접수대기) 일 때 --%>
                            <c:when test="${order.orderStep == 'O'}">
                                <button class="btn btn-accept" onclick="processOrder('${order.orderNo}', 'P')">접수</button>
                                <button class="btn btn-cancel" onclick="processOrder('${order.orderNo}', 'C')">취소</button>
                            </c:when>
                            
                            <%-- 상태가 'P' (준비중) 일 때 --%>
                            <c:when test="${order.orderStep == 'P'}">
                                <button class="btn btn-ready" onclick="processOrder('${order.orderNo}', 'S')">준비완료</button>
                                <button class="btn btn-cancel" onclick="processOrder('${order.orderNo}', 'C')">취소</button>
                            </c:when>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <tr><td colspan="6">미처리 주문이 없습니다.</td></tr>
        </c:otherwise>
    </c:choose>
</tbody>
                </table>
            </div>

            <div id="allOrdersDiv" style="display: none;">
                <h3>전체 주문 내역</h3>
                <table class="order-table">
                    <thead>
                        <tr>
                            <th>주문 번호</th>
                            <th>주문 일시</th>
                            <th>상품명</th>
                            <th>주문 금액(총)</th>
                            <th>상태</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${not empty allOrders}">
                                <c:forEach var="order" items="${allOrders}">
                                    <tr>
                                        <td><a href="${pageContext.request.contextPath}/manager/shopDetail.do?orderNo=${order.orderNo}">${order.orderNo}</a></td>
                                        <td>${order.regDt}</td>
                                        <td>${order.orderName}</td>
                                        <td>
                                            <fmt:formatNumber value="${order.settleTotalPrice}" type="currency" currencySymbol="₩" groupingUsed="true"/>
                                        </td>
                                        <td class="order-step-${order.orderStep}">
                                            <c:choose>
                                                <c:when test="${order.orderStep == 'S'}">완료</c:when>
                                                <c:when test="${order.orderStep == 'P'}">준비중</c:when>
                                                <c:when test="${order.orderStep == 'C'}">주문취소</c:when>
                                                <c:when test="${order.orderStep == 'O'}">주문완료</c:when>
                                                <c:otherwise>(${order.orderStep})</c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="5">등록된 주문 내역이 없습니다.</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>
            
        </div>
    </div>
</div>

<form id="processOrderForm" action="${pageContext.request.contextPath}/manager/processOrder.do" method="POST">
    <input type="hidden" name="orderNo" id="processOrderNo">
    <input type="hidden" name="step" id="processStep">
</form>

<script>
    // 탭 전환 기능
    function showOrders(tab) {
        document.getElementById('pendingOrdersDiv').style.display = 'none';
        document.getElementById('allOrdersDiv').style.display = 'none';
        
        document.getElementById('pendingTab').classList.remove('active');
        document.getElementById('allTab').classList.remove('active');

        if (tab === 'pending') {
            document.getElementById('pendingOrdersDiv').style.display = 'block';
            document.getElementById('pendingTab').classList.add('active');
        } else if (tab === 'all') {
            document.getElementById('allOrdersDiv').style.display = 'block';
            document.getElementById('allTab').classList.add('active');
        }
    }

    // 주문 처리 기능 
function processOrder(orderNo, step) {
    var actionText = "";
    if (step === 'P') actionText = "접수(준비중)";
    else if (step === 'S') actionText = "준비완료(픽업대기)";
    else if (step === 'C') actionText = "주문취소";

    if (confirm("주문 번호 [" + orderNo + "]를 " + actionText + " 처리하시겠습니까?")) {
        document.getElementById('processOrderNo').value = orderNo;
        document.getElementById('processStep').value = step;
        document.getElementById('processOrderForm').submit();
    }
}
</script>

</body>
</html>