$(document).ready(function(){
	// 버튼과 팝업 요소 가져오기
	const openPopupBtn = document.querySelector('#openPopupBtn');
	const closePopupBtn = document.querySelector('#closePopupBtn');
	const bottomPopup = document.querySelector('#bottomPopup');
	
	// 팝업 열기 버튼 클릭 이벤트
	openPopupBtn.addEventListener("click", function() {
	    bottomPopup.classList.add('show');
	});

	// 팝업 닫기 버튼 클릭 이벤트
	closePopupBtn.addEventListener('click', function() {
	    bottomPopup.classList.remove('show');
	});

	// (선택 사항) 팝업 외부 클릭 시 닫기
	window.addEventListener('click', (event) => {
	    if (event.target === bottomPopup) {
	        bottomPopup.classList.remove('show');
	    }
	});
		
		
	//head_down_menu start
     $(".head_wrap .head .head_menu > ul > li").hover(function() {
          if ( $(".head_menu_wrap").is(".right0") == false ) {
               $(".head_wrap .head .head_menu > ul > li").removeClass("check");
               $(this).addClass("check");
               $(".head_menu_down_menu").stop().slideDown("fast");
          };
     }, function() {
               $(".head_wrap .head .head_menu > ul > li").removeClass("check");
     });

     $(".head_wrap").hover(function() {
          $(this).addClass("head_over");
     }, function() {
          if ( $(".head_menu_wrap").is(".right0") == false ) {
               $(this).removeClass("head_over");
               $(".head_wrap .head .head_menu > ul > li").removeClass("check");
            
               $(".head_menu_down_menu").stop().slideUp("fast");
          }
     });

	//head_down_menu end
	//mobile start
     $(".mobile_menu_icon").click(function(){
          $(".head_wrap").addClass("head_over");
          $(this).toggleClass("mobile_menu_icon_open");
          if ( $(this).is(".mobile_menu_icon_open") ) {
               $(".head_menu_wrap").addClass("right0");
          } else {
               $(".head_menu_wrap").removeClass("right0");
          }
     });
     $(".head_menu_down").click(function(){
          $(this).toggleClass("head_menu_down_open");
          if ( $(this).is(".head_menu_down_open") ) {
               $(this).next(".head_menu_down_menu").find("ul").slideDown("fast");
          } else {
               $(this).next(".head_menu_down_menu").find("ul").slideUp("fast");
          }
     });
	//mobile end
     cont_gallery_list_img();
     $(window).resize(function() {
          $(".head_menu_down_menu > ul").css("display","block");
          if ( $(window).width() < 760 ) {
               $(".head_menu_down_menu > ul").css("display","none");
          };
          $(".head_menu_down").removeClass("head_menu_down_open");
          if ( $(window).width() >= 1280 ) {
               $(".head_wrap").removeClass("head_over");
               $(".head_menu_wrap").removeClass("right0");
               $(".mobile_menu_icon").removeClass("mobile_menu_icon_open");
          };
          cont_gallery_list_img();
     });
});
$(window).scroll( function() {
     if ( $(document).scrollTop() > $(".cont_wrap").offset() .top ) {
          $(".head_wrap").addClass("head_fixed");
     } else{
          $(".head_wrap").removeClass("head_fixed");
     }
});
function cont_gallery_list_img(){
     $(".cont_gallery_list > ul > li").each(function() {
          if ( $(this).find(".cont_gallery_list_img").length ) {
               $(this).find(".cont_gallery_list_img").css("width", $(this).width() );
          };

          if ( $(this).find(".cont_gallery_list_img_height").length ) {
               $(this).find(".cont_gallery_list_img_height").css("height", $(this).width() );
          } else {
               $(this).find(".cont_gallery_list_img").css("height", $(this).width() );
          };
     });
};

function login(){
     location.href = "/login/?ReturnPage=" + window.location.pathname;
};
function logout(){
     location.href = "/login/logout.php?ReturnPage=" + window.location.pathname;
};
function quick(ele){
     $(ele).parents(".nav_wrap").find(".nav").fadeToggle("fast");
     $(".nav_wrap .nav_quick_title, .nav_wrap .nav_quick_close").slideToggle("fast");
};
