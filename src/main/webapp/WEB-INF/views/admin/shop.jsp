<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
    
  <%@ include file="/WEB-INF/views/manager/common/header.jsp" %>
  
<h1>회원별 주문 내역 조회</h1>
        
        <form id="memberOrderSearchForm" action="doUserOrderSearch.do" method="get">
            <div class="search-area">
                <input type="hidden" id="branchCodeParam" name="branchCodeParam" value="B001"> 
                
                <label for="memberIdInput">회원 ID:</label>
                <input type="text" id="memberIdInput" name="userIdParam" placeholder="U001" required>
                <button type="submit" onclick="searchOrders(event)">조회</button>
            </div>
        </form>

        <table id="userOrderTable">
            <tr>
                <th>주문번호</th>
                <th>주문일시</th>
                <th>회원ID</th>
                <th>총 상품 가격</th> 
                <th>처리</th> </tr>
            <tr>
                <td>O025</td>
                <td>2025-12-22 10:30:00</td>
                <td>U001</td>
                <td>7,500원</td> <td>S (완료)</td>
            </tr>
            <tr>
                <td>O026</td>
                <td>2025-12-22 10:45:00</td>
                <td>U002</td>
                <td>12,000원</td>
                <td>S (완료)</td>
            </tr>
        </table>
        
        <h2>미완료 주문</h2>
        
        <table id="adminPendingOrderTable">
            <tr>
                <th>주문번호</th>
                <th>주문일시</th>
                <th>회원ID</th> <th>총 상품 가격</th> 
                <th>처리</th> </tr>
            <tr>
                <td>ORD003</td>
                <td>2025-12-22 11:00:00</td>
                <td>U005</td> <td>15,500원</td> 
                <td>
                    P (대기)
                    <button onclick="processOrder('ORD003', document.getElementById('branchSelector').value)">완료</button>
                    <button onclick="processOrder('ORD003', document.getElementById('branchSelector').value)">취소</button>
                </td>
            </tr>
            <tr>
                <td>ORD004</td>
                <td>2025-12-22 11:15:00</td>
                <td>U003</td>
                <td>8,000원</td> 
                <td>
                    P (대기)
                    <button onclick="processOrder('ORD004', document.getElementById('branchSelector').value)">완료</button>
                    <button onclick="processOrder('ORD003', document.getElementById('branchSelector').value)">취소</button>
                </td>
            </tr>
        </table>
        
            <div class="pagination" style="margin-top: 15px;">
                <a href="?pageNo=1">&laquo;</a>
                <a href="?pageNo=1" class="active">1</a>
                <a href="?pageNo=2">2</a>
                <a href="?pageNo=3">&raquo;</a>
            </div>
  
  <%@ include file="/WEB-INF/views/manager/common/footer.jsp" %>