<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
					<form>
					<div class="search-container-inputBox">
						<ul>
							<li><input type="text" class="form-control" placeholder="검색하실 상품을 입력하세요"></li>
							<li><button type="submit" class="btn btn-secondary">검색</button></li>
						</ul>
					</div>
					</form>
				
					<div class="search-keword">
						<h4 class="modal-sub-title">최근 검색어</h4>
						<div>아메리카노<span>X</span></div>
						<div>스무디<span>X</span></div>
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

<!-- 전체페이지 Modal - FullPage -->
<div class="modal fade up" id="fullPage">
	<div class="modal-dialog modal-fullscreen">
		<div class="modal-content">
			
			<!-- Modal Header -->
			<div class="modal-header">
			<h4 class="modal-title">전체메뉴</h4>
			<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
			</div>
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
</style>
			<!-- Modal body -->
			<div class="modal-body">
				<div class="fullpage-intext">DOC5님 반가워요 👋</div>
				<div class="fullpage-stamp">스탬프 5/10</div>
				<div class="fullpage-menu">
					<ul>
						<li><a href="#.">주문내역</a></li>
						<li><a href="#.">스탬프</a></li>
						<li><a href="#.">공지사항</a></li>
						<li><a href="#.">이용안내</a></li>
						<li><a href="#.">자주묻는 질문</a></li>
						<li><a href="#.">약관 및 정책서</a></li>
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
      <form action="/user/doLoginCheck.do" method="post">
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
            value="test1@doc5.com"
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
            value="4321a"
          />
          <label for="floatingPassword">비밀번호</label>
        </div>
        
        <button class="btn btn-primary w-100 py-2" type="submit">
          로그인 하기
        </button>
        <p class="mt-5 mb-3 text-body-secondary">&copy; PROJECT02 DOC5 TEAM.</p>
      </form>
    </main>
      </div>
    </div>
  </div>
</div>