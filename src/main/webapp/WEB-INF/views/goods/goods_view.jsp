<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ include file="/WEB-INF/views/common/header/header_goods.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/modals.css">
    
    <style>
    .cont_wrap {
		margin-top:35px;
	}
	
	.order-bottom-btns{
		padding:5px 5px;
		bottom:10px;
	}
	.order-bottom-btns .half-btn{
		bottom:5px;
		margin:5px;
	}
	.goods-detail-box{
		margin-top:20px;
	}
	
.goods-detail-box li {
    display: flex;              /* 한 줄을 차지하며 flex 컨테이너로 설정 */
    justify-content: space-between; /* 왼쪽(b)과 오른쪽(span)을 양 끝으로 배치 */
    align-items: center;        /* 세로 중앙 정렬 */
    width: 100%;                /* 가로 전체 차지 */
    padding: 10px 0;           /* 줄 간격 여백 */
    border-bottom: 1px solid #eee; /* 줄 구분선 (선택사항) */
}

/* b와 span에서 float 속성은 제거해도 됩니다. */
.goods-detail-box li b {
    flex-shrink: 0;             /* 제목이 찌그러지지 않게 설정 */
}
    </style>
	<div class="cont_wrap menu_wrap">
		<div class="cont goods_list">
            <div class="goods_detail_wrap">
                <div class="section-box">
            <div class="goods_img_box" style="text-align:center;">
           		 <img id="goodsImg" src="${pageContext.request.contextPath}/resources/img/goods/${goodsVO.goodsNo}.png" onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/resources/img/goods/10001.png';" style="width:260px;border-radius:10px;">
            </div>
            <div class="goods_info">
                <h2 id="goodsName">${goodsVO.goodsName}</h2>
                <div id="goods_Type" class="temp-select">
               		<c:if test="${goodsVO.iceFl eq 'Y'}">
	                	<button class="temp-btn ice <c:if test="${goodsVO.hotFl eq 'N'}">active</c:if>" data-value="ICE">ICE</button>
	                </c:if>
	                <c:if test="${goodsVO.hotFl eq 'Y'}">
	                	<button class="temp-btn hot active" data-value="HOT">HOT</button>
	                </c:if>
                		
                </div>
                <p id="goodsSummary" style="margin:20px 0;font-size:15px;">${goodsVO.goodsContents}</p>
            </div>
        </div>

 


        <div class="section-box" style="text-align:center;">
            <button id="detailBtn" class="detail-btn">
                <span>상품 상세 정보</span>
                <span class="arrow">></span>
            </button>
        </div>

		<c:if test="${empty optionList == false}">
        <div class="section-box option_list">
            <div class="option-title">퍼스널 옵션</div>
           		<c:forEach var="vo" items="${optionList}" varStatus="status">
                <button id="openModal_${vo.seq}" class="option-btn box">
                    <div class="shot-row">
                        <span class="shot-label">${vo.goodsOptionName}</span>
                        <span class="arrow">></span>
                    </div>
                    <div id="result_${vo.seq}" class="result-text"></div>
                </button>
                </c:forEach>
               
            </div>
        </div>
		</c:if>
		
        <div id="detailModal" class="popup-container">
            <div class="popup-content" style="height:60vh;">
                <span id="closeDetailBtn" class="modal-close-icon">×</span>
                <h3>상품 상세정보</h3>
                <ul class="goods-detail-box">
                	<c:forEach var="vo" items="${goodsInfo}">
                		<li><b>${vo.productInfoTitle}:</b> <span>${vo.productInfoText}</span></li> 
                	</c:forEach>
                </ul>

            </div>
        </div>

		<c:if test="${empty optionList == false}">
		<c:forEach var="vo" items="${optionList}" varStatus="status">
			<div id="modal_${vo.seq}" class="popup-container">
	            <div class="popup-content" style="height:50vh;">
	                <span id="closeModal_${vo.seq}" class="modal-close-icon">×</span>
	                <h3>${vo.goodsOptionName}</h3>
	                <div class="option_box">
						<c:forEach var="subVo" items="${vo.goodsOptionVO}">
						<label class="shot-option">
	                        <input type="checkbox" name="subOption_${vo.seq}" value="${subVo.optionPrice}" data-label="${subVo.optionName}" data-price="<fmt:formatNumber value="${subVo.optionPrice}" pattern="##,###" />">
	                        <span class="checkbox-icon"></span>
	                        <span>${subVo.optionName} (+ <fmt:formatNumber value="${subVo.optionPrice}" pattern="##,###" />)</span>
	                    </label>
						</c:forEach>
	                </div>
	                <button id="apply_${vo.seq}" class="apply-btn">적용하기</button>
	            </div>
	        </div>
	        
	        <script>
	        
	     	// 옵션 버튼 클릭시 이벤
	        openModal_${vo.seq}.onclick = () => modal_${vo.seq}.classList.add("show");
	        closeModal_${vo.seq}.onclick = () => modal_${vo.seq}.classList.remove("show");
	        
	        const option_${vo.seq} = document.querySelectorAll("input[name='subOption_${vo.seq}']");
	        
	        option_${vo.seq}.forEach(box => {
	        	let title = $("#closeModal_${vo.seq}").closest(".popup-content").find("h3").text();
	        	if(title == "샷 선택" || title == "논-커피 샷 선택"){
		            box.addEventListener("change", () => {
		
		                const checkedItems = document.querySelectorAll("input[name='subOption_${vo.seq}']:checked");
		
		                if (checkedItems.length > 1) {
		                    alert("최대 1개까지 선택할 수 있습니다.");
		                    box.checked = false; // 방금 선택한 것 취소
		                }
		            });
	        	}
	        });
	        
	     // 적용하기 버튼
	        document.getElementById("apply_${vo.seq}").onclick = () => {
	        	
	        	let title = $("#closeModal_${vo.seq}").closest(".popup-content").find("h3").text();
	        	
	            /* const selected = document.querySelector("input[name='subOption_${vo.seq}']:checked");
	            if (!selected) {
	                alert(title +" 옵션을 선택하세요.");
	                return;
	            } */
	            let label = "";
	            let intputTag = ""; 
	            
	            //해당 옵션 전체삭제 
	            document.querySelectorAll("input[name='subOption_${vo.seq}']").forEach(box => {
	            	$("#subOption_${vo.seq}").remove();
	            })
	            
	            document.querySelectorAll("input[name='subOption_${vo.seq}']:checked").forEach(box => {
	            	label += (label) ? "\n"+box.getAttribute("data-label") +" (+"+box.dataset.price+")" : box.getAttribute("data-label")  +" (+"+box.dataset.price+")";
	            	
	            	intputTag = '<input type="hidden" id="subOption_${vo.seq}" name="options" value="'+box.getAttribute("data-label")+'^|^'+box.value+'">';
	            	$("#cartForm").append(intputTag);
	            });
	            
	            // 결과 표시
	            document.getElementById("result_${vo.seq}").innerText = label;

	            // 모달 닫기
	            modal_${vo.seq}.classList.remove("show");
	            
	            updatePrice();
	        };
	        </script>
		</c:forEach>  
		</c:if>

        <div class="order-bar">

            <!-- 수량 조절 -->
            <div class="order-qty">
                <button id="qtyMinus" class="qty-btn">−</button>
                <span id="orderQty">1</span>
                <button id="qtyPlus" class="qty-btn">+</button>
            </div>

            <!-- 가격 표시 -->
            <div class="order-price">
                <span id="totalPrice"><fmt:formatNumber value="${goodsVO.goodsPrice}" pattern="##,###" />원</span>
            </div>

        </div>

		<!-- 아래 버튼 2개 영역 -->
		<div class="order-bottom-btns">
		    <button id="addCartBtn" class="half-btn gray">담기</button>
		    <button id="orderBtn" class="half-btn yellow">주문하기</button>
		</div>



		</div>
	</div>
