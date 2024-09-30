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



document.getElementById('addRowBtn').addEventListener('click', function() {
  const table = document.getElementById('outboundrequest_register').getElementsByTagName('tbody')[0];
  const rowCount = table.rows.length;
  const newRow = table.insertRow(rowCount);

  const cell1 = newRow.insertCell(0);
  cell1.innerHTML = rowCount + 1;

  const cell2 = newRow.insertCell(1);
  cell2.innerHTML = '<select name="stockInfo[' + rowCount + ']" class="form-control" required>'
      + '<option value="">선택</option>'
      + '<option value="stock1">재고 1</option>'
      + '<option value="stock2">재고 2</option>'
      + '<option value="stock3">재고 3</option>'
      + '</select>';

  const cell3 = newRow.insertCell(2);
  cell3.innerHTML = '<input type="text" name="availableStock[' + rowCount + ']" class="form-control" readonly>';

  const cell4 = newRow.insertCell(3);
  cell4.innerHTML = '<input type="number" name="quantity[' + rowCount + ']" class="form-control" required min="1">';

  const cell5 = newRow.insertCell(4);
  cell5.innerHTML = '<input type="text" name="recipientName[' + rowCount + ']" class="form-control" required>';

  const cell6 = newRow.insertCell(5);
  cell6.innerHTML = '<input type="text" name="recipientAddress[' + rowCount + ']" class="form-control" required>';

  const cell7 = newRow.insertCell(6);
  cell7.innerHTML = '<input type="text" name="recipientContact[' + rowCount + ']" class="form-control" required>';

  const cell8 = newRow.insertCell(7);
  cell8.innerHTML = '<input type="text" name="note[' + rowCount + ']" class="form-control">';
});

document.getElementById('removeRowBtn').addEventListener('click', function() {
  const table = document.getElementById('outboundrequest_register').getElementsByTagName('tbody')[0];
  const rowCount = table.rows.length;

  if (rowCount > 1) {
    table.deleteRow(rowCount - 1);
  }
});
