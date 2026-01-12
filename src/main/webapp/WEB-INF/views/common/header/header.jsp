<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
Date date = new Date();
SimpleDateFormat simpleDate = new SimpleDateFormat("yyyyMMddhhmmss");
String verDate = simpleDate.format(date);
%>	
<!doctype html>
<html lang="ko">
<head>
	<title>DOC5커피</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
	<meta name="title" content="DOC5커피">
	<meta name="Subject" content="DOC5 커피전문점, 프랜차이즈, 빅사이즈 투샷, 테이크아웃">
	<meta name="keywords" content="DOC5 커피전문점, 프랜차이즈, 빅사이즈 투샷, 테이크아웃">
	<meta name="Descript-xion" content="DOC5 커피전문점, 프랜차이즈, 빅사이즈 투샷, 테이크아웃">
	<meta name="Description" content="DOC5커피 커피전문점, 프랜차이즈, 빅사이즈 투샷, 테이크아웃">
	<meta name="Publisher" content="chingooplus">
	<meta name="Other Agent" content="chingooplus">
	<meta name="Distribution" content="chingooplus">
	<meta name="Copyright" content="chingooplus">
	<meta name="Author" content="chingooplus">
	<meta name="Robots" content="ALL">
	<!-- <meta name="Robots" content="noindex"> -->
	<!-- webmaster start -->
	<meta property="og:type" content="website">
	<meta property="og:title" content="DOC5커피">
	<meta property="og:site_name" content="DOC5 커피전문점, 프랜차이즈, 빅사이즈 투샷, 테이크아웃">
	<meta property="og:description" content="DOC5 커피전문점, 프랜차이즈, 빅사이즈 투샷, 테이크아웃">
	<meta property="og:url" content="http://192.168.100.185:8080/">
	<meta property="og:image" content="/resources/img/doc5_logo.png">
	<!-- webmaster end -->
	<!-- app start-->
	<meta property="al:ios:url" content="applinks://docs">
	<meta property="al:ios:app_store_id" content="12345">
	<meta property="al:ios:app_name" content="App Links">
	<meta property="al:android:url" content="applinks://docs">
	<meta property="al:android:app_name" content="App Links">
	<meta property="al:android:package" content="org.applinks">
	<meta property="al:web:url" content="http://192.168.100.185:8080">
	<!-- app end-->
	<link rel="canonical" href="http://192.168.100.185:8080/goods_order.jsp?cate=m_rec">

	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@900&display=swap">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;600;700;900&display=swap">

	<link rel="stylesheet" type="text/css" href="https://use.fontawesome.com/releases/v6.0.0/css/all.css">
	
	<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<!-- <link rel="stylesheet" type="text/css" href="https://img.79plus.co.kr/megahp/common/css/simple-line-icons.min.css"> -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css?ver=<%=verDate%>">
	<link rel="stylesheet" type="text/css" href="https://img.79plus.co.kr/megahp/common/css/animate.css">
	<link rel="stylesheet" type="text/css" href="https://img.79plus.co.kr/megahp/common/css/aos.css?ver=202508271045">

	<script type="text/javascript" src="https://code.jquery.com/jquery.min.js"></script>



    <meta charset="UTF-8" />


	<link rel="icon" type="image/png" sizes="16x16"  href="${pageContext.request.contextPath}/resources/img/favicon-16x16.png">
	<meta name="msapplication-TileColor" content="#ffffff">
	<meta name="theme-color" content="#ffffff">
	
	<!-- swiper -->
	<link rel="stylesheet" type="text/css" href="https://img.79plus.co.kr/megahp/common/css/swiper.min.css?ver=202508271045">
	<script type="text/javascript" src="https://img.79plus.co.kr/megahp/common/js/swiper.min.js"></script>

	<script src="${pageContext.request.contextPath}/resources/js/assets/dist/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/assets/js/color-modes.js"></script>
<link href="${pageContext.request.contextPath}/resources/js/assets/dist/css/bootstrap.min.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/resources/css/sign-in.css?ver=<%=verDate%>" rel="stylesheet" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js?ver=<%=verDate%>"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/inc/_inc_modal.jsp" %>
	<style>
		.bscookieframe {
			position: absolute;
			bottom: 10px;
			right: 5px;
			width: 3px;
			height: 3px;
			z-index: 10;
		}
	</style>
	
	
	
	<div class="overlay none"></div>
	<div class="modal">
		<div class="modal-dialog">
			<div class="modal-content"></div>
		</div>
	</div>

	<div class="wrap">
		<c:if test="${headerFl ne 'N' }">
		<div class="head_wrap">
			<div class="head">
				<div class="head_logo">
					<div class="head_logo">
						<a href="${pageContext.request.contextPath}"><img src="${pageContext.request.contextPath}/resources/img/doc5_logo.png" class="head_logo_img_fixed"></a>
					</div>
				</div>
				<div class="head_title">
					<!-- <img src="/resources/img/5p_order.png" class="top_5doc_order_icon"> -->
				</div>
				
				<div class="mobile_top_icon_wrap" >
					
				<c:choose>
				    <c:when test="${sessionScope.sessionUser.userId != null}">
				    	<div><button class="top_mypage" title="마이페이지" onclick="location.href='${pageContext.request.contextPath}/user/userModify.do'"><span>마이페이지</span></button></div>
				    </c:when>
				    <c:otherwise>
				        <div><button class="top_login" data-bs-toggle="modal" data-bs-target="#mlogin" title="로그인"><span>Login</span></button></div>
				    </c:otherwise>
				</c:choose>
                         <div><button class="top_search bn_srch" data-bs-toggle="modal" data-bs-target="#mSearch" title="검색"><span>검색</span></button></div>
					<!--로그인 중 일시 마이페이지 보기-->
<!-- <div><button class="top_branch_search" data-bs-toggle="modal" data-bs-target="#mBranchSearch" title="매장찾기"><span>매장찾기</span></button></div> -->
<div><button class="top_cart" data-bs-target="#mCart" title="로그인" onclick="location.href='${pageContext.request.contextPath}/cart/cart.do'"><span>장바구</span></button></div>

                         
				</div>
			</div>
		</div>
		</c:if>
		
		