<form id="cartForm" name="cartForm" method="post">
  <input type="hidden" name="orderType" value="cart">
  <input type="hidden" name="goodsNo" value="${goodsVO.goodsNo}">
  <input type="hidden" name="branchCode" value="s0001">
  <input type="hidden" name="orderNo" value="">
  <input type="hidden" name="userId" value="${sessionScope.sessionUser.userId}">
  <input type="hidden" name="goodsName" value="${goodsVO.goodsName}">
  <input type="hidden" name="goodsPrice" value="${goodsVO.goodsPrice}">
  <input type="hidden" name="goodsCnt" value="1">
  <input type="hidden" name="tumblerFl" value="N">
  <input type="hidden" name="hotFl" value="${goodsVO.hotFl}">
  <input type="hidden" name="hotPrice" value="${goodsVO.hotPrice}">
  <input type="hidden" name="iceFl" value="${goodsVO.iceFl}">
  <input type="hidden" name="icePrice" value="${goodsVO.icePrice}">
</form>  

<style>
/* 컨펌 모달 전용 스타일 */
.confirm-modal {
    height: auto !important;
    padding: 30px 20px !important;
    text-align: center;
    border-radius: 15px;
}
.modal-msg {
    font-size: 16px;
    font-weight: bold;
    margin-bottom: 25px;
    color: #333;
}

.modal-body {
	padding:10px 0 20px 0;
}
.modal-footer-btns {
    display: flex;
    gap: 10px;
}
.confirm-btn {
    flex: 1;
    padding: 15px;
    border: none;
    border-radius: 8px;
    font-size: 14px;
    font-weight: bold;
    cursor: pointer;
}
.confirm-btn.gray { background-color: #f4f4f4; color: #666; }
.confirm-btn.yellow { background-color: #fdd000; color: #000; }
</style>
<div id="cartConfirmModal" class="popup-container">
    <div class="popup-content confirm-modal">
        <div class="modal-body">
            <p class="modal-msg">장바구니에 상품을 담았습니다.</p>
        </div>
        <div class="modal-footer-btns">
            <button id="goShoppingBtn" class="confirm-btn gray">더 담으러 가기</button>
            <button id="goCartBtn" class="confirm-btn yellow">장바구니 가기</button>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/views/common/footer/footer_goods.jsp" %>
<script src="${pageContext.request.contextPath}/resources/js/goods_view.js"></script>
