<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ include file="/WEB-INF/views/common/header/header.jsp"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/cart.css">
<div class="cont_wrap menu_wrap">
	<div class="cont goods_list">
		<div class="mega-cart-container">
			<header class="header">
				<h1>DOC5 장바구니</h1>
			</header>


			<!-- 장바구니제품과 주문제품 다를경우 -->
			<c:if test="${not empty msg}">
			    <script>
			        alert("${msg}");
			    </script>
			</c:if>	

			<!-- <div class="store-select">
			        <span>매장을 선택해 주세요</span>
			        <i class="arrow-right">〉</i>
			    </div> -->

			<div class="all-select-bar">
				<label class="checkbox-container"> <input type="checkbox">
					<span class="checkmark"></span> 전체선택 0/2
				</label>
				<button class="btn-delete-selected">선택삭제</button>
			</div>

			<section class="cart-list">


				<c:forEach var="vo" items="${list}">


				

					<article class="cart-item">
						<div class="item-checkbox">
							<label class="checkbox-container"> <input type="checkbox"
								value="${vo.seq}"> <span class="checkmark"></span>
							</label>
						</div>
						<div class="item-content">
							<div class="item-main">
								<div class="item-img">
									<a
										href="${pageContext.request.contextPath}/goods/goodsView.do?goodsNo=${vo.goodsNo}">
										<img
										src="${pageContext.request.contextPath}/resources/img/goods/${vo.goodsNo}.png"
										onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/resources/img/goods/10001.png';">
									</a>
								</div>
								<div class="item-details">
									<div class="item-title-row">
										<h2 class="item-name">${vo.goodsName}</h2>
										<button class="btn-close" data-seq="${vo.seq}">×</button>
									</div>
									<p class="item-options">
										<c:forEach var="subVO" items="${vo.mcgList}"
											varStatus="status">
											<c:if test="${status.index != 0}"> | </c:if>
											${subVO.optionName} (<fmt:formatNumber
												value="${subVO.optionPrice}" pattern="##,###" />원)
										</c:forEach>

									</p>
									<span class="item-temp">ICE</span>
									<div class="item-bottom-row">
										<div class="quantity-box"
											data-unitPrice='${vo.totalGoodsTotalPrice / vo.goodsCnt}'>
											<button>-</button>
											<span class="num">${vo.goodsCnt}</span>
											<button>+</button>
										</div>
										<span class="item-price"><fmt:formatNumber
												value="${vo.totalGoodsTotalPrice}" pattern="##,###" />원</span>
									</div>
								</div>
							</div>
						</div>
					</article>
				</c:forEach>


				<div class="cart-notice">
					<p>장바구니에 담긴 상품은 최대 7일간 보관됩니다.</p>
				</div>
		</div>
		<form id="cartForm" name="cartForm"></form>
		<footer class="footer-order">
			<button class="btn-order-submit">주문하기</button>
		</footer>
	</div>
