<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>DOC5 관리자 - 메인 대시보드</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin.css">
    <style>
        /* 기본 레이아웃 및 배경 */
        .content { padding: 30px; background-color: #f8f9fa; min-height: 100vh; }
        
        /* 대시보드 카드 그리드 */
        .dashboard-grid { 
            display: grid; 
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr)); 
            gap: 20px; 
            margin-top: 20px;
        }

        /* 지표 카드 디자인 */
        .metric-card {
            background: white;
            padding: 25px;
            border-radius: 12px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.05);
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            transition: transform 0.2s ease;
        }
        .metric-card:hover { transform: translateY(-5px); }

        .metric-label { font-size: 14px; color: #888; font-weight: 600; margin-bottom: 10px; }
        .metric-value { font-size: 28px; font-weight: 700; color: #333; }
        .metric-subtext { font-size: 13px; color: #666; margin-top: 10px; }
        
        /* 특정 색상 강조 */
        .text-primary { color: #007bff; }
        .text-success { color: #28a745; }

        /* 섹션 구분 */
        .section-header { margin-top: 40px; margin-bottom: 20px; }
        .section-header h2 { font-size: 20px; font-weight: 700; color: #333; }

        /* 하단 상세 테이블 */
        .info-section { 
            background: white; 
            padding: 25px; 
            border-radius: 12px; 
            box-shadow: 0 4px 6px rgba(0,0,0,0.05); 
            margin-top: 20px;
        }
        .info-table { width: 100%; border-collapse: collapse; }
        .info-table th { text-align: left; padding: 15px; border-bottom: 2px solid #eee; color: #444; font-weight: 600; }
        .info-table td { padding: 15px; border-bottom: 1px solid #f1f1f1; color: #666; }
    </style>
</head>
<body>
<div class="wrapper">
    <div class="sidebar">
        <h2>DOC5 Admin</h2>
        <a href="${pageContext.request.contextPath}/manager/index.do" class="active">메인 대시보드</a>
        <a href="${pageContext.request.contextPath}/manager/member.do">회원 관리 시스템</a>
        <a href="${pageContext.request.contextPath}/manager/shop.do">실시간 주문 관리</a>
    </div>

    <div class="content">
        <div class="header" style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 30px;">
            <h1 class="page-title" style="margin: 0; font-size: 24px;">관리자 메인</h1>
            <div class="admin-menu">
                <span style="font-weight: 600; margin-right: 15px;">📍 ${adminBranchName.branchName}</span>
                <a href="${pageContext.request.contextPath}/manager/branchLogin.do" style="color: #fa5252; text-decoration: none; font-weight: 600;">로그아웃</a>
            </div>
        </div>

        <div class="section-header">
            <h2>오늘의 매출 현황</h2>
        </div>
        
        <div class="dashboard-grid">
            <div class="metric-card">
                <div class="metric-label">총 매출액</div>
                <div class="metric-value text-primary">
                    <fmt:formatNumber value="${salesStatus.totalSales}" type="currency" currencySymbol="₩" groupingUsed="true"/>
                </div>
                <div class="metric-subtext">실제 결제가 완료된 주문의 합계입니다.</div>
            </div>

            <div class="metric-card">
                <div class="metric-label">완료된 주문 건수</div>
                <div class="metric-value text-success">
                    <fmt:formatNumber value="${salesStatus.totalOrderCount}" pattern="#,###"/> <span style="font-size: 18px;">건</span>
                </div>
                <div class="metric-subtext">성공적으로 처리된 누적 주문 수입니다.</div>
            </div>

            <div class="metric-card">
                <div class="metric-label">현재 관리 지점</div>
                <div class="metric-value" style="font-size: 24px;">
                    ${adminBranchName.branchName}
                </div>
                <div class="metric-subtext">현재 로그인된 지점의 데이터입니다.</div>
            </div>
        </div>

        <div class="section-header">
            <h2>지점 상세 정보</h2>
        </div>
        
        <div class="info-section">
            <table class="info-table">
                <thead>
                    <tr>
                        <th style="width: 30%;">항목</th>
                        <th>상세 내용</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>조회 기준 지점명</td>
                        <td style="font-weight: 600;">${adminBranchName.branchName}</td>
                    </tr>
                    <tr>
                        <td>데이터 업데이트 시각</td>
                        <td id="currentTime"></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script>
    // 현재 시간을 실시간으로 표시하는 스크립트
    function updateTime() {
        const now = new Date();
        document.getElementById('currentTime').innerText = now.toLocaleString();
    }
    updateTime();
    setInterval(updateTime, 1000);
</script>

</body>
</html>