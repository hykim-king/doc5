
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko">
<head>
	<title>DOC5커피...</title>
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
	<meta property="og:url" content="http://192.168.100.185:8080/goods_order.jsp?cate=m_rec">
	<meta property="og:image" content="./resources/img/doc5_logo.png">
	<!-- webmaster end -->
	<!-- app start-->
	<meta property="al:ios:url" content="applinks://docs">
	<meta property="al:ios:app_store_id" content="12345">
	<meta property="al:ios:app_name" content="App Links">
	<meta property="al:android:url" content="applinks://docs">
	<meta property="al:android:app_name" content="App Links">
	<meta property="al:android:package" content="org.applinks">
	<meta property="al:web:url" content="http://192.168.100.185:8080/goods_order.jsp?cate=m_rec">
	<!-- app end-->
	<link rel="canonical" href="http://192.168.100.185:8080/goods_order.jsp?cate=m_rec">
	<link rel="icon" href="/favicon.ico" sizes="16x16">
	<link rel="icon" href="/favicon.ico" sizes="32x32">

	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@900&display=swap">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;600;700;900&display=swap">

	<link rel="stylesheet" type="text/css" href="https://use.fontawesome.com/releases/v6.0.0/css/all.css">
	
	<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<link rel="stylesheet" type="text/css" href="https://img.79plus.co.kr/megahp/common/css/simple-line-icons.min.css">
	<link rel="stylesheet" type="text/css" href="resources/css/style.css">
	<link rel="stylesheet" type="text/css" href="https://img.79plus.co.kr/megahp/common/css/animate.css">
	<link rel="stylesheet" type="text/css" href="https://img.79plus.co.kr/megahp/common/css/aos.css?ver=202508271045">

	<script type="text/javascript" src="https://code.jquery.com/jquery.min.js"></script>



    <meta charset="UTF-8" />

    <link href="favicon.ico" rel="icon" type="image/x-icon" />


		
	<!-- swiper -->
	<link rel="stylesheet" type="text/css" href="https://img.79plus.co.kr/megahp/common/css/swiper.min.css?ver=202508271045">
	<script type="text/javascript" src="https://img.79plus.co.kr/megahp/common/js/swiper.min.js"></script>

	<script src="resources/js/assets/dist/js/bootstrap.bundle.min.js"></script>
	<script src="resources/js/assets/js/color-modes.js"></script>
<link href="resources/js/assets/dist/css/bootstrap.min.css" rel="stylesheet" />
	<link href="resources/css/sign-in.css" rel="stylesheet" />
	<script type="text/javascript" src="/resources/js/common.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/_inc_modal.jsp" %>
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
	<div class="bscookieframe"><iframe width="2" height="2" src="https://analytics.smartigy.biz/InfoBanners.php?Code=Y2hpbmdvb3BsdXMuY29t" frameborder="0" scrolling="no" marginwidth="0" marginheight="0" vspace="0" hspace="0" allowtransparency="true" frameborder="0" style="background: transparent; border: none;" ></iframe></div>
	
	<div class="overlay none"></div>
	<div class="modal">
		<div class="modal-dialog">
			<div class="modal-content"></div>
		</div>
	</div>

	<div class="wrap">
		<div class="head_wrap">
			<div class="head">
				<div class="head_logo">
					<div class="head_logo">
						<a href="/"><img src="resources/img/doc5_logo.png" class="head_logo_img_fixed"></a>
					</div>
				</div>
				<div class="head_menu_wrap">
					<div class="head_menu_logo t m">
						<img src="https://img.79plus.co.kr/megahp/common/img/logo_w.png">
					</div>
					<div class="head_menu">
						<ul>
							<li>
								<a href="/about/brand/" class="pc">
									5P 스토리
								</a>
								<a class="m head_menu_down">
									5P 스토리
								</a>
								<div class="head_menu_down_menu cont_list cont_list1 cont_list_small cont_list_small1">
									<ul>
										<li>
											<a href="/about/brand/">
												브랜드
											</a>
										</li>
										<li>
											<a href="/about/bi/">
												BI
											</a>
										</li>
										<li>
											<a href="/about/map/">
												오시는 길
											</a>
										</li>
									</ul>
								</div>
							</li>
							<li>
								<a href="/menu/" class="pc">
									메뉴소개
								</a>
								<a class="m head_menu_down">
									메뉴소개
								</a>
								<div class="head_menu_down_menu cont_list cont_list1 cont_list_small cont_list_small1">
									<ul>
										<li>
											<a href="/menu/?menu_category1=1&menu_category2=1">음료</a>
										</li>
										<li>
											<a href="/menu/?menu_category1=2&menu_category2=2">푸드</a>
										</li>
										<li>
											<a href="/menu/?menu_category1=3&menu_category2=3">상품</a>
										</li>
									</ul>
								</div>
							</li>
							<li>
								<a href="/store/find/" class="pc">매장</a>
								<a class="m head_menu_down">매장</a>
								<div class="head_menu_down_menu cont_list cont_list1 cont_list_small cont_list_small1">
									<ul>
										<li>
											<a href="/store/find/">매장찾기</a>
										</li>
									</ul>
								</div>
							</li>
							<li>
								<a href="/startup/cost" class="pc">창업안내</a>
								<a class="m head_menu_down">창업안내</a>
								<div class="head_menu_down_menu cont_list cont_list1 cont_list_small cont_list_small1">
									<ul>
										<li>
											<a href="/startup/cost/">개설비용</a>
										</li>
										<li>
											<a href="/startup/interior/">인테리어</a>
										</li>
										<li>
											<a href="/customer/?db_category=2">창업문의</a>
										</li>
										<li>
											<a href="/customer/?db_category=7">가맹점 인수</a>
										</li>
										<li>
											<a href="/bbs/?bbs_category=7">창업뉴스													</a>
										</li>
									</ul>
								</div>
							</li>
							<li>
								<a href="/bbs/?bbs_category=4" class="pc">5P소식</a>
								<a class="m head_menu_down">5P소식</a>
								<div class="head_menu_down_menu cont_list cont_list1 cont_list_small cont_list_small1">
									<ul>
										<li>
											<a href="/bbs/?bbs_category=1">공지사항</a>
										</li>
										<li>
											<a href="/bbs/?bbs_category=3">이벤트</a>
										</li>
										<li>
											<a href="/bbs/?bbs_category=4">FAQ</a>
										</li>
										<li>
											<a href="/news/voc/">고객의 소리													</a>
										</li>
									</ul>
								</div>
							</li>
							<li>
								<a href="/recruit/job/" class="pc">채용</a>
								<a class="m head_menu_down">채용</a>
								<div class="head_menu_down_menu cont_list cont_list1 cont_list_small cont_list_small1">
									<ul>
										<li>
											<a href="/recruit/job/">채용안내													</a>
										</li>
									</ul>
								</div>
							</li>
						</ul>
					</div>
				</div>
				<!-- <div class="mobile_menu_icon_wrap m t">
					<div class="mobile_menu_icon">
						<div class="mobile_menu_icon_bar bar_black"></div>
					</div>
				</div> -->
			</div>
		</div>
		