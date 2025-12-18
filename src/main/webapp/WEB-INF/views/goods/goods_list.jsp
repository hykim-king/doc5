<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/header/header_order.jsp" %>

	<div class="cont_wrap menu_wrap">
		<div class="cont goods_list">

			<link rel="stylesheet" href="/resources/css/item.css">

<ul id="menuList"></ul>

<script>
// 파라미터 읽기
const urlParams = new URLSearchParams(window.location.search);
let cat1 = urlParams.get("cate");
let cat2 = "";

console.log(cat1)

// ======================
// 소분류 기본값 설정
// ======================
if (!cat2) {
  cat2 = "001";   // 소분류 기본값
}

// ======================
// JSON 파일 경로 매핑
// ======================
function getJsonPath(cat1, cat2) {
	// 대분류 선택
	let base = "";

	if (cat1 == "001") base = "/resources/data/json/coffee";
	else if (cat1 == "002") base = "/resources/data/json/decaf";
	else if (cat1 == "003") base = "/resources/data/json/drink";
	else if (cat1 == "004") base = "/resources/data/json/tea";
	else if (cat1 == "005") base = "/resources/data/json/food";
	else if (cat1 == "006") base = "/resources/data/json/item";
	else base = "/resources/data/json";

	// 소분류 선택 (001 기본)
	if (cat2 === "001") return base+`/001.json`;
	if (cat2 === "002") return base+`/002.json`;
	if (cat2 === "003") return base+`/003.json`;
	if (cat2 === "004") return base+`/004.json`;
	if (cat2 === "005") return base+`/005.json`;
	if (cat2 === "006") return base+`/006.json`;

	console.log("aa");
	// 없으면 기본값
	return base+`/001.json`;
}
console.log(cat1);
const jsonPath = getJsonPath(cat1, cat2);

// ======================
// JSON 불러와서 리스트 출력
// ======================
fetch(jsonPath)
  .then(res => res.json())
  .then(data => {
    const list = document.getElementById("menuList");
    list.innerHTML = "";
	let i=0;
    data.forEach(item => {
      const html = `
        <li>
          <a href="./goodsView?cate=${cate}&goodsno=10001">
            <div class="cont_gallery_list_box">
                <div class="cont_gallery_list_img">
                    <img src="https://img.79plus.co.kr/megahp/manager/upload/menu/20250320000925_1742396965069_ekSqAIVc1L.jpg">
                </div>
                
                <div class="cont_text_box">
                    <div class="cont_text">
                        <div class="cont_text_inner text_wrap cont_text_title">
                            <div class="text text1"><b>`+item.goods_name+`</b></div>
                        </div>
                        <div class="cont_text_inner text_wrap cont_text_info">
                            <div class="text text1">`+item.goods_eng_name+`</div>
                        </div>
						<div class="cont_text_inner text_wrap cont_text_type">
                            <div class="text `+item.goods_type+`">`+item.goods_type+`</div>
                        </div>
						
                    </div>
                </div>
               
            </div>
             </a>
        </li>`;
      i++;
      list.insertAdjacentHTML("beforeend", html);
    });
	if(i>0){
		$(".goods_list_title").css("display","none");
	}
  });
</script>
			<!--
            <div style="text-align:center;font-size:30px">상품 상세 입니다.</div>
            <div style="text-align:center;padding:20px;"><button id="openPopupBtn">옵션 팝업 열기</button></div>
		    -->
		</div>
	</div>
<%@ include file="/WEB-INF/views/common/footer/footer.jsp" %>

