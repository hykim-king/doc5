<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<style>
.fullpage-intext {
	font-size: 24px;
	text-align: left;
	margin:20px 0px 30xp 0px;
	line-height:50px;
	text-indent: 30px;
}
.fullpage-stamp {
	font-size: 20px;
	text-align: center;
	margin-top: 10px;
	margin-bottom: 30px;
	border-radius: 20px ;
	background-color: #f5f5f5;
	line-height:70px;
}
.fullpage-menu ul {
	list-style:none;
	padding:0;
	margin:0;
}	

.fullpage-menu ul li {
	font-size: 20px;
	text-align: left;
	border-bottom: 1px solid #eee;
	padding:15px 0px;
	line-height:40px;
	text-indent: 20px;
	border-radius: 20px ;
}

.btn-regist {
	margin-top:10px;
    --bs-btn-color: #fff;
    --bs-btn-bg: #33b4ff;
    --bs-btn-border-color: #0d6efd;
    --bs-btn-hover-color: #fff;
    --bs-btn-hover-bg: #188ccf;
    --bs-btn-hover-border-color: #0a58ca;
    --bs-btn-focus-shadow-rgb: 49, 132, 253;
    --bs-btn-active-color: #fff;
    --bs-btn-active-bg: #0a58ca;
    --bs-btn-active-border-color: #0a53be;
    --bs-btn-active-shadow: inset 0 3px 5px rgba(0, 0, 0, 0.125);
    --bs-btn-disabled-color: #fff;
    --bs-btn-disabled-bg: #0d6efd;
    --bs-btn-disabled-border-color: #0d6efd;
}

.text-password-find{
	padding-top:10px;
	text-align:right;
}
</style>

<!-- The Modal Start-->
 <!-- 통합검색 Modal - FullPage -->
<div class="modal fade up" id="mSearch">
	<div class="modal-dialog modal-fullscreen">
		<div class="modal-content">
			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">메뉴 검색</h4>
				<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
			</div>

			<!-- Modal body -->
			<div class="modal-body">
				<div class="modal-search-body">
					<form action="/goods/goodsSearch.do" method="get">
					<div class="search-container-inputBox">
						<ul>
							<li><input type="text" class="form-control" name="keyword" required="required" placeholder="검색하실 상품을 입력하세요"></li>
							<li><button type="submit" class="btn btn-secondary">검색</button></li>
						</ul>
					</div>
					</form>
					<div class="search-keword-recommend">
						<h4 class="modal-sub-title">추천 검색어</h4>
						<div>
							<div><a href="/goods/goodsSearch.do?keyword=아메리카노">아메리카노</a></div>
							<div><a href="/goods/goodsSearch.do?keyword=디카페인">디카페인</a></div>
						</div>
					</div>
					<div class="search-keword">
						<h4 class="modal-sub-title">최근 검색어</h4>
						<div class="modal-search-con-list">
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
</div>

<!-- 마이페이지 Modal - FullPage -->
<div class="modal fade up" id="mMypage">
	<div class="modal-dialog modal-fullscreen">
		<div class="modal-content">
			
			<!-- Modal Header -->
			<div class="modal-header">
			<h4 class="modal-title">마이페이지</h4>
			<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
			</div>

			<!-- Modal body -->
			<div class="modal-body">
			Modal body..
			</div>
		</div>
	</div>
</div>

<!-- 공지사항  Modal - FullPage -->
<div class="modal fade up" id="mNoticeBoard">
	<div class="modal-dialog modal-fullscreen">
		<div class="modal-content">
			
			<!-- Modal Header -->
			<div class="modal-header">
			<h4 class="modal-title">공지사항</h4>
			<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
			</div>

			<!-- Modal body -->
			<div class="modal-body">
			Modal body..
			</div>
		</div>
	</div>
</div>

<!-- 전체페이지 Modal - FullPage -->
<div class="modal fade up" id="fullPage">
	<div class="modal-dialog modal-fullscreen">
		<div class="modal-content">
			
			<!-- Modal Header -->
			<div class="modal-header">
			<h4 class="modal-title">전체메뉴</h4>
			<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
			</div>

			<!-- Modal body -->
			<div class="modal-body">
				<!-- <div class="fullpage-intext">DOC5님 반가워요 👋</div>-->
				<div class="fullpage-stamp">
					<c:choose>
					<c:when test="${empty sessionScope.sessionUser.userId}">
						로그인 해주세요.
					</c:when>
					<c:otherwise>
						${sessionScope.sessionUser.name}님 반가워요 👋
					</c:otherwise>
					</c:choose>
					
					</div>
				<div class="fullpage-menu">
					<ul>
						<!--  <li><a href="#." data-bs-toggle="modal" data-bs-target="#mNoticeBoard"  title="공지사항">공지사항</a></li> -->
						<li><a href="/board/boardList.do?code=notice" title="공지사항">공지사항</a></li>
						
						<!-- <li><a href="#.">스탬프</a></li> -->
						
						<!-- <li><a href="#.">이용안내</a></li> -->
						<!-- <li><a href="#.">자주묻는 질문</a></li> -->
						<!-- <li><a href="#.">약관 및 정책서</a></li> -->
						<c:if test="${sessionScope.sessionUser.userId != null}">
							<li><a href="/mypage/myOrderList.do">주문내역</a></li>
  							<li><a href="/user/doLogOut.do">로그아웃 </a></li>
						</c:if>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>

<<!-- 로그인 Modal -->
<div class="modal fade" id="mlogin" tabindex="-1" aria-labelledby="mloginLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">로그인</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
    <main class="form-signin w-100 m-auto">
      <form action="/user/doLoginCheck.do" method="post" target="iframe">
		<div style="text-align:center;">
        <img src="/resources/img/doc5_logo.png" class="" style="margin-bottom:60px;width:150px;">
	   </div>
        <h1 class="h3 mb-3 fw-normal">로그인 정보</h1>
        <div class="form-floating">
          <input
            type="email"
            name="userId"
            class="form-control"
            id="floatingInput"
            placeholder="name@example.com"
            value="doc5_1@doc5.com"
          />
          <label for="floatingInput">아이디</label>
        </div>
        <div class="form-floating">
          <input
            type="password"
            name="password"
            class="form-control"
            id="floatingPassword"
            placeholder="Password"
            value="doc5_010"
          />
          <label for="floatingPassword">비밀번호</label>
        </div>
        
        <button class="w-100 btn btn-lg btn-primary" type="submit">로그인 하기</button>
        <button class="w-100 btn btn-lg btn-regist" type="button" onclick="location.href='/user/userRegist.do'">회원가입</button>
        <p class="text-password-find"><a href="/user/userPasswordFind.do">비밀번호찾기</a></p>
        <p class="mt-5 mb-3 text-body-secondary">&copy; PROJECT02 DOC5 TEAM.</p>
      </form>
      
    </main>
      </div>
    </div>
  </div>
</div>