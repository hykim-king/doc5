<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
    
  <%@ include file="/WEB-INF/views/manager/common/header.jsp" %>
  
  			<h2>📊 매출 현황 (최근 30일)</h2>
            <table>
                <tr><th>항목</th><th>내용</th></tr>
                <tr><td>조회 기준 지점</td><td id="currentBranchName">강남 본점</td></tr>
                <tr><td>총 매출액</td><td id="totalSales">₩ 12,530,000</td></tr>
                <tr><td>주문 건수 (성공)</td><td id="totalOrderCount">540건</td></tr>
            </table>
  
  <%@ include file="/WEB-INF/views/manager/common/footer.jsp" %>