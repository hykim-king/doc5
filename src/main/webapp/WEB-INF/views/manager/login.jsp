<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/manager/common/header_not.jsp" %>


    <div class="modal-content">
     
      <div class="modal-body">
	    <main class="form-signin w-100 m-auto">
	      <form action="/admin/doLoginCheck.do" method="post">
			<div style="text-align:center;">
	        <img src="/resources/img/doc5_logo.png" class="" style="margin-bottom:60px;width:150px;">
		   </div>
	        <h1 class="h3 mb-3 fw-normal">지점 관리자 정보</h1>
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

	        <p class="mt-5 mb-3 text-body-secondary">&copy; PROJECT02 DOC5 TEAM.</p>
	      </form>
	      
	    </main>
      </div>
    </div>

  
<%@ include file="/WEB-INF/views/manager/common/footer_not.jsp" %>