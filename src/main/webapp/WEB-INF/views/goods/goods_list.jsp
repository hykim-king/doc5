<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%@ include file="/WEB-INF/views/common/header/header_goods.jsp" %>

<link rel="stylesheet" href="/resources/css/item.css">

<div class="cont_wrap menu_wrap">
    <div class="cont goods_list">

        <ul class="goods_list_ul">

            <c:choose>
                <c:when test="${empty goodsList}">
                    <li style="text-align:center; width:100%;">
                        등록된 상품이 없습니다.
                    </li>
                </c:when>

                <c:otherwise>
                    <c:forEach var="item" items="${goodsList}">
                        <li>
                            <a href="./goodsView.do?goodsNo=${item.goodsNo}">
                                <div class="cont_gallery_list_box">

                                    <div class="cont_gallery_list_img">
                                        <img src="https://img.79plus.co.kr/megahp/manager/upload/menu/20250320000925_1742396965069_ekSqAIVc1L.jpg"
                                             alt="${item.goodsName}">
                                    </div>

                                    <div class="cont_text_box">
                                        <div class="cont_text">

                                            <div class="cont_text_inner cont_text_title">
                                                <div class="text text1">
                                                    <b>${item.goodsName}</b>
                                                </div>
                                            </div>

                                            <div class="cont_text_inner cont_text_info">
                                                <div class="text text1">
                                                    ${item.goodsEngName}
                                                </div>
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

<%@ include file="/WEB-INF/views/common/footer/footer_goods.jsp" %>
