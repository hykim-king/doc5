<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="_header_search">
	<!-- pop_title -->
	<div class="search_title">
		<p>"찾으시는 제품"을 검색해주세요</p>
		<button type="button" class="h_search_close_btn ly_btn_close">{=__('')}</button>
	</div>
	<!-- //pop_title -->

	<div class="layer_search_box">
		<form name="frmSearchTop" id="frmSearchTop" action="../goods/goods_search.php" method="get">
			<!--{ ? adUrl }-->
			<input type="hidden" name="adUrl" value="{adUrl}">
			<!--{ / }-->
			<!--{ ? gd_get('scmCode') }-->
			<input type="hidden" name="scmCode" value="{=gd_get('scmCode')}">
			<!--{ / }-->
			<fieldset>
				<legend>{=__('검색하기')}</legend>
				<dl>
					<dt><label for="sch">{=__('검색')}</label></dt>
					<dd>
						<div class="keyword_box">
							<input type="search" name="keyword" class="js_auto_focus" title="{adKeyword}" placeholder="{adKeyword}" id="sch" placeholder="{=__('검색어를 입력해주세요.')}" autocomplete="off">
							<!-- <button type="button" class="bn_wrg" style="display:block">{=__('취소')}</button> -->
						</div>
					</dd>
				</dl>
			</fieldset>
			<button type="submit" class="h_search_btn sear_btn_icon">{=__('')}</button>

			{=includeWidget('proc/_autocomplete_search.html', 'mode', 'top')}
		</form>
	</div>
	<!-- //layer_search_box -->

	<div class="layer_search_content ly_ct">
		<div class="search_bx_area">
			<!--{ ? recentCount > 0 }-->
			<div class="latest_search_tab2" data-type="recent">
				<h3 class="fl">{=__('최근검색어')}</h3>
				<!--{ ? recentKeyword }-->
				<div class="js_recent_all_delete latest_search_del">{=__('전체삭제')}</div>
				<!--{ / }-->
			</div>
			<div class="latest_search_list_box recent_list">
				<ul class="latest_search_list">
					<!--{ ? recentKeyword }-->
					<!--{ @ recentKeyword }-->
					<li>
						<div class="link"><a href="../goods/goods_search.php?keyword={=urlencode(.value_[0])}">{=.value_[0]}</a></div>
						<div class="date">{=.value_[1]}</div>
						<div class="keyword_delete"><button type="button" class="js_recent_keyword_delete" data-recent-keyword="{=.value_[0]}">{=__('삭제')}</button></div>
					</li>
					<!--{ / }-->
					<!--{ : }-->
					<li><a>{=__('최근 검색어가 없습니다.')}</a></li>
					<!--{ / }-->
				</ul>
			</div>
			<!--{ / }-->
		</div>
		<div class="search_bx_area search_bx_area2">
			<!--{ ? hitKeywordConfig.keyword }-->
			<div class="latest_search_tab2" data-type="top"><h3>{=__('인기검색어')}</h3></div>
			<div class="latest_search_list_box">
				<ul class="latest_search_list">
					<!--{ @ hitKeywordConfig.keyword }-->
					<li class="popular popular2"><a href="../goods/goods_search.php?keyword={=urlencode(.value_)}">{=.value_}</a></li>
					<!--{ / }-->
				</ul>
			</div>
			<!--{ / }-->
			{=includeWidget('proc/_recom_goods.html')}
		</div>
		<div class="search_bx_area search_bx_area3">
			<div class="latest_search_tab3" data-type="top"><h3>{=__('인기카테고리')}</h3></div>
			<div class="latest_search_list_box3">
				<ul class="latest_search_list">
					<li><a href="/goods/goods_list.php?cateCd=004001">세탁기</a></li>
					<li><a href="/goods/goods_list.php?cateCd=004002">건조기</a></li>
					<li><a href="/goods/goods_list.php?cateCd=004003">의류관리기</a></li>
					<li><a href="/goods/goods_list.php?cateCd=004008">정수기</a></li>
					<li><a href="/goods/goods_list.php?cateCd=004009">비데</a></li>
					<li><a href="/goods/goods_list.php?cateCd=005005">냉장고</a></li>
					<li><a href="/goods/goods_list.php?cateCd=005015">김치냉장고</a></li>
					<li><a href="/goods/goods_list.php?cateCd=005014">전기레인지</a></li>
					<li><a href="/goods/goods_list.php?cateCd=005010">음식물처리기</a></li>
					<li><a href="/goods/goods_list.php?cateCd=006001">에어컨</a></li>
					<li><a href="/goods/goods_list.php?cateCd=006002">냉난방기</a></li>
					<li><a href="/goods/goods_list.php?cateCd=007006">안마의자</a></li>
					<li><a href="/goods/goods_list.php?cateCd=007010">안마기기</a></li>
					<li><a href="/goods/goods_list.php?cateCd=007007">사우나기</a></li>
					<li><a href="/goods/goods_list.php?cateCd=007008">운동기기</a></li>
					<li><a href="/goods/goods_list.php?cateCd=007012">반려동물용품</a></li>
					<li><a href="/goods/goods_list.php?option_view=&smartFiltering%5B008009%5D%5B39%5D%5B%5D=%EB%AA%A8%EC%85%98%EB%B2%A0%EB%93%9C&cpageNum=100&cateCd=008009&pageNum=100" target="_blank">모션베드</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- //layer_search -->