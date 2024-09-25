const tableContainer = document.querySelector('.table-scroll');

let isDown = false;
let startX;
let scrollLeft;

tableContainer.addEventListener('mousedown', (e) => {
  isDown = true;
  tableContainer.classList.add('active');
  startX = e.pageX;  // 클릭 시점의 X 좌표를 저장
  scrollLeft = tableContainer.scrollLeft;  // 클릭 시점의 스크롤 위치를 저장
});

tableContainer.addEventListener('mouseleave', () => {
  isDown = false;
});

tableContainer.addEventListener('mouseup', () => {
  isDown = false;
});

tableContainer.addEventListener('mousemove', (e) => {
  if (!isDown) return;  // 마우스 클릭이 되어 있지 않으면 동작하지 않음
  e.preventDefault();
  const x = e.pageX;  // 현재 마우스의 X 좌표
  const walk = (x - startX);  // 이동한 거리
  tableContainer.scrollLeft = scrollLeft - walk;  // 이동한 거리만큼 스크롤
});
