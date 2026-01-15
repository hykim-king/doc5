<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/header/header.jsp" %>
<style> 
.container {
    max-width: 500px;
    margin: 0 auto;
    background-color: #fff;
    min-height: 100vh;
}

/* Header */
.header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 15px;
    border-bottom: 1px solid #eee;
}

.title {
    font-size: 18px;
    font-weight: bold;
}

/* Status Section */
.status-section {
    padding: 20px;
    text-align: left;
}

.store-info {
    color: #4a78ff;
    font-size: 14px;
    font-weight: 600;
    margin-bottom: 8px;
}

.status-text {
    font-size: 22px;
    font-weight: bold;
    margin-bottom: 25px;
}

/* Progress Bar */
.progress-bar-container {
    margin-top: 20px;
}

.progress-labels {
    display: flex;
    justify-content: space-between;
    font-size: 13px;
    color: #bbb;
    margin-bottom: 8px;
}

.progress-labels .active {
    color: #333;
    font-weight: bold;
}

.progress-bar {
    height: 6px;
    background-color: #eee;
    border-radius: 3px;
    position: relative;
}

.progress-fill-O {
    width: 0%;
    height: 100%;
    background-color: #ffda00; /* 메가커피 노란색 */
    border-radius: 3px;
}

.progress-fill-P {
    width: 50%;
    height: 100%;
    background-color: #ffda00; /* 메가커피 노란색 */
    border-radius: 3px;
}

.progress-fill-S {
    width: 100%;
    height: 100%;
    background-color: #ffda00; /* 메가커피 노란색 */
    border-radius: 3px;
}

.progress-dot {
    width: 12px;
    height: 12px;
    background-color: #ffda00;
    border: 2px solid #fff;
    box-shadow: 0 0 5px rgba(0,0,0,0.2);
    border-radius: 50%;
    position: absolute;
    right: 0;
    top: -3px;
}

.progress-times {
    display: flex;
    justify-content: space-between;
    font-size: 12px;
    color: #bbb;
    margin-top: 8px;
}

/* Order List */
.order-list, .payment-section, .stamp-section {
    padding: 20px;
    border-top: 8px solid #f8f8f8;
}

.section-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 15px;
    border-bottom:2px solid #eee;
}

.btn-receipt {
    background: none;
    border: none;
    color: #888;
    text-decoration: underline;
    font-size: 12px;
}

.item-card {
    display: flex;
    align-items: center;
    position: relative;
    padding:20px 0;
    border-bottom:1px solid #eee;
}

.item-img {
    width: 80px;
    height: 80px;
    border-radius: 12px;
    background-color: #f1f1f1;
    margin-right: 15px;
}

.item-name {
    font-weight: bold;
    font-size: 15px;
}

.item-option {
    color: #888;
    font-size: 13px;
    margin: 4px 0;
}


.item-price {
    font-weight: 600;
    font-size: 14px;
}

.favorite-icon {
    position: absolute;
    right: 0;
    top: 5px;
    color: #ccc;
}

/* Payment Section */
.payment-section h3 {
    font-size: 17px;
    margin-bottom: 15px;
}

.price-row {
    display: flex;
    justify-content: space-between;
    margin-bottom: 10px;
    font-size: 15px;
}

hr {
    border: 0;
    border-top: 1px solid #eee;
    margin: 15px 0;
}

.total {
    font-weight: bold;
    font-size: 18px;
}

/* Stamp Section */
.stamp-section {
    display: flex;
    justify-content: space-between;
    align-items: flex-start;
}

.stamp-title {
    font-weight: bold;
    font-size: 16px;
    margin-bottom: 10px;
}

.stamp-desc {
    font-size: 13px;
    color: #666;
    line-height: 1.4;
}

.btn-stamp-detail {
    background: none;
    border: none;
    font-weight: bold;
    margin-top: 10px;
    font-size: 14px;
    cursor: pointer;
}

