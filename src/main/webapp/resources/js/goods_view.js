$(document).ready(function(){
    // =======================
    // 모달 열기 / 닫기
    // =======================
    detailBtn.onclick = () => detailModal.classList.add("show");
    closeDetailBtn.onclick = () => detailModal.classList.remove("show");

    // 샷 선택
    openShotModal.onclick = () => shotModal.classList.add("show");
    closeShotModal.onclick = () => shotModal.classList.remove("show");

    // 저당 선택
    openSugarModal.onclick = () => sugarModal.classList.add("show");
    closeSugarModal.onclick = () => sugarModal.classList.remove("show");

    // 당도 선택
    openSweetModal.onclick = () => sweetModal.classList.add("show");
    closeSweetModal.onclick = () => sweetModal.classList.remove("show");

    // 토핑 선택
    openToppingModal.onclick = () => toppingModal.classList.add("show");
    closeToppingModal.onclick = () => toppingModal.classList.remove("show");
});

const shotChecks = document.querySelectorAll("input[name='shotOption']");

shotChecks.forEach(box => {
    box.addEventListener("change", () => {

        const checkedItems = document.querySelectorAll("input[name='shotOption']:checked");

        if (checkedItems.length > 1) {
            alert("최대 1개까지 선택할 수 있습니다.");
            box.checked = false; // 방금 선택한 것 취소
        }
    });
});

function getShotOption() {
    const selected = document.querySelector("input[name='shotOption']:checked");
    return selected ? selected.value : null;

    // 적용하기 버튼
    document.getElementById("applyShot").onclick = () => {
        
        const selected = document.querySelector("input[name='shotOption']:checked");

        if (!selected) {
            alert("샷 옵션을 선택하세요.");
            return;
        }

        // data-label 값 가져오기
        const label = selected.getAttribute("data-label");

        // 결과 표시
        document.getElementById("shotResult").innerText = label;

        // 모달 닫기
        shotModal.classList.remove("show");
        
        updatePrice();
    };
}

// 적용하기 버튼
document.getElementById("applyShot").onclick = () => {

    const selected = document.querySelector("input[name='shotOption']:checked");

    if (!selected) {
        alert("샷 옵션을 선택하세요.");
        return;
    }

    const label = selected.getAttribute("data-label");

    document.getElementById("shotResult").innerText = label;

    shotModal.classList.remove("show");

    updatePrice();
};

document.getElementById("applySugar").onclick = () => {
    const checked = document.querySelector("input[name='sugarOption']:checked");

    const result = document.getElementById("sugarResult");

    if (!checked) {
        result.innerText = ""; // 선택 해제 시 텍스트 삭제
    } else {
        result.innerText = checked.getAttribute("data-label");
    }

    sugarModal.classList.remove("show");

    updatePrice();
};

applySweet.onclick = () => {
    const selected = document.querySelectorAll("input[name='sweetOption']:checked");

    let resultText = "";

    selected.forEach(item => {
        resultText += item.getAttribute("data-label") + " / ";
    });

    // 마지막 / 제거
    resultText = resultText.replace(/ \/ $/, "");

    document.getElementById("sweetResult").innerText = resultText;

    sweetModal.classList.remove("show");

    updatePrice();
};

// 토핑 체크박스 하나만 선택 가능
const toppingBoxes = document.querySelectorAll("input[name='toppingOption']");

toppingBoxes.forEach(box => {
    box.addEventListener("change", () => {

        // 선택된 체크박스 개수 찾기
        const checked = document.querySelectorAll("input[name='toppingOption']:checked");

        if (checked.length > 1) {
            alert("최대 1개까지 선택할 수 있습니다.");
            box.checked = false; // 방금 체크한 것 취소
        }
    });
});

applyTopping.onclick = () => {

    const checked = document.querySelector("input[name='toppingOption']:checked");

    if (!checked) {
        document.getElementById("toppingResult").innerText = "";
    } else {
        document.getElementById("toppingResult").innerText =
            checked.getAttribute("data-label");
    }

    toppingModal.classList.remove("show");

    updatePrice();
};

let basePrice = 3000;
let qty = 1;

function updatePrice() {
    const optionPrice = calcOptionPrice();  // ★ 옵션 가격 합산
    const total = (basePrice + optionPrice) * qty;

    document.getElementById("totalPrice").innerText = 
        total.toLocaleString() + "원";
}

qtyPlus.onclick = () => {
    qty++;
    orderQty.innerText = qty;
    updatePrice();
};

qtyMinus.onclick = () => {
    if (qty > 1) qty--;
    orderQty.innerText = qty;
    updatePrice();
};

orderBtn.onclick = () => {
    alert(`주문하기\n수량: ${qty}개\n총 금액: ${(basePrice * qty).toLocaleString()}원`);
};

addCartBtn.onclick = () => {
    alert("상품을 장바구니에 담았습니다!");
};

updatePrice();

//총 옵션 가격 계산
function calcOptionPrice() {
    let optionTotal = 0;

    // ✔ 샷 옵션 1개 선택
    const shot = document.querySelector("input[name='shotOption']:checked");
    if (shot) optionTotal += Number(shot.dataset.price);

    // ✔ 저당 옵션 1개(체크박스)
    const sugar = document.querySelector("input[name='sugarOption']:checked");
    if (sugar) optionTotal += Number(sugar.dataset.price);

    // ✔ 당도 옵션 (2개 중 복수 선택 가능)
    const sweetOptions = document.querySelectorAll("input[name='sweetOption']:checked");
    sweetOptions.forEach(opt => optionTotal += Number(opt.dataset.price));

    // ✔ 토핑 중복 불가
    const topping = document.querySelector("input[name='toppingOption']:checked");
    if (topping) optionTotal += Number(topping.dataset.price);

    return optionTotal;
}


const tempButtons = document.querySelectorAll(".temp-btn");

tempButtons.forEach(btn => {
    btn.addEventListener("click", () => {

        // 모든 버튼 비활성화
        tempButtons.forEach(b => b.classList.remove("active"));

        // 클릭된 버튼만 활성화
        btn.classList.add("active");

        // 선택된 값 확인 (ICE/HOT)
        const selectedTemp = btn.dataset.value;

        console.log("현재 선택된 온도:", selectedTemp);
    });
});

