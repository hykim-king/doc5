<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ include file="/WEB-INF/views/common/header/header.jsp" %>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/cart.css">
	<div class="cont_wrap menu_wrap">
		<div class="cont goods_list">
			<div class="mega-cart-container">
			    <header class="header">
		        	<h1>DOC5 장바구니</h1>
		    	</header>		    
			
			    <!-- <div class="store-select">
			        <span>매장을 선택해 주세요</span>
			        <i class="arrow-right">〉</i>
			    </div> -->
			
			    <div class="all-select-bar">
			        <label class="checkbox-container">
			            <input type="checkbox">
			            <span class="checkmark"></span>
			            전체선택 0/2
			        </label>
			        <button class="btn-delete-selected">선택삭제</button>
			    </div>
			
			    <section class="cart-list">
			    
			    
			   	 	<c:forEach var="vo" items="${list}">
					        	
					        	
			    
			    
			        <article class="cart-item">
			            <div class="item-checkbox">
			                <label class="checkbox-container">
			                    <input type="checkbox" value="${vo.seq}">
			                    <span class="checkmark"></span>
			                </label>
			            </div>
			            <div class="item-content">
			                <div class="item-main">
			                    <div class="item-img">
			                    	<a href="${pageContext.request.contextPath}/goods/goodsView.do?goodsNo=${vo.goodsNo}">
			                        <img src="${pageContext.request.contextPath}/resources/img/goods/${vo.goodsNo}.png" onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/resources/img/goods/10001.png';">
			                        </a>
			                    </div>
			                    <div class="item-details">
			                        <div class="item-title-row">
			                            <h2 class="item-name">${vo.goodsName}</h2>
			                            <button class="btn-close" data-seq="${vo.seq}">×</button>
			                        </div>
			                        <p class="item-options">
			                        	<c:forEach var="subVO" items="${vo.mcgList}" varStatus="status">
											<c:if test="${status.index != 0}"> | </c:if>
											${subVO.optionName} (<fmt:formatNumber value="${subVO.optionPrice}" pattern="##,###" />원)
										</c:forEach>
			                           
			                        </p>
			                        <span class="item-temp">ICE</span>
			                        <div class="item-bottom-row">
			                            <div class="quantity-box" data-unitPrice='${vo.totalGoodsTotalPrice / vo.goodsCnt}'>
			                                <button>-</button>
			                                <span class="num">${vo.goodsCnt}</span>
			                                <button>+</button>
			                            </div>
			                            <span class="item-price" ><fmt:formatNumber value="${vo.totalGoodsTotalPrice}" pattern="##,###" />원</span>
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
			<footer class="footer-order">
			        <button class="btn-order-submit">주문하기</button>
			</footer>
		</div>
	</div>
    <script>
    document.addEventListener('DOMContentLoaded', function() {
        const quantityBoxes = document.querySelectorAll('.quantity-box');

        quantityBoxes.forEach(box => {
            const minusBtn = box.querySelector('button:first-child');
            const plusBtn = box.querySelector('button:last-child');
            const numDisplay = box.querySelector('.num');

            const handleQuantityChange = (isPlus) => {
                const cartItem = box.closest('.cart-item');
                const checkbox = cartItem.querySelector('input[type="checkbox"]');
                const isChecked = checkbox.checked;
                const itemValue = checkbox.value;

                // 1. 수량 계산
                let currentNum = parseInt(numDisplay.innerText);
                if (isPlus) {
                    currentNum++;
                } else {
                    if (currentNum > 1) currentNum--;
                    else return;
                }

                // 2. 가격 데이터 가져오기 (HTML의 data-unitprice 속성 활용)
                // dataset을 사용할 때는 소문자로 접근합니다. (data-unit-price -> unitPrice)
                const unitPrice = parseInt(box.dataset.unitprice); 
                const totalPrice = unitPrice * currentNum;

                // 3. UI 반영
                numDisplay.innerText = currentNum;
                
                // 해당 상품의 가격 표시 영역 업데이트 (클래스가 .item-price라고 가정)
                const priceDisplay = cartItem.querySelector('.item-price');
                if (priceDisplay) {
                    priceDisplay.innerText = totalPrice.toLocaleString() + '원';
                }
					
                updateCartItem(itemValue, currentNum);
                // 4. 로그 확인
                console.log("상품ID:", itemValue, "| 변경수량:", currentNum, "| 합산가격:", totalPrice);

                // 5. 체크된 상품이면 하단 총 결제 금액 업데이트
                if(isChecked) {
                    updateTotalPrice(); 
                }
            };

            minusBtn.addEventListener('click', () => handleQuantityChange(false));
            plusBtn.addEventListener('click', () => handleQuantityChange(true));
        });
        
     // 1. 필요한 요소들 참조
        const allCheck = document.querySelector('.all-select-bar input[type="checkbox"]'); // 전체 선택 체크박스
        const itemChecks = document.querySelectorAll('.cart-item input[type="checkbox"]'); // 개별 체크박스들
        const selectCountText = document.querySelector('.all-select-bar label'); // '전체선택 0/2' 텍스트 영역

        // 2. 전체 선택 클릭 시 이벤트
        allCheck.addEventListener('change', function() {
            itemChecks.forEach(check => {
                check.checked = allCheck.checked; // 전체 선택 상태를 개별 박스에 모두 적용
            });
            updateSelectCount(); // 카운트 업데이트
        });

        // 3. 개별 체크박스 클릭 시 이벤트
        itemChecks.forEach(check => {
            check.addEventListener('change', function() {
                // 체크된 개수와 전체 개수 비교
                const checkedCount = document.querySelectorAll('.cart-item input[type="checkbox"]:checked').length;
                
                // 모든 개별 박스가 체크되면 전체 선택도 체크, 하나라도 풀리면 전체 선택 해제
                allCheck.checked = (checkedCount === itemChecks.length);
                
                updateSelectCount(); // 카운트 업데이트
            });
        });

        // 4. 선택 개수 표시 업데이트 (예: 전체선택 1/2)
        function updateSelectCount() {
            const checkedCount = document.querySelectorAll('.cart-item input[type="checkbox"]:checked').length;
            const totalCount = document.querySelectorAll('.cart-item input[type="checkbox"]').length;
            
            // 텍스트 노드만 변경 (체크박스 아이콘 뒤의 텍스트)
            if (selectCountText) {
                // '전체선택 0/2' 부분의 텍스트를 업데이트
                // HTML 구조에 따라 innerHTML 혹은 textContent를 적절히 조절하세요.
                const countSpan = ' 전체선택 '+checkedCount+'/'+totalCount;
                console.log(countSpan);
                // 체크박스 마크업 유지를 위해 마지막 텍스트 노드만 교체하는 것이 안전합니다.
                selectCountText.childNodes[selectCountText.childNodes.length - 1].nodeValue = countSpan;
            }
            
            // 하단 주문하기 버튼 활성화/비활성화 제어
            const orderBtn = document.querySelector('.btn-order-submit');
            if (checkedCount > 0) {
                orderBtn.style.backgroundColor = "#fdd000"; // 메가커피 노란색
                orderBtn.style.color = "#000";
                orderBtn.disabled = false;
            } else {
                orderBtn.style.backgroundColor = "#f4f4f4";
                orderBtn.style.color = "#888";
                orderBtn.disabled = true;
            }
        }
        
        
        const selectDeleteButtons = document.querySelector('.btn-delete-selected');
        selectDeleteButtons.addEventListener('click', function() {
        	if(document.querySelectorAll('.cart-item input[type="checkbox"]:checked').length < 1){
        		alert('선택하신 상품이 없습니다.')
        	}else{
	        	// 사용자 확인 (모바일 UX 권장)
	            if (!confirm("선택하신 상품을 삭제하시겠습니까?")) return;
	        	
	            deleteCartItem();
        	}
        });
        
     	// 1. 모든 삭제 버튼(btn-close)을 찾아 이벤트를 겁니다.
        const deleteButtons = document.querySelectorAll('.item-title-row .btn-close');

        deleteButtons.forEach(btn => {
            btn.addEventListener('click', function() {
                // 사용자 확인 (모바일 UX 권장)
                if (!confirm("상품을 삭제하시겠습니까?")) return;

                // 2. 가장 가까운 상품 부모 요소(.cart-item) 찾기
                const cartItem = btn.closest('.cart-item');
                
                // 3. 삭제 전, 체크박스가 체크되어 있었는지 확인
                const isChecked = cartItem.querySelector('input[type="checkbox"]').checked;

                // 4. 애니메이션 효과 (선택사항: 부드럽게 사라지기)
                cartItem.style.opacity = '0';
                cartItem.style.transition = '0.3s';

                setTimeout(() => {
                    // 5. DOM에서 실제 삭제
                    cartItem.remove();

                    // 6. 삭제 후 데이터 갱신
                    // 체크된 상품을 삭제했다면 총액을 다시 계산해야 함
                    if (isChecked) {
                        updateTotalPrice(); 
                    }
                   
                    // 전체 선택 카운트(0/2 등) 업데이트 함수도 호출
                    if (typeof updateSelectCount === 'function') {
                        updateSelectCount();
                    }

                    // 7. (선택사항) Ajax를 통해 DB에서도 삭제 처리
                   deleteCartItem(cartItem.querySelector('input').value);
                }, 300);
            });
        });
        
        // 페이지 로드 시 초기 카운트 설정
        updateSelectCount();
    });
    
    //장바구니 수량 변경 
    function updateCartItem(seq, cnt){
		let contextPath = document.querySelector("#iframe").dataset.contextpath;
    	
    	if(seq){
    		
	    	var params = {
	    		unitSeq : seq,
	    		type : 'update',
	    		cnt : cnt
	    	};
	
	    	$.ajax({
	    		method: "POST",
	    		cache: false,
	    		url: contextPath+"/cart/cartPs.do",
	    		data: params,
	    		dataType : "json",
	    		success: function (res) {
	    	        if(res.flag === 1 ){//데이터 타입 까지 비교
	    	           console.log('res.message:',res.message) 
	    	          
	    	           
	    	        }else{
	    	           alert(res.message);
	    	        }            	  
	    		  
	    		},
	    		error: function() {      //실패 
	    		  console.log("실패");
	    		}
	    	}); 
    	}
    }
    
    //장바구니 개별 삭제
    function deleteCartItem(seq){
    	let contextPath = document.querySelector("#iframe").dataset.contextpath;
        
    	if(!seq){
    		let tmpSeq = "";
    		document.querySelectorAll('.cart-item input[type="checkbox"]:checked').forEach(check => {
    			tmpSeq += (tmpSeq) ? ","+check.value : check.value;
    			const cartItem = check.closest('.cart-item');
    			cartItem.remove();
    			
            });
    		seq = tmpSeq;
    	}
    	
    	console.log(seq);
    	if(seq){
    		
	    	var params = {
	    		unitSeq : seq,
	    		type : 'delete'
	    	};
	
	    	$.ajax({
	    		method: "POST",
	    		cache: false,
	    		url: contextPath+"/cart/cartPs.do",
	    		data: params,
	    		dataType : "json",
	    		success: function (res) {
	    	        if(res.flag === 1 ){//데이터 타입 까지 비교
	    	           console.log('res.message:',res.message) 
	    	           location.reload();
	    	           
	    	        }else{
	    	           alert(res.message);
	    	        }            	  
	    		  
	    		},
	    		error: function() {      //실패 
	    		  console.log("실패");
	    		}
	    	}); 
    	}
    }
    
 	// 총 결제 금액을 업데이트하는 가상 함수 (필요 시 구현)
    function updateTotalPrice() {
        console.log("수량이 변경되었습니다. 합계를 다시 계산합니다...");
    }
 
   
    </script>

   
<%@ include file="/WEB-INF/views/common/footer/footer_goods.jsp" %>

