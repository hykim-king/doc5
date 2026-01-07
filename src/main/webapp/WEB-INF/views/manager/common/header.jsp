<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
	<link rel="icon" href="/resources/img/favicon.ico" sizes="16x16">
	<link rel="icon" href="/resources/img/favicon.ico" sizes="32x32">

	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@900&display=swap">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;600;700;900&display=swap">

    <meta charset="UTF-8" />

	
	<link rel="stylesheet" href="/resources/css/admin.css">
</head>
<body>


<div class="wrapper">
    <div class="sidebar">
        <h2>DOC5 관리자 페이지</h2>
        <a href="/manager/">메인</a>
        <a href="/manager/member.do">회원관리</a>
        <a href="/manager/shop.do">주문 관리</a>
    </div>

    <div class="content">
        <div class="header">
            <div class="page-title"> 관리자 메인</div>
            <div class="admin-menu">
                <span class="branch-selector" style="margin-right: 20px;">
                    <label for="branchCode">지점 변경:</label>
                    <select id="branchCode" onchange="changeBranch(this.value)">
                        <option value="s0001" selected>강남 본점</option>
                        <option value="s0002">홍대 지점</option>
                        <option value="s0003">부산 지점</option>
                    </select>
                </span>
                <a href="logout.do">로그아웃</a>
            </div>
        </div>

        <div class="section">
		