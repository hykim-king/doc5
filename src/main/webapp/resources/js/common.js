$(document).ready(function(){
	getAjaxGoodsSearchKeyword();
	
	
	
});

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

function getAjaxGoodsSearchKeyword(){
	var params = {
	};

	$.ajax({
		method: "POST",
		cache: false,
		url: "/goods/goodsSearchKeywordList.do",
		data: params,
		success: function (data) {
			console.log(data);
			$('.modal-search-con-list').html(data);
		}
	}); 
};

function getAjaxGoodsSearchKeywordHiddeh(keyword){
	var params = {
		keyword:keyword
	};
	
	$.ajax({
		method: "POST",
		cache: false,
		url: "/goods/goodsSearchKeywordHiddeh.do",
		data: params,
		success: function (data) {
			console.log(data);
		}
	}); 
	
	
};


