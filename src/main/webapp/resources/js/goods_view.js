$(document).ready(function(){
    // =======================
    // 모달 열기 / 닫기
    // =======================
    detailBtn.onclick = () => detailModal.classList.add("show");
    closeDetailBtn.onclick = () => detailModal.classList.remove("show");

    
    
   
    document.querySelector("#m-roolback-btn").onclick = () => {
    	window.history.go(-1);
	};
	
	
	const hotIceBtn = document.querySelectorAll(".temp-btn");
	hotIceBtn.forEach(function(btn) {
	    btn.addEventListener("click", function() {
	        hotIceBtn.forEach(function(otherBtn) {
	            otherBtn.classList.remove("active");
	        });
	        btn.classList.add("active");
	        
	        const selectedValue = btn.getAttribute("data-value");
	        document.querySelector("input[name='hotFl']").value = (selectedValue === "HOT") ? "Y" : "N";
	        document.querySelector("input[name='iceFl']").value = (selectedValue === "ICE") ? "Y" : "N";
	        
	        let hotPrice = Number(document.querySelector("input[name='hotPrice']").value);
	        let icePrice = Number(document.querySelector("input[name='icePrice']").value);
	        if(selectedValue === "HOT"){
	        	document.querySelector("input[name='goodsPrice']").value = basePrice + hotPrice;
	        }else if(selectedValue === "ICE"){
	        	document.querySelector("input[name='goodsPrice']").value = basePrice + icePrice;
	        }
	        
	        let options = document.querySelectorAll("input[name='options']");
	        options.forEach(option => {
			    option.remove();
			});
			
			document.querySelectorAll("input[name^='subOption_']:checked").forEach(box => {
	            box.checked = false;
	        });
	         
	         
	        document.querySelectorAll(".result-text").forEach(optionText => {
			    optionText.innerText = "";
			});
	         
	        
	        updatePrice();
	        
	        console.log(basePrice)
	        
	    });
	}); 

	if (hotIceBtn.length > 1) {
	    hotIceBtn.forEach(function(otherBtn) {
	        // "active" 클래스가 있는지 확인하고 조건에 따라 동작
	        if (otherBtn.classList.contains("active")) {
	            otherBtn.click();
	        }
	    });
	}
	
	getAjaxGoodsOptionTypeList();
	
});


let basePrice = Number(document.querySelector("input[name='goodsPrice']").value);
let qty = 1;

function updatePrice() {
	let nowBasePrice = Number(document.querySelector("input[name='goodsPrice']").value);

    const optionPrice = calcOptionPrice();  // ★ 옵션 가격 합산
    const total = (nowBasePrice + optionPrice) * qty;

    document.getElementById("totalPrice").innerText = 
        total.toLocaleString() + "원";
    
}

qtyPlus.onclick = () => {
    qty++;
    orderQty.innerText = qty;
    document.querySelector("input[name='goodsCnt']").value = qty;
    updatePrice();
};

qtyMinus.onclick = () => {
    if (qty > 1) qty--;
    orderQty.innerText = qty;
    document.querySelector("input[name='goodsCnt']").value = qty;
    updatePrice();
};


//주문 바로가기 
orderBtn.onclick = () => {
    let contextPath = document.querySelector("#iframe").dataset.contextpath;
    document.querySelector("input[name='orderType']").value = "direct";
	let params = $("form[name=cartForm]").serialize(); 

	$.ajax({
		method: "POST",
		cache: false,
		url: contextPath+"/goods/goodsPs.do",
		data: params,
		dataType : "json",
		success: function (res) {
			console.log("성공", res);
	        if(res.flag === 1 ){//데이터 타입 까지 비교
	           console.log('res.message:',res.message) 
			// 1. 전송할 데이터 준비 (Ajax 성공 시 전달받은 res 활용)
			const newForm = document.createElement('form');
			newForm.method = 'POST';
			newForm.action = contextPath + "/order/order.do"; 
			
			// 2. 항목 추가 함수 (중복 코드를 줄이기 위해 내부 함수 활용)
			const addHiddenField = (name, value) => {
			    const input = document.createElement('input');
			    input.type = 'hidden';
			    input.name = name;
			    input.value = value;
			    newForm.appendChild(input);
			};
			
			// 3. 필수 데이터들 매핑
			addHiddenField('seq', res.seq);            // 장바구니/주문 번호
			addHiddenField('orderType', 'direct');     // 바로구매 여부 구분용
			
			// 만약 수량이나 선택된 옵션도 같이 넘겨야 한다면 아래처럼 추가하세요.
			//const currentQty = document.getElementById('orderQty').innerText;
			//addHiddenField('goodsCnt', currentQty);
			
			// 4. 전송 (문서에 추가해야 submit이 작동함)
			document.body.appendChild(newForm);
			newForm.submit();
			
			// submit 후에는 페이지가 이동되므로 removeChild는 생략해도 무방하지만, 
			// 비동기 상황을 위해 남겨두는 것도 나쁘지 않습니다.
	        }else{
	           alert(res.message);
	        }            	  
		  
		},
		error: function() {      //실패 
		  console.log("실패");
		}
	}); 
};

addCartBtn.onclick = () => {
    let contextPath = document.querySelector("#iframe").dataset.contextpath;
    document.querySelector("input[name='orderType']").value = "cart";
    
	let params = $("form[name=cartForm]").serialize(); 

	$.ajax({
		method: "POST",
		cache: false,
		url: contextPath+"/goods/goodsPs.do",
		data: params,
		dataType : "json",
		success: function (res) {
			console.log("성공", res);
	        if(res.flag === 1 ){//데이터 타입 까지 비교
	           console.log('res.message:',res.message) 
	           //alert(res.message); 
	           cartConfirmModal.classList.add("show");
	           
	        }else{
	           alert(res.message);
	        }            	  
		  
		},
		error: function() {      //실패 
		  console.log("실패");
		}
	}); 
};


//총 옵션 가격 계산
function calcOptionPrice() {
    let optionTotal = 0;

	const subOption = document.querySelectorAll("input[name='options']");
	subOption.forEach(box => {
		let boxSplit = box.value.split("^|^");
		optionTotal += Number(boxSplit[1]);
	});


    return optionTotal;
}


// 상품 상세 옵션 불러오기 
function getAjaxGoodsOptionTypeList(){
	let contextPath = document.querySelector("#iframe").dataset.contextpath;
	let goodsNo = document.querySelector("input[name='goodsNo']").value;
	
	var params = {
		goodsNo : goodsNo
	};

	$.ajax({
		method: "GET",
		cache: false,
		url: contextPath+"/goods/getAjaxGoodsOptionTypeList.do",
		data: params,
		success: function (data) {
			//console.log(data);
			
			$('#goods-option-box').html(data);
		}
	}); 
};


// 2. 더 담으러 가기 (모달 닫고 현재 페이지 유지 혹은 목록으로)
    goShoppingBtn.onclick = function() {
        cartConfirmModal.classList.remove("show");
        // 필요하다면 목록으로 이동: location.href = "목록페이지URL";
    };

    // 3. 장바구니 가기 (장바구니 페이지로 이동)
    goCartBtn.onclick = function() {
    	let contextPath = document.querySelector("#iframe").dataset.contextpath;
        location.href = contextPath+"/cart/cart.do"; 
    };

