<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	</div>

<c:if test="${bottomMenuFl ne 'N'}">
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
					<div><img src="${pageContext.request.contextPath}/resources/img/5p_order2.png" class="bottom-5doc-menu"></div>
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

<script>
	var topMenuSwiper = new Swiper(".topMenuSwiper", {
		slidesPerView: 6,
		spaceBetween:5,
		freeMode: true
	});

	$(document).ready(function(){
		$(".topMenuSwiper .swiper-slide").each(function() {
			if($(this).hasClass('on')==true){
				topMenuSwiper.slideTo($(this).data("num"));
			}
		});
		
		let goodsCateMenuBtn = document.querySelectorAll(".swiper-slide");

		// Loop through each element in the collection
		goodsCateMenuBtn.forEach(function(btn) {
		    btn.addEventListener("click", function() {
		    	
		        topMenuSwiper.slideTo($(this).data("num"));
		        
		        // Since you are using jQuery inside the function:
		        $(this).addClass("on").siblings().removeClass("on");
		        $("#iframe").data("cate",$(this).data("cate"));
		        

		        console.log($("#iframe").data("cate"));
		        
		       getAjaxGoodsList($("#iframe").data("cate"));
		        // If you want to use the Swiper slideTo method:
		        // topMenuSwiper.slideTo($(this).data("cate"));
		    });
		});

	});
</script>

</body>


	
      <!-- iframe 설정 -->
    <iframe id="iframe" name="iframe" data-contextPath="${pageContext.request.contextPath}" data-cate="${cate}"></iframe>   
</html>