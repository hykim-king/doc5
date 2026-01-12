<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/header/header_goods.jsp" %>

<script src="${pageContext.request.contextPath}/resources/js/goods_list.js"></script>

<style>
.cont_wrap {
	margin-top:35px;
}

.cont_wrap .menuSubTitle {
  width: 90%;
  margin: 10px auto;
  font-size:22px;
  font-weight:bold;
  text-align: center;
  
}
</style>
	<div class="cont_wrap menu_wrap">
		<div class="cont goods_list">
    <!-- <div class="menuSubTitle">${cateTitle}</div>  -->
			<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/item.css">

			<ul id="goods-list"></ul>
		</div>
	</div>
<%@ include file="/WEB-INF/views/common/footer/footer_goods.jsp" %>

