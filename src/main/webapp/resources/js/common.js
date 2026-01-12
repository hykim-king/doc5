$(document).ready(function(){
	// 키워드 리스트 불러오기
	getAjaxGoodsSearchKeyword();
	
	//header 파일에서 매장찾기 아이콘 클릭시 _inc_modal.jsp 매장찾기 열리며 위치 찾기 자동 검색 
	$('.mypageBranchSearch').on("click",function(){
		getAjaxBranchFinder();
	});
});

/*
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
*/
//상품 검색 키워드 블러오기 
function getAjaxGoodsSearchKeyword(){
	let contextPath = document.querySelector("#iframe").dataset.contextpath;
	
	var params = {
	};

	$.ajax({
		method: "POST",
		cache: false,
		url: contextPath+"/goods/goodsSearchKeywordList.do",
		data: params,
		success: function (data) {
			$('.modal-search-con-list').html(data);
		}
	}); 
};

//삼품 키워드 삭제 
function getAjaxGoodsSearchKeywordHiddeh(keyword){
	let contextPath = document.querySelector("#iframe").dataset.contextpath;
	var params = {
		keyword:keyword
	};
	
	$.ajax({
		method: "POST",
		cache: false,
		url: contextPath+"/goods/goodsSearchKeywordHiddeh.do",
		data: params,
		success: function (data) {
			console.log(data);
		}
	}); 
};

//위치기반 가까운 지점 불러오기 HTTPS에서 작동 , HTTP 에서는 http://localhost 에서만 작동 
//=======================위치 기반 시작 =======================
function getStoreList(list) {
    if (!navigator.geolocation) {
        alert("현재 사용하시는 브라우저는 위치 정보 기능을 지원하지 않습니다.");
        return;
    }
    
    const options = {
        enableHighAccuracy: true, // GPS를 사용하여 정확도 향상
        timeout: 15000,          // 15초까지 대기 (모바일은 시간이 걸릴 수 있음)
        maximumAge: 0            // 항상 최신 위치 가져오기
    };

    navigator.geolocation.getCurrentPosition(
        function (pos) {
            var userLat = pos.coords.latitude;
            var userLng = pos.coords.longitude;
            var result = [];

            // Use 'list' (the parameter), not 'data'
            for (var i = 0; i < list.length; i++) {
                var b = list[i];

                var distance = calculateHaversine(
                    userLat,
                    userLng,
                    parseFloat(b.lat),
                    parseFloat(b.lng)
                );

                b.distance = distance;
                result.push(b);
            }

            // Sort by distance (ascending)
            result.sort(function (a, b) {
                return a.distance - b.distance;
            });

            // Output top 30
            renderBranch(result.slice(0, 30));
        },
        function (error) {
            // 상세 에러 확인용
            const errorTypes = {
                0: "알 수 없는 오류",
                1: "사용자가 권한을 거부했습니다.",
                2: "위치를 찾을 수 없습니다 (신호 부족).",
                3: "응답 시간 초과"
            };
            alert("에러: " + errorTypes[error.code]);
        },
        options //옵션 추
    );
}

function calculateHaversine(lat1, lon1, lat2, lon2) {
    var R = 6371; // Earth's radius in km
    var dLat = (lat2 - lat1) * Math.PI / 180;
    var dLon = (lon2 - lon1) * Math.PI / 180;
    var a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
            Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
            Math.sin(dLon / 2) * Math.sin(dLon / 2);
    var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    return R * c;
}

function renderBranch(list) {
    var ul = document.getElementById("branchList");
    ul.innerHTML = "";

    for (var i = 0; i < list.length; i++) {
        var b = list[i];

        var li = document.createElement("li");
        li.className = "branch-item";

        li.innerHTML =
            "<strong>" + b.branchName + "</strong><br>" +
            b.address1 + "<br>" +
            "<span>" + b.distance.toFixed(2) + " km</span>";

        ul.appendChild(li);
    }
}

function getAjaxBranchFinder(){
	let contextPath = document.querySelector("#iframe").dataset.contextpath;
	console.log(contextPath);
    $.ajax({
        method: "POST",
        cache: false,
        url: contextPath+"/branch/ajaxSelectBranch.do",
        data: {},
        dataType : 'json',
        success: function (data) {
            getStoreList(data);
        },
        error: function() {
            console.error("지점 데이터를 가져오는 데 실패했습니다.");
        }
    }); 
};

//=======================위치 기반 끝  =======================