.badge {
    width: 60px;
    height: 60px;
    background-color: #ffda00;
    clip-path: polygon(50% 0%, 61% 35%, 98% 35%, 68% 57%, 79% 91%, 50% 70%, 21% 91%, 32% 57%, 2% 35%, 39% 35%); /* 간이 별모양 */
    display: flex;
    justify-content: center;
    align-items: center;
}

.item-option .ICE {
    font-size: 12px;
    color: #023dff;
    margin-top: 2px;
}

.item-option .HOT {
    font-size: 12px;
    color: #ff0000;
    margin-top: 2px;
}
</style>

    <div class="container">
        <header class="header">
            <span class="material-icons" onclick="location.href='${pageContext.request.contextPath}/mypage/myOrderList.do'">close</span>
            <h1 class="title">주문내역</h1>
            <span class="material-icons" onclick="location.reload();">refresh</span>
        </header>

        <section class="status-section">
            <h2 class="status-text">${orderStepText} 👌</h2>
            
            <div class="progress-bar-container">
                <div class="progress-labels">
                    <span class="${list[0].orderStep eq 'O' ? 'active' : ''}">주문완료</span>
                    <span class="${list[0].orderStep eq 'P' ? 'active' : ''}">준비중</span>
                    <span class="${list[0].orderStep eq 'S' ? 'active' : ''}">준비완료</span>
                </div>
                <div class="progress-bar">
                    <div class="progress-fill-${list[0].orderStep}"></div>
                    <div class="progress-dot"></div>
                </div>
                <div class="progress-times">
                    <span>${list[0].regDt}</span>
                    <span>${list[0].modDt}</span>
                </div>
            </div>
        </section>

        <section class="order-list">
            <div class="section-header">
                <h3>주문내역 ${list[0].cList.size() }개</h3>
            </div>
            <c:forEach var="vo" items="${list }">
	            <c:forEach var="sVo" items="${vo.cList }">
	            <div class="item-card">
	                <img src="${pageContext.request.contextPath}/resources/img/goods/${sVo.goodsNo}.png" alt="${sVo.goodsName }" class="item-img" onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/resources/img/goods/10001.png';">
	                <div class="item-info">
	                    <p class="item-name">${sVo.goodsName }</p> 
	                    <p class="item-option">
	                    	<c:if test="${sVo.hotFl eq 'Y'}">
                           		<span class="text HOT">HOT</span>
                          	</c:if>
                            <c:if test="${sVo.iceFl eq 'Y'}"> 
                           		<span class="text ICE">ICE</span>
                            </c:if>
	                    </p>
	                    <p class="item-option">
	                    <c:forEach var="optVO" items="${sVo.mcgList}" varStatus="status">
								<c:if test="${status.index != 0}"> | </c:if>
								${optVO.optionName} (<fmt:formatNumber value="${optVO.optionPrice}" type="number" />원)
							</c:forEach>
	                    </p>
	                    <p class="item-price">${sVo.goodsCnt}개 | <fmt:formatNumber value="${sVo.totalGoodsTotalPrice}" type="number" />원</p>
	                </div>
	            </div>
	            </c:forEach>
            </c:forEach>
        </section>

        <section class="payment-section">
            <h3>결제금액</h3>
            <div class="price-row">
                <span>총 상품금액</span>
                <span><fmt:formatNumber value="${list[0].goodsTotalPrice}" type="number"/>원</span>
            </div>
            <div class="price-row">
                <span>총 옵션금액</span>
                <span><fmt:formatNumber value="${list[0].optionTotalPrice}" type="number"/>원</span>
            </div>

            
            <hr>
            <div class="price-row total">
                <span>총 결제금액</span>
                <span><fmt:formatNumber value="${list[0].settleTotalPrice}" type="number"/>원</span>
            </div>
        </section>

        
    </div>
<%@ include file="/WEB-INF/views/common/footer/footer.jsp" %>