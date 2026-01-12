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

.progress-fill {
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
</style>

    <div class="container">
        <header class="header">
            <span class="material-icons">close</span>
            <h1 class="title">주문내역</h1>
            <span class="material-icons">refresh</span>
        </header>

        <section class="status-section">
            <p class="store-info">홍대서교점(메가MGC커피) (주문번호 0117)</p>
            <h2 class="status-text">픽업이 완료되었습니다 👌</h2>
            
            <div class="progress-bar-container">
                <div class="progress-labels">
                    <span>주문완료</span>
                    <span>준비중</span>
                    <span class="active">준비완료</span>
                </div>
                <div class="progress-bar">
                    <div class="progress-fill"></div>
                    <div class="progress-dot"></div>
                </div>
                <div class="progress-times">
                    <span>01.09 13:12</span>
                    <span>01.09 13:14</span>
                </div>
            </div>
        </section>

        <section class="order-list">
            <div class="section-header">
                <h3>주문내역 1개</h3>
                <button class="btn-receipt">영수증 보기</button>
            </div>
            <div class="item-card">
                <img src="https://via.placeholder.com/80" alt="아메리카노" class="item-img">
                <div class="item-info">
                    <p class="item-name">아메리카노</p>
                    <p class="item-option">ICE</p>
                    <p class="item-price">1개 | 2,000원</p>
                </div>
                <span class="material-icons favorite-icon">favorite_border</span>
            </div>
        </section>

        <section class="payment-section">
            <h3>결제금액</h3>
            <div class="price-row">
                <span>총 상품금액</span>
                <span>2,000원</span>
            </div>
            <div class="price-row">
                <span>제휴 멤버십</span>
                <span>-0원</span>
            </div>
            <div class="price-row">
                <span>쿠폰</span>
                <span>-0원</span>
            </div>
            <hr>
            <div class="price-row total">
                <span>총 결제금액</span>
                <span>2,000원</span>
            </div>
        </section>

        <section class="stamp-section">
            <div class="stamp-content">
                <p class="stamp-title">스탬프 1개가 적립되었어요! 🎉</p>
                <p class="stamp-desc">스탬프 10개를 모아 아메리카노(HOT/ICE)<br>1잔 무료 쿠폰을 넣어드렸어요!</p>
                <button class="btn-stamp-detail">스탬프 내역 보러가기 &gt;</button>
            </div>
            <div class="stamp-icon">
                <div class="badge">
                    <img src="https://via.placeholder.com/40" alt="cup">
                </div>
            </div>
        </section>
    </div>
<%@ include file="/WEB-INF/views/common/footer/footer.jsp" %>