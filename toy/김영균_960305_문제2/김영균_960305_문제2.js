let CURRENT_COIN = 0;
const MAX_COIN = 10000;

const menu = [
  { name: "사이다", price: 500 },
  { name: "콜라", price: 1000 },
  { name: "비타민 음료", price: 300 },
];

document.querySelectorAll(".circle").forEach((coin) => {
  coin.addEventListener("click", () => {
    const now = CURRENT_COIN + Number(coin.dataset.coin);
    if (now > 10000) {
      alert("삽입 가능 금액 : 10,000원 이하");
      return;
    }
    CURRENT_COIN = now;
    console.log(now);
  });
});

document.getElementById("result").addEventListener("click", () => {
  const printBox = document.querySelector(".print-box");

  const available = menu.filter((el) => el.price <= CURRENT_COIN);

  let text = "";

  available.forEach((item, idx) => {
    text += item.name;
    if (idx < available.length - 1) text += ", ";
  });

  printBox.innerHTML = `
        현재 금액: ${CURRENT_COIN} <br/>
        뽑을 수 있는 음료: ${text}
    `;
});
