<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header/header.jsp" %>

<style>

.cont_wrap {
	margin-top:35px;
}

.search_keyword {
	width:90%;
	margin:0 auto;
	font-size:17px;
	padding-bottom:10px;
}

</style>
<link rel="stylesheet" href="/resources/css/item.css">
	<div class="cont_wrap menu_wrap">
		<div class="cont">
			<div class="search_keyword">검색단어 : "${keyword}"</div>
			<div class="search_item">
				<ul id="menuList">
				<c:choose>
				<c:when test="${empty list}">
				<li>검색하신 상품이 없습니다.</li>
				</c:when>
				<c:otherwise>
				<c:forEach var="vo" items="${list}">
				<li>
		          <a href="/goods/goodsView.do?goodsno=${vo.goodsNo}">
		            <div class="cont_gallery_list_box">
		                <div class="cont_gallery_list_img">
		                    <img src="/resources/img/goods/${vo.goodsNo}.png" onerror="this.onerror=null; this.src='/resources/img/goods/10001.png';">
		                    ${vo.goodsNo}
		                </div>
		                
		                <div class="cont_text_box">
		                    <div class="cont_text">
		                        <div class="cont_text_inner text_wrap cont_text_title">
		                            <div class="text text1"><b>${vo.goodsName}</b></div>
		                        </div>
		                        <div class="cont_text_inner text_wrap cont_text_info">
		                            <div class="text text1">${vo.goodsEngName}</div>
		                        </div>
								<div class="cont_text_inner text_wrap cont_text_type">
									<c:if test="${vo.hotFl eq 'Y'}">
		                            <span class="text HOT">HOT</span>
		                            </c:if>
		                            <c:if test="${vo.iceFl eq 'Y'}">
		                            <span class="text ICE">ICE</span>
		                            </c:if>
		                        </div>
								
		                    </div>
		                </div>
		               
		            </div>
		             </a>
		        </li>
		        </c:forEach>
		        </c:otherwise>
		        </c:choose>
				</ul>
			</div>
		</div>
	</div>
<%@ include file="/WEB-INF/views/common/footer/footer_goods.jsp" %>
