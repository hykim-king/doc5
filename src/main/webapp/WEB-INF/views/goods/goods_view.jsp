<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/views/common/header/header_goods.jsp" %>
    <link rel="stylesheet" href="/resources/css/modals.css">
    
    <style>
    .cont_wrap {
		margin-top:35px;
	}

    </style>
	<div class="cont_wrap menu_wrap">
		<div class="cont goods_list">
            <div class="goods_detail_wrap">
                <div class="section-box">
						<div class="goods_img_box" style="text-align:center;">
						    <img id="goodsImg"
						         src=""
						         style="width:260px;border-radius:10px;">
						</div>
						
						<div class="goods_info">
						    <h2 id="goodsName">${goodsVO.goodsName}</h2>
						
						    <div id="goods_Type" class="temp-select">
						        <c:if test="${goodsVO.iceFl eq 'Y'}">
						            <button class="temp-btn ice active" data-value="ICE">ICE</button>
						        </c:if>
						        <c:if test="${goodsVO.hotFl eq 'Y'}">
						            <button class="temp-btn hot" data-value="HOT">HOT</button>
						        </c:if>
						    </div>
						
						    <p id="goodsSummary">${goodsVO.goodsContents}</p>
						</div>
        </div>

 
<form id="orderForm" method="post" action="/goods/goodsPs.do">
  <input type="text" name="goodsNo" value="${goodsVO.goodsNo}">
  <input type="text" name="branchCode" value="s0001">
  <input type="text" name="orderNo" value="null">
  <input type="text" name="userId" value="${sessionScope.sessionUser.userId}">
  <input type="text" name="goodsName" value="${goodsVO.goodsName}">
  <input type="text" name="goodsPrice" value="${goodsVO.goodsPrice}">
  <input type="text" name="goodsCnt" value="1">
  <input type="text" name="tumblerFl" value="${goodsVO.tumblerFl}">
  <input type="text" name="hotFl" value="${goodsVO.hotFl}">
  <input type="text" name="hotPrice" value="${goodsVO.hotPrice}">
  <input type="text" name="iceFl" value="${goodsVO.iceFl}">
  <input type="text" name="icePrice" value="${goodsVO.icePrice}">

  <input type="hidden" name="option" value="옵션이름1">
  <input type="hidden" name="optionPrice" value="옵션가격1">
  <input type="hidden" name="option" value="옵션이름2">
  <input type="hidden" name="optionPrice" value="옵션가격2">
  <input type="hidden" name="option" value="옵션이름3">
  <input type="hidden" name="optionPrice" value="옵션가격3">
  
  <button type="submit">장바구니 저장</button>
