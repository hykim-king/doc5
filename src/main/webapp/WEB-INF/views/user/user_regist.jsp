<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/header/header.jsp" %>
	<div class="cont_wrap menu_wrap">
		<div class="cont">		
<style>
.form-floating {margin:5px 0;}
.form-floating .form-control{border-radius: 5px !important;}
.form-floating #floatingPassword {marign:0;}

.sub-box {
	width:95%;
    min-width: 410px;
    max-width: 900px;
    padding: 1rem;
    margin: auto !important;
  	padding-bottom:180px;
    
}

.sub-box .sub_title {
	border-bottom: 2px solid #fdd000;
	box-shadow:0px 0px 5px 0px #eee;
	font-weight: 400 !important;
	margin-bottom: 1rem !important;
	font-size: calc(1.325rem + .9vw);
	padding:10px;
	/* border-radius: 0 0 20px 20px ; */
	
}
</style>

<div class="sub-box">
				<div class="sub_title">
					${pageTitle}
				</div>
				
		    <main class="form-signin w-100 m-auto">
		      <form action="${actionUrl}" method="post" target="iframe">
				<div style="text-align:center;"> </div>
		        <div class="form-floating">
		          <input
		            type="email"
		            name="userId"
		            class="form-control"
		            id="floatingInput"
		            placeholder="name@example.com"
		            value="${userInfo.userId}"
		            required="required"
		            ${pageTitle.equals("회원정보 수정") ? "style='border:0px;' readonly='readonly'" : ""}
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
		            value=""
		            
		          />
		          <label for="floatingPassword">비밀번호</label>
		         </div>
		         <c:if test="${pageTitle eq '회원정보 수정'}">
		         <div class="form-floating">
		          <input
		            type="password"
		            name="password_re"
		            class="form-control"
		            id="floatingPassword_re"
		            placeholder="Password Re"
		            value=""
		            
		          />
		          <label for="floatingPassword_re">비밀번호 확인</label>
		        </div>
		        
		        </c:if>
		        <div class="form-floating">
		          <input
		            type="input"
		            name="name"
		            class="form-control"
		            id="floatingUserId"
		            placeholder="이름"
		            value="${userInfo.name}"
		            required="required"
		          />
		          <label for="floatingPassword">이름</label>
		        </div>
		        <div class="form-floating">
		          <input
		            type="input"
		            name="phone"
		            class="form-control"
		            id="floatingPhone"
		            placeholder="휴대폰번호"
		            value="${userInfo.phone}"
		          />
		          <label for="floatingPhone">휴대폰번호</label>
		        </div>
		        <c:if test="${pageTitle eq '회원가입'}">
		        <div class="form-floating" style="text-align:right;padding-bottom:10px;">
			        <input type='checkbox' class="form-check-input" id="privacyCheck" name='privacyCheck' value=Y' required="required" onkeyUp="phoneFomatter(this.value)"/>
			        <span for="privacyCheck">개인정보 취급방침 동의 [내용무]</span>
		        </div>
		        </c:if>
            <br/>
		        <button class="w-100 btn btn-lg btn-primary" type="submit">${pageTitle}</button>
		      </form>
		    </main>
		    </div>
		</div>
		    

	</div>
<%@ include file="/WEB-INF/views/common/footer/footer.jsp" %>
