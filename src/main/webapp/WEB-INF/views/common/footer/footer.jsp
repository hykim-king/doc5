
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

	});

	/*
	var category_change = "";
	$(document).on("click","[name='list_checkbox_all']",function(){
		if ($(this).prop("checked") == true) {
			$(this).parents(".list_checkbox_wrap").find(".list_checkbox").find(":checkbox").prop("checked",false).change();
		}
		menu();
	});
	$(document).on("click","[name='list_checkbox']",function(){
		$(this).parents(".list_checkbox_wrap").find("[name='list_checkbox_all']").prop("checked",false).change();
		if ( $(this).parents(".list_checkbox_wrap").find("[name='list_checkbox']").length ==  $(this).parents(".list_checkbox").find("[name='list_checkbox']:checked").length ) {
			$(this).parents(".list_checkbox_wrap").find("[name='list_checkbox_all']").prop("checked",true).change();
		} else {
			$(this).parents(".list_checkbox_wrap").find("[name='list_checkbox_all']").prop("checked",false).change();
		}
		menu();
	});

	function menu(page){
		var list_checkbox_all = $("input[name='list_checkbox_all']:checked").val();
		var checkbox = [];
		$("input[name='list_checkbox']:checked").each(function(i) {
			checkbox.push($(this).val());
		});
		var checklist_join = checkbox.join(",");
		$.ajax({
			url : "menu.php",
			type : "GET",
			data : {
				"page" : page,
				"menu_category1" : '',
				"menu_category2" : '',
				"category" : checklist_join,
				"list_checkbox_all" : list_checkbox_all,
			},
			success : function(result) {
				$("#menu_list").html(result);
				cont_gallery_list_img();
			}
		});
	};
	*/
</script>
	</div>

	<div class="bottomMenu">
		<ul>
			<li>
				<a href="/">
					<div><img src="/resources/img/5doc_home.png" class="bottom-5doc-menu"></div>
					<span>HOME</span>
				</a>
			</li>
			<li>
				<a href="/goods/goodsList.do">
					<div><img src="/resources/img/5p_order.png" class="bottom_5doc_order"></div>
					<span>DOC5 오더</span>
				</a>
			</li>
			<li>
				<a href="#." class="top_login" data-bs-toggle="modal" data-bs-target="#fullPage" title="마이페이지">
					<div><img src="/resources/img/5doc_all.png" class="bottom-5doc-menu"></div>
					<span>전체메뉴</span>
				</a>
			</li>

		</ul>
	</div>
	
	

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
		$(document).ready(function(){
			var swiper_foot = new Swiper(".swiper_foot", {
				slidesPerView: "auto",
				spaceBetween: 50,
				loop: true,
				autoplay: {
				  delay: 0,
				},
				speed: 5000,
				navigation: {
					nextEl: ".swiper-button-next",
					prevEl: ".swiper-button-prev",
				},
				on: {
					slideChange: function () {
					},
					activeIndexChange: function () {
					}
				},
				breakpoints: {
				}
			});
		});
	</script>
	     <!-- iframe 설정 -->
    <iframe id="iframe" name="iframe"></iframe>   
</html>