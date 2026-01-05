<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
    <%@ include file="/WEB-INF/views/manager/common/header.jsp" %>
        <form action="doRetrieve.do" method="get"> 
            <table>
                <tr>
                    <th>조회 기준</th><th>입력</th><th>검색</th>
                </tr>
                <tr>
                    <td>
                        <select name="searchType">
                            <option value="id">아이디</option> 
                        </select>
                    </td>
                    <td><input type="text" name="searchKeyword" placeholder="검색어" ></td>
                    <td>
                        <input type="hidden" name="pageNo" value="1">
                        <input type="hidden" name="pageSize" value="10">
                        <button type="submit">조회</button>
                    </td>
                </tr>
            </table>
        </form>
        <table>
            <tr><th>회원ID</th><th>이름</th><th>전화번호</th><th>가입일</th><th>관리</th></tr> 
            <tr>
                <td>user01</td><td>홍길동</td><td>010-1234-5678</td><td>2025-01-01</td>
                <td>
                    <button onclick="deleteMember('user01')">삭제</button> 
                </td>
            </tr>
            <tr>
                <td>test104</td><td>김테스트</td><td>010-9876-5432</td><td>2025-11-20</td>
                <td>
                    <button onclick="deleteMember('test104')">삭제</button>
                </td>
            </tr>
            </table>
        
        <div class="pagination">
            <a href="?pageNo=1&pageSize=10">&laquo;</a>
            <a href="?pageNo=1&pageSize=10" class="active">1</a>
            <a href="?pageNo=2&pageSize=10">2</a>
            <a href="?pageNo=3&pageSize=10">3</a>
            <a href="?pageNo=4&pageSize=10">&raquo;</a>
        </div>
        
<%@ include file="/WEB-INF/views/manager/common/footer.jsp" %>        