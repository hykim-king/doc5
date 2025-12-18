
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
			//console.log($(this).data("num"));
		});

		//menu();
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
	<style>
		.bottomMenu {position:fixed;bottom:0px; width:100%; background-color:#fff;border-radius: 20px 20px 0px 0px;box-shadow:0px 0px 0px 2px #ddd; border:0;}
		.bottomMenu ul{padding:0;}
		.bottomMenu li{float:left; width:33%;text-align:center;line-height:70px;font-size:16px;font-weight:600;text-shadow: 1px 1px #fdd000;}
	</style>
	<div class="bottomMenu">
		<ul>
			<li><a href="/">HOME</a></li>
			<li><a href="/goods/goodsList?cate=m_rec">DOC5 오더</a></li>
			<li><a href="#." class="top_login" data-bs-toggle="modal" data-bs-target="#fullPage" title="마이페이지">전체메뉴</a></li>
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
	
<script>
window.onload = function() {

// 버튼과 팝업 요소 가져오기
const openPopupBtn = document.getElementById('openPopupBtn');
const closePopupBtn = document.getElementById('closePopupBtn');
const bottomPopup = document.getElementById('bottomPopup');

// 팝업 열기 버튼 클릭 이벤트
openPopupBtn.addEventListener('click', () => {
    bottomPopup.classList.add('show');
});

// 팝업 닫기 버튼 클릭 이벤트
closePopupBtn.addEventListener('click', () => {
    bottomPopup.classList.remove('show');
});

// (선택 사항) 팝업 외부 클릭 시 닫기
window.addEventListener('click', (event) => {
    if (event.target === bottomPopup) {
        bottomPopup.classList.remove('show');
    }
});
};
</script>
</html>