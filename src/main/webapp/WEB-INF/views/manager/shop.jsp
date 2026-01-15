<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>DOC5 관리자 - 미처리 주문</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin.css">
    <style>
        /* 기본 레이아웃 */
        .content { padding: 30px; background-color: #f8f9fa; min-height: 100vh; }
        .section { background: white; padding: 25px; border-radius: 12px; box-shadow: 0 4px 6px rgba(0,0,0,0.05); }
        .tab-menu { margin-bottom: 25px; border-bottom: 2px solid #eee; display: flex; gap: 10px; }
        .tab-menu button { padding: 12px 24px; cursor: pointer; border: none; background: none; font-size: 15px; font-weight: 600; color: #666; transition: 0.3s; position: relative; top: 2px; }
        .tab-menu button.active { color: #007bff; border-bottom: 3px solid #007bff; }
        
        /* 테이블 설정 */
        .order-table { width: 100%; border-collapse: collapse; margin-top: 10px; }
        .order-table th { background-color: #f1f3f5; padding: 15px; text-align: center; border-bottom: 2px solid #dee2e6; color: #495057; }
        .order-table td { padding: 15px; text-align: center; border-bottom: 1px solid #eee; vertical-align: middle; }

        /* 상태 뱃지 */
        .badge { padding: 5px 10px; border-radius: 20px; font-size: 12px; font-weight: bold; }
        .bg-wait { background: #e9ecef; color: #495057; }
        .bg-ing { background: #fff4e6; color: #fd7e14; }

       
        .btn { 
            padding: 6px 14px; 
            border-radius: 6px; 
            font-size: 13px; 
            font-weight: 700; 
            cursor: pointer; 
            transition: all 0.2s; 
            display: inline-flex; 
            align-items: center; 
            justify-content: center;
            border: 1px solid; /* 테두리 강조 */
        }

        /* 접수하기 (파란색 계열 스타일) */
        .btn-accept { 
            background-color: #f0f7ff; 
            color: #007bff; 
            border-color: #b3d7ff; 
        }
        .btn-accept:hover { background-color: #007bff; color: white; }

        /* 준비완료 (초록색 계열 스타일) */
        .btn-ready { 
            background-color: #f0fff4; 
            color: #28a745; 
            border-color: #b2f2bb; 
        }
        .btn-ready:hover { background-color: #28a745; color: white; }

        /* 취소 (빨간색 ) */
        .btn-cancel { 
            background-color: #fff5f5; 
            color: #fa5252; 
            border-color: #ffa8a8; 
            margin-left: 5px;
        }
        .btn-cancel:hover { background-color: #fa5252; color: white; }
        /* -------------------------------------- */

        /* 페이징 */
        .pagination { display: flex; justify-content: center; gap: 5px; margin-top: 30px; }
        .pagination a, .pagination strong { display: inline-block; width: 35px; height: 35px; line-height: 35px; text-align: center; border-radius: 6px; border: 1px solid #dee2e6; text-decoration: none; color: #495057; }
        .pagination strong { background-color: #007bff; color: white; border-color: #007bff; }
    </style>
</head>
<body>
<div class="wrapper">
    <div class="sidebar">
        <h2>DOC5 Admin</h2>
        <a href="${pageContext.request.contextPath}/manager/index.do">메인 대시보드</a> 
        <a href="${pageContext.request.contextPath}/manager/member.do">회원 관리 시스템</a>
        <a href="${pageContext.request.contextPath}/manager/shop.do" class="active">실시간 주문 관리</a>
    </div>

    <div class="content">
        <div class="header" style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 30px;">
            <h1 class="page-title" style="margin: 0; font-size: 24px;">실시간 주문 관리</h1>
            <div class="admin-menu">
                <span style="font-weight: 600; margin-right: 15px;">📍 ${adminBranchName.branchName}</span>
                <a href="${pageContext.request.contextPath}/manager/branchLogin.do" style="color: #fa5252; text-decoration: none; font-weight: 600;">로그아웃</a>
            </div>
        </div>

        <div class="section">
            <div class="tab-menu">
                <button class="active">미처리 주문 <span style="color:#007bff;"></span></button>
                <button onclick="location.href='allOrders.do'">전체 주문 내역</button>
            </div>

            <table class="order-table">
                <thead>
                    <tr>
                        <th>주문번호</th>
                        <th>주문일시</th>
                        <th>상품 정보</th>
                        <th>총 결제금액</th>
                        <th>현재상태</th>
                        <th>주문처리</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty pendingOrders}">
                            <c:forEach var="order" items="${pendingOrders}">
                                <tr>
                                    <td><a href="shopDetail.do?orderNo=${order.orderNo}" style="color: #007bff; text-decoration: none; font-weight: 600;">${order.orderNo}</a></td>
                                    <td style="color: #888; font-size: 13px;">${order.regDt}</td>
                                    <td style="font-weight: 500;">${order.orderName}</td>
                                    <td style="font-weight: 600;"><fmt:formatNumber value="${order.settleTotalPrice}" type="currency" currencySymbol="₩"/></td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${order.orderStep == 'O'}"><span class="badge bg-wait">접수대기</span></c:when>
                                            <c:when test="${order.orderStep == 'P'}"><span class="badge bg-ing">준비중</span></c:when>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:if test="${order.orderStep == 'O'}">
                                            <button type="button" class="btn btn-accept" onclick="processOrder('${order.orderNo}', 'P')">접수하기</button>
                                            <button type="button" class="btn btn-cancel" onclick="processOrder('${order.orderNo}', 'C')">취소</button>
                                        </c:if>
                                        <c:if test="${order.orderStep == 'P'}">
                                            <button type="button" class="btn btn-ready" onclick="processOrder('${order.orderNo}', 'S')">준비완료</button>
                                            <button type="button" class="btn btn-cancel" onclick="processOrder('${order.orderNo}', 'C')">취소</button>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr><td colspan="6" style="padding: 50px 0; color: #999;">새로운 주문 내역이 없습니다.</td></tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>

            <div class="pagination">
                <c:if test="${vo.pre}"><a href="shop.do?pageNo=${vo.startNo - 1}">&laquo;</a></c:if>
                <c:forEach var="i" begin="${vo.startNo}" end="${vo.endNo}">
                    <c:choose>
                        <c:when test="${i == vo.pageNo}"><strong>${i}</strong></c:when>
                        <c:otherwise><a href="shop.do?pageNo=${i}">${i}</a></c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${vo.next}"><a href="shop.do?pageNo=${vo.endNo + 1}">&raquo;</a></c:if>
            </div>
        </div>
    </div>
</div>

<form id="processOrderForm" action="${pageContext.request.contextPath}/manager/processOrder.do" method="POST">
    <input type="hidden" name="orderNo" id="processOrderNo">
    <input type="hidden" name="step" id="processStep">
</form>

<script>
    function processOrder(orderNo, step) {
        let actionMsg = (step === 'P') ? "접수" : (step === 'S') ? "준비완료" : "취소";
        if (confirm(`주문번호 [\${orderNo}]를 \${actionMsg} 처리하시겠습니까?`)) {
            document.getElementById('processOrderNo').value = orderNo;
            document.getElementById('processStep').value = step;
            document.getElementById('processOrderForm').submit();
        }
    }
</script>
</body>
</html>