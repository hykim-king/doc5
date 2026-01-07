<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/header/header.jsp" %>
	<div class="cont_wrap menu_wrap">
		<div class="cont">		
<style>
.form-floating {margin:5px 0;}
.form-floating .form-control{border-radius: 5px !important;}
.form-floating #floatingPassword {marign:0;}


.board-view {
	width:95%;
    min-width: 410px;
    max-width: 900px;
    padding: 1rem;
    margin: auto !important;
}

.board-view .board_title {
	border-bottom: 2px solid #fdd000;
	box-shadow:0px 0px 5px 0px #eee;
	font-weight: 400 !important;
	margin-bottom: 1rem !important;
	font-size: calc(1.325rem + .9vw);
	padding:10px;
	/* border-radius: 0 0 20px 20px ; */
	
}


.board-view ul {
	list-style:none;
	padding:0;
	margin:0;
}	

.board-title{
	font-size: 16px;
	text-align: left;
	border-bottom: 1px solid #eee;
	padding:15px 0px;
	line-height:40px;
	text-indent: 20px;
	font-weight:blod;
}
.board-contents {
	font-size: 14px;
	text-align: left;
	border-bottom: 2px solid #eee;
	padding:15px 0px;
	line-height:40px;
	text-indent: 20px;
	min-height:300px;
	
}
.btn-container {
	padding-20px;
	margin-top:10px;
	text-align:right;
}

.btn-container button{
	padding: 10px 20px;
	border-radius: 5px 5px ;
	font-size:15px;
	border:1px solid #eee;
	/* background-color:#a9caff; */
}
</style>
		    <div class="board-view">
		        <div class="board_title">공지사항</div>
		        <div class="board-title">${vo.subject}</div>
		        
		        <div class="board-contents">${vo.contents}</div>
		        <div class="btn-container">
		        	<button type="button" data-pageNo="${pageNo}" onclick="goBoardList('${code}', '${pageNo}')">목록</button>
		        </div>
		    </div>
		</div>
		     
	</div>
<script type="text/javascript">
function goBoardList(code, pageNo){
	location.href="/board/boardList.do?code="+code+"&pageNo="+pageNo;
}

</script>
<%@ include file="/WEB-INF/views/common/footer/footer.jsp" %>