</div>
<script>
document.addEventListener('DOMContentLoaded', function() {
    const quantityBoxes = document.querySelectorAll('.quantity-box');
    const orderBtn = document.querySelector('.btn-order-submit');
    const contextPath = "${pageContext.request.contextPath}";

    // --------------------------
    // 수량 변경
    // --------------------------
    quantityBoxes.forEach(box => {
        const minusBtn = box.querySelector('button:first-child');
        const plusBtn = box.querySelector('button:last-child');
        const numDisplay = box.querySelector('.num');

        const handleQuantityChange = (isPlus) => {
            const cartItem = box.closest('.cart-item');
            const checkbox = cartItem.querySelector('input[type="checkbox"]');
            const isChecked = checkbox.checked;
            const itemValue = checkbox.value;

            let currentNum = parseInt(numDisplay.innerText);
            if(isPlus) currentNum++;
            else if(currentNum > 1) currentNum--;
            else return;

            const unitPrice = parseInt(box.dataset.unitprice);
            const totalPrice = unitPrice * currentNum;

            numDisplay.innerText = currentNum;
            const priceDisplay = cartItem.querySelector('.item-price');
            if(priceDisplay) priceDisplay.innerText = totalPrice.toLocaleString() + '원';

            updateCartItem(itemValue, currentNum);

            if(isChecked) updateTotalPrice();
        };

        minusBtn.addEventListener('click', () => handleQuantityChange(false));
        plusBtn.addEventListener('click', () => handleQuantityChange(true));
    });

    // --------------------------
    // 전체 선택 / 개별 체크
    // --------------------------
    const allCheck = document.querySelector('.all-select-bar input[type="checkbox"]');
    const itemChecks = document.querySelectorAll('.cart-item input[type="checkbox"]');
    const selectCountText = document.querySelector('.all-select-bar label');

    allCheck.addEventListener('change', function() {
        itemChecks.forEach(check => check.checked = allCheck.checked);
        updateSelectCount();
    });

    itemChecks.forEach(check => {
        check.addEventListener('change', function() {
            const checkedCount = document.querySelectorAll('.cart-item input[type="checkbox"]:checked').length;
            allCheck.checked = (checkedCount === itemChecks.length);
            updateSelectCount();
        });
    });

    function updateSelectCount() {
        const checkedCount = document.querySelectorAll('.cart-item input[type="checkbox"]:checked').length;
        const totalCount = itemChecks.length;

        if(selectCountText) {
            const countSpan = ' 전체선택 '+checkedCount+'/'+totalCount;
            selectCountText.childNodes[selectCountText.childNodes.length - 1].nodeValue = countSpan;
        }

        if(checkedCount > 0){
            orderBtn.style.backgroundColor = "#fdd000";
            orderBtn.style.color = "#000";
            orderBtn.disabled = false;
        } else {
            orderBtn.style.backgroundColor = "#f4f4f4";
            orderBtn.style.color = "#888";
            orderBtn.disabled = true;
        }
    }

    updateSelectCount();

	 // --------------------------
	 // 주문하기 버튼 클릭
	 // --------------------------
	 orderBtn.addEventListener('click', function() {
	
	     const checkedItems = document.querySelectorAll('.cart-item input[type="checkbox"]:checked');
	
	     if (checkedItems.length === 0) {
	         alert("주문할 상품을 선택해주세요.");
	         return;
	     }
	
	     const form = document.getElementById('cartForm');
	     form.innerHTML = ""; // 초기화
	
	     // 주문 타입
	     form.appendChild(createHiddenInput("orderType", "cart"));
	
	     // 선택된 seq만 전송
	     checkedItems.forEach(chk => {
	         form.appendChild(createHiddenInput("seq", chk.value));
	     });
	
	     form.method = "post";
	     form.action = contextPath + "/order/order.do";
	     form.submit();
	 });


    function createHiddenInput(name, value){
        const input = document.createElement('input');
        input.type = 'hidden';
        input.name = name;
        input.value = value;
        return input;
    }

    // --------------------------
    // 선택삭제 버튼
    // --------------------------
    const selectDeleteButtons = document.querySelector('.btn-delete-selected');
    selectDeleteButtons.addEventListener('click', function() {
        if(document.querySelectorAll('.cart-item input[type="checkbox"]:checked').length < 1){
            alert('선택하신 상품이 없습니다.');
        } else {
            if(!confirm("선택하신 상품을 삭제하시겠습니까?")) return;
            deleteCartItem();
        }
    });

    // --------------------------
    // 개별 삭제 버튼
    // --------------------------
    const deleteButtons = document.querySelectorAll('.item-title-row .btn-close');
    deleteButtons.forEach(btn => {
        btn.addEventListener('click', function() {
            if(!confirm("상품을 삭제하시겠습니까?")) return;

            const cartItem = btn.closest('.cart-item');
            const isChecked = cartItem.querySelector('input[type="checkbox"]').checked;

            cartItem.style.opacity = '0';
            cartItem.style.transition = '0.3s';

            setTimeout(() => {
                cartItem.remove();
                if(isChecked) updateTotalPrice();
                updateSelectCount();
                deleteCartItem(cartItem.querySelector('input').value);
            }, 300);
        });
    });

}); // DOMContentLoaded

// --------------------------
// 수량 변경 서버 업데이트
// --------------------------
function updateCartItem(seq, cnt){
    let contextPath = document.querySelector("#iframe").dataset.contextpath;
    if(seq){
        $.ajax({
            method: "POST",
            cache: false,
            url: contextPath+"/cart/cartPs.do",
            data: {unitSeq: seq, type: 'update', cnt: cnt},
            dataType: "json",
            success: function(res){
                if(res.flag !== 1) alert(res.message);
            },
            error: function(){ console.log("실패"); }
        });
    }
}

// --------------------------
// 장바구니 삭제 서버 업데이트
// --------------------------
function deleteCartItem(seq){
    let contextPath = document.querySelector("#iframe").dataset.contextpath;
    if(!seq){
        let tmpSeq = "";
        document.querySelectorAll('.cart-item input[type="checkbox"]:checked').forEach(check=>{
            tmpSeq += (tmpSeq)? ","+check.value : check.value;
            check.closest('.cart-item').remove();
        });
        seq = tmpSeq;
    }

    if(seq){
        $.ajax({
            method:"POST",
            cache:false,
            url: contextPath+"/cart/cartPs.do",
            data: {unitSeq: seq, type:'delete'},
            dataType:"json",
            success: function(res){
                if(res.flag===1){ location.reload(); }
                else{ alert(res.message); }
            },
            error:function(){ console.log("실패"); }
        });
    }
}

// --------------------------
// 총 결제금액 업데이트 (필요 시 구현)
// --------------------------
function updateTotalPrice(){
    console.log("총 결제금액 계산 필요...");
}
</script>


<%@ include file="/WEB-INF/views/common/footer/footer_goods.jsp"%>