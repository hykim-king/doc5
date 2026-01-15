
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


	</div>
	
	<c:if test="${bottomMenuFl ne 'N' }">
	<div class="bottomMenu">
		<ul>
			<li>
				<a href="${pageContext.request.contextPath}">
					<div><img src="${pageContext.request.contextPath}/resources/img/5doc_home.png" class="bottom-5doc-menu"></div>
					<span>HOME</span>
				</a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/goods/goodsList.do">
					<div><img src="${pageContext.request.contextPath}/resources/img/5p_order.png" class="bottom_5doc_order"></div>
					<span>DOC5 오더</span>
				</a>
			</li>
			<li>
				<a href="#." class="top_login" data-bs-toggle="modal" data-bs-target="#fullPage" title="마이페이지">
					<div><img src="${pageContext.request.contextPath}/resources/img/5doc_all.png" class="bottom-5doc-menu"></div>
					<span>전체메뉴</span>
				</a>
			</li>

		</ul>
	</div>
	</c:if>
	

	<div id="bottomPopup" class="popup-container">
	    <div class="popup-content">
			<div class="option-colseBtn"><button id="closePopupBtn">X</button></div>
	        <h2 class="bottom_option_text">옵션 선택</h2>
	        <div>이곳에 상품 옵션 팝업 내용을 입력하세요.</div>
	    </div>
	</div>



</body>


<script type="text/javascript" src="https://img.79plus.co.kr/megahp/common/js/aos.js"></script>
	<script>
		
		AOS.init({
		  duration: 1200,
		});
		
	</script>
	<!-- iframe 설정 -->
    <iframe id="iframe" name="iframe" data-contextPath="${pageContext.request.contextPath}"></iframe>   
</html>