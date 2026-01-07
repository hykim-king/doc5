<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<c:choose>
	<c:when test="${empty gsList}">
		<div>최근 검색 내용이 없습니다.</div>
	</c:when>
	<c:otherwise>
		<c:forEach var="vo" items="${gsList }">
		<div><a href="/goods/goodsSearch.do?keyword=${vo.keyword}">${vo.keyword}</a><span>${vo.regDt} </span><span class="goodsKeywordDel" data-keyword="${vo.keyword}">X</span></div>
		</c:forEach>
	</c:otherwise>
</c:choose>


<script>
$('.goodsKeywordDel').on("click",function(){
	getAjaxGoodsSearchKeywordHiddeh(this.getAttribute("data-keyword"));
	$(this).parent().remove();
});
</script>