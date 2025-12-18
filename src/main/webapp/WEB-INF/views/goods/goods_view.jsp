<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/header/header_order.jsp" %>
    <link rel="stylesheet" href="/resources/css/modals.css">
	<div class="cont_wrap menu_wrap">
		<div class="cont goods_list">
            <div class="goods_detail_wrap">
                <div class="section-box">
            <div class="goods_img_box" style="text-align:center;">
                <img id="goodsImg" src="https://img.79plus.co.kr/megahp/manager/upload/menu/20250320000925_1742396965069_ekSqAIVc1L.jpg" style="width:260px;border-radius:10px;">
            </div>

            <div class="goods_info">
                <h2 id="goodsName">아메리카노</h2>
                <div id="goods_Type" class="temp-select">
                    <button class="temp-btn ice active" data-value="ICE">ICE</button>
                    <button class="temp-btn hot" data-value="HOT">HOT</button>
                </div>
                <p id="goodsSummary">[기본2샷]메가MGC커피 블렌드 원두로 추출한 에스프레소에 물을 더해, 풍부한 바디감을 느낄 수 있는 스탠다드 커피.</p>
            </div>
        </div>
       

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
                    <li><b>상품명:</b> <span id="detailGoodsName"></span></li>
                    <li><b>칼로리:</b> <span id="goodsKcal"></span></li>
                    <li><b>당류:</b> <span id="goodsSugar"></span></li>
                    <li><b>포화지방:</b> <span id="goodsFat"></span></li>
                    <li><b>나트륨:</b> <span id="goodsSodium"></span></li>
                    <li><b>단백질:</b> <span id="goodsProtein"></span></li>
                    <li><b>카페인:</b> <span id="goodsCaffeine"></span></li>
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
<%@ include file="/WEB-INF/views/common/footer/footer.jsp" %>
<script src="/resources/js/goods_view.js"></script>