</form>  
  
			  <span id="priceData"
				      data-base-price="${goodsVO.goodsPrice}"
				      data-hot-price="${goodsVO.hotPrice}"
				      data-ice-price="${goodsVO.icePrice}">
				</span>
				  
  

        <div class="section-box" style="text-align:center;">
            <button id="detailBtn" class="detail-btn">
                <span>상품 상세 정보</span>
                <span class="arrow">></span>
            </button>
        </div>

        <div class="section-box option_list">
            <div class="option-title">퍼스널 옵션</div>
                <button id="openShotModal" class="option-btn box">
                    <div class="shot-row">
                        <span class="shot-label">샷 선택</span>
                        <span class="arrow">></span>
                    </div>
                    <div id="shotResult" class="result-text"></div>
                </button>
               
                <button id="openSugarModal" class="option-btn box">
                    <div class="shot-row">
                        <span class="shot-label">저당 스테비아 슈가 추가</span>
                        <span class="arrow">></span>
                    </div>
                    <div id="sugarResult" class="result-text"></div>
                </button>

                <button id="openSweetModal" class="option-btn box">
                    <div class="shot-row">
                        <span class="shot-label">당도 선택</span>
                        <span class="arrow">></span>
                    </div>
                    <div id="sweetResult" class="result-text"></div>
                </button>

                <button id="openToppingModal" class="option-btn box">
                    <div class="shot-row">
                        <span class="shot-label">토핑 선택</span>
                        <span class="arrow">></span>
                    </div>
                    <div id="toppingResult" class="result-text"></div>
                </button>
            </div>
        </div>

				<div id="detailModal" class="popup-container">
				    <div class="popup-content" style="height:60vh;">
				        <span id="closeDetailBtn" class="modal-close-icon">×</span>
				        <h3>상품 상세정보</h3>
				
				        <ul>
				            <!-- 상품명 -->
				            <li>
				                <b>상품명:</b> ${goodsVO.goodsName}
				            </li>
				
				            <!-- 용량 / 기본 정보 -->
				            <li>
				                ${goodsVO.shortDescription}
				            </li>
				
				            <!-- 영양정보 (중복 제거) -->
				            <c:set var="printed" value="" />
				
				            <c:forEach var="info" items="${goodsVO.goodsInfoVO}">
				                <c:if test="${not fn:contains(printed, info.productInfoText)}">
				                    <li>${info.productInfoText}</li>
				                    <c:set var="printed"
				                           value="${printed}${info.productInfoText}|"/>
				                </c:if>
				            </c:forEach>
				        </ul>
				    </div>
				</div>





        <div id="shotModal" class="popup-container">
            <div class="popup-content" style="height:40vh;">
                <span id="closeShotModal" class="modal-close-icon">×</span>
                <h3>샷 선택</h3>
                <div class="option_box">

                    <label class="shot-option">
                        <input type="checkbox" name="shotOption" value="0" data-label="연하게" data-price="0">
                        <span class="checkbox-icon"></span>
                        <span>연하게</span>
                    </label>

                    <label class="shot-option">
                        <input type="checkbox" name="shotOption" value="1" data-label="샷 추가 +600원" data-price="600">
                        <span class="checkbox-icon"></span>
                        <span>샷 추가 +600원</span>
                    </label>

                    <label class="shot-option">
                        <input type="checkbox" name="shotOption" value="2" data-label="2샷 추가 +1,200원" data-price="1200">
                        <span class="checkbox-icon"></span>
                        <span>2샷 추가 +1,200원</span>
                    </label>
                </div>
                <button id="applyShot" class="apply-btn">적용하기</button>
            </div>
        </div>



        <div id="sugarModal" class="popup-container">
            <div class="popup-content" style="height:40vh;">
                <span id="closeSugarModal" class="modal-close-icon">×</span>
                <h3>저당(스테비아) 선택</h3>
                <div class="option_box">

                    <label class="sugar-option">
                        <input type="checkbox" name="sugarOption" value="1" data-label="저당 스테비아 슈가 추가 +600원" data-price="600">
                        <span class="checkbox-icon"></span>
                        <span>저당 스테비아 슈가 추가 +600원</span>
                    </label>
                </div>
                <button id="applySugar" class="apply-btn">적용하기</button>
            </div>
        </div>



        <div id="sweetModal" class="popup-container">
            <div class="popup-content" style="height:40vh;">
                <span id="closeSweetModal" class="modal-close-icon">×</span>
                <h3>당도 선택</h3>
                <div class="option_box">
                    <!-- 연유 추가 -->
                    <label class="sweet-option">
                        <input type="checkbox" name="sweetOption" value="condensed" 
            data-label="연유 추가 +700원" data-price="700">
                        <span class="checkbox-icon"></span>
                        <span>연유 추가 +700원</span>
                    </label>
                    <!-- 저당 스테비아 추가 -->
                    <label class="sweet-option">
                        <input type="checkbox" name="sweetOption" value="stevia" 
            data-label="저당 스테비아 추가 +600원" data-price="600">
                        <span class="checkbox-icon"></span>
                        <span>저당 스테비아 추가 +600원</span>
                    </label>
                </div>

                <!-- 적용 버튼 -->
                <button id="applySweet" class="apply-btn">적용하기</button>
            </div>
        </div>



        <div id="toppingModal" class="popup-container">
            <div class="popup-content" style="height:40vh;">

                <span id="closeToppingModal" class="modal-close-icon">×</span>

                <h3>토핑 선택</h3>

                <div class="option_box">

                    <label class="topping-option">
                        <input type="checkbox" name="toppingOption" value="whip" data-label="휘핑 추가 +500원" data-price="500">
                        <span class="checkbox-icon"></span>
                        <span>휘핑 추가 +500원</span>
                    </label>

                    <label class="topping-option">
                        <input type="checkbox" name="toppingOption" value="pearl" data-label="타피오카 펄 추가 +700원" data-price="700">
                        <span class="checkbox-icon"></span>
                        <span>타피오카 펄 추가 +700원</span>
                    </label>

                    <label class="topping-option">
                        <input type="checkbox" name="toppingOption" value="gelato" data-label="초코젤라또 추가 +700원" data-price="700">
                        <span class="checkbox-icon"></span>
                        <span>초코젤라또 추가 +700원</span>
                    </label>

                </div>

                <button id="applyTopping" class="apply-btn">적용하기</button>

            </div>
        </div>

        <div class="order-bar">

            <!-- 수량 조절 -->
            <div class="order-qty">
                <button id="qtyMinus" class="qty-btn">−</button>
                <span id="orderQty">1</span>
                <button id="qtyPlus" class="qty-btn">+</button>
            </div>

            <!-- 가격 표시 -->
            <div class="order-price">
                <span id="totalPrice">3,000원</span>
            </div>

        </div>

<!-- 아래 버튼 2개 영역 -->
<div class="order-bottom-btns">
    <button id="addCartBtn" class="half-btn gray">담기</button>
    <button id="orderBtn" class="half-btn yellow">주문하기</button>
</div>



		</div>
	</div>
<%@ include file="/WEB-INF/views/common/footer/footer_goods.jsp" %>
<script src="/resources/js/goods_view.js"></script>
