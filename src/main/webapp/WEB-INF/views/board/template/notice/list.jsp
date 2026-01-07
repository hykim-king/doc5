<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/header/header.jsp" %>
	<div class="cont_wrap menu_wrap">
		<div class="cont">		
<style>
.form-floating {margin:5px 0;}
.form-floating .form-control{border-radius: 5px !important;}
.form-floating #floatingPassword {marign:0;}


.board-list {
	width:95%;
    min-width: 410px;
    max-width: 900px;
    padding: 1rem;
    margin: auto !important;
  	padding-bottom:180px;
    
}

.board-list .board_title {
	border-bottom: 2px solid #fdd000;
	box-shadow:0px 0px 5px 0px #eee;
	font-weight: 400 !important;
	margin-bottom: 1rem !important;
	font-size: calc(1.325rem + .9vw);
	padding:10px;
	/* border-radius: 0 0 20px 20px ; */
	
}

.board-list ul {
	list-style:none;
	padding:0;
	margin:0;
}	

.board-list .board-list-box ul li {
	font-size: 16px;
	text-align: left;
	border-bottom: 1px solid #eee;
	padding:15px 0px;
	line-height:40px;
	text-indent: 20px;

}



제시해주신 HTML 구조에 딱 맞는 CSS 코드를 작성해 드립니다. 클래스명 .board-paging을 활용하여 다른 요소와 겹치지 않게 설계했으며, 글자와 버튼 모두 정중앙에 위치하도록 설정했습니다.1. 세련된 버튼형 중앙 정렬 CSS이 코드는 플렉스박스($Flexbox$)를 사용하여 리스트를 가로로 나열하고 화면 중앙에 배치합니다.CSS/* 페이징 컨테이너 */
.board-paging {
    text-align: center; /* 인라인 요소 중앙 정렬 보조 */
    margin: 20px 0;
    
}

.board-paging ul {
    display: flex;             /* 가로 나열 */
    justify-content: center;   /* 가로 중앙 정렬 */
    align-items: center;       /* 세로 중앙 정렬 */
    list-style: none;          /* 불렛 점 제거 */
    padding: 15px 0;
    margin: 0;
    gap: 8px;                  /* 버튼 사이 간격 */
    
}

.board-paging ul li {
	
	display: flex;
    justify-content: center;   /* 글자 가로 중앙 */
    align-items: center;       /* 글자 세로 중앙 */
    min-width: 30px;           /* 최소 너비 설정으로 정사각형/직사각형 유지 */
    height: 35px;
    padding: 0 5px;
    text-decoration: none;
    color: #666;
    border: 1px solid #dee2e6;
    border-radius: 4px;        /* 살짝 둥근 모서리 */
    font-size: 14px;
    background-color: #fff;
    transition: all 0.2s;
    text-align:center;
}

.board-paging ul li a {
    
}

.board-paging ul li.on {
    background-color: #ececec;
    /* color: #fff; */
    border-color: #ccc;
}

/* 마우스 호버 효과 */
.board-paging ul li:hover {
    background-color: #007bff;
    color: #fff;
    border-color: #007bff;
}

/* 이전/다음 버튼 강조 (선택 사항) */
.board-paging ul li.pagingPrev,
.board-paging ul li.pagingNext {
    background-color: #f8f9fa !important;
    font-weight: bold !important;
}  
</style>
		    <div class="board-list">


				<div class="board_title">
					공지사항
				</div>
		        <div class="board-list-box">
			        <ul>
			        	<c:choose>
			        		<c:when test="${empty list} }">
			        		<li>공지사항 게시글이 없습니다.</li>
			        		</c:when>
			        		<c:otherwise>
			        			<c:forEach var="vo" items="${list}">
			        			<li><a href="/board/boardView.do?code=notice&seq=${vo.seq}">${vo.subject}</a></li>
			        			</c:forEach>
			        		</c:otherwise>
			        	</c:choose>
			        </ul>
		        </div>
		        <div class="board-paging">${pageCode}</div>
		    </div>
		    
		</div>
		     
	</div>
<%@ include file="/WEB-INF/views/common/footer/footer.jsp" %>