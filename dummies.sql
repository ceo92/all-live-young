-- WAREHOUSE 더미 데이터 (먼저 삽입)
INSERT INTO WAREHOUSE (warehouse_id, member_id, warehouse_name, warehouse_code, warehouse_road_address, warehouse_jibun_address, warehouse_zipcode, warehouse_details_address)
VALUES (1, 1, '창고1', 'WH001', '도로명주소1', '지번주소1', '12345', '상세주소1'),
       (2, 2, '창고2', 'WH002', '도로명주소2', '지번주소2', '67890', '상세주소2');

-- MEMBER 더미 데이터 (WAREHOUSE 참조)
INSERT INTO MEMBER (member_id, name, password, phone_number, email, road_name_address, details_address, account_status, join_date, last_login_date, role_type, business_number, warehouse_id, is_agree, agree_date)
VALUES (1, '회원1', 'password1', '010-1111-1111', 'user1@example.com', '도로명주소1', '상세주소1', '활성', NOW(), NOW(), '사업자', '1234567890', 1, 1, NOW()),
       (2, '회원2', 'password2', '010-2222-2222', 'user2@example.com', '도로명주소2', '상세주소2', '비활성', NOW(), NOW(), '창고관리자', '0987654321', 2, 1, NOW());

-- PRODUCT 더미 데이터 (참조 없음)
INSERT INTO PRODUCT (product_id, member_id, product_name, store_temperature, product_type, product_license_date, product_license_num)
VALUES (1, 1, '제품1', '상온', '일반', '2024-09-01', 123456),
       (2, 2, '제품2', '냉동', '폭발물', '2024-09-02', 654321);

-- SECTION 더미 데이터 (WAREHOUSE 참조)
INSERT INTO SECTION (section_id, section_code, warehouse_id, store_temperature, product_type, section_capacity, block_number)
VALUES (1, 'SEC001', 1, '상온', '일반', 1000, 'B001'),
       (2, 'SEC002', 2, '냉동', '폭발물', 2000, 'B002');

-- STOCK 더미 데이터 (PRODUCT, WAREHOUSE, PALETTE 참조)
INSERT INTO STOCK (stock_id, product_id, warehouse_id, palette_id, stock_code, reg_date, mod_date)
VALUES (1, 1, 1, 1, 'STK001', NOW(), NOW()),
       (2, 2, 2, 2, 'STK002', NOW(), NOW());

-- INBOUND_REQUEST 더미 데이터 (WAREHOUSE, MEMBER 참조)
INSERT INTO INBOUND_REQUEST (inbound_request_id, warehouse_id, member_id, inbound_request_code, inbound_request_status, reg_date, mod_date, is_fault, rejection_note)
VALUES (1, 1, 1, 'INREQ001', '처리대기중', NOW(), NOW(), 0, NULL),
       (2, 2, 2, 'INREQ002', '승인', NOW(), NOW(), 1, '반려 사유');

-- INBOUND_REQUEST_PRODUCT 더미 데이터 (INBOUND_REQUEST, PRODUCT 참조)
INSERT INTO INBOUND_REQUEST_PRODUCT (inbound_request_product_id, pallet_quantity, box_quantity, manufacture_number, expiration_date, inbound_request_id, product_id)
VALUES (1, 100, 50, 'MAN001', '2025-01-01', 1, 1),
       (2, 200, 100, 'MAN002', '2025-06-01', 2, 2);

-- PALLET 더미 데이터 (INBOUND_REQUEST_PRODUCT, SECTION 참조)
INSERT INTO PALLET (pallet_id, inbound_request_product_id, section_id)
VALUES (1, 1, 1),
       (2, 2, 2);

-- OUTBOUND_REQUEST 더미 데이터 (MEMBER, STOCK 참조)
INSERT INTO OUTBOUND_REQUEST (outbound_request_id, outbound_request_code, outbound_request_status, member_id, stock_id, order_quantity, recipient_name, recipient_address, recipient_contact, is_fault, rejection_note, reg_date, mod_date, outbound_request_note)
VALUES (1, 'OUTREQ001', '처리대기중', 1, 1, 100, '수령인1', '주소1', '010-1111-1111', 0, NULL, NOW(), NOW(), '요청사항1'),
       (2, 'OUTREQ002', '승인', 2, 2, 200, '수령인2', '주소2', '010-2222-2222', 1, '반려 사유', NOW(), NOW(), '요청사항2');

-- INQUIRY 더미 데이터 (MEMBER 참조)
INSERT INTO INQUIRY (inquriy_id, member_id, inquiry_title, inquiry_content, write_time, answer)
VALUES (1, 1, '문의1', '문의 내용1', NOW(), 0),
       (2, 2, '문의2', '문의 내용2', NOW(), 1);

-- ANSWER 더미 데이터 (INQUIRY, MEMBER 참조)
INSERT INTO ANSWER (answer_id, inquriy_id, member_id, answer_content, answer_write_time)
VALUES (1, 1, 1, '답변 내용1', NOW()),
       (2, 2, 2, '답변 내용2', NOW());

-- ANNOUNCEMENT 더미 데이터 (MEMBER 참조)
INSERT INTO ANNOUNCEMENT (announcement_id, manager_id, write_date, content, is_highlight)
VALUES (1, 1, NOW(), '공지사항 내용1', 1),
       (2, 2, NOW(), '공지사항 내용2', 0);

-- EXPENSE 더미 데이터 (WAREHOUSE 참조)
INSERT INTO EXPENSE (expense_id, warehouse_id, expense_date, expense_category, expense_amount, expense_description, reg_date, mod_date)
VALUES (1, 1, '2024-09-01', '입고비용', 1000.00, '입고비용 설명', NOW(), NOW()),
       (2, 2, '2024-09-02', '출고비용', 2000.00, '출고비용 설명', NOW(), NOW());

-- SALES 더미 데이터 (WAREHOUSE 참조)
INSERT INTO SALES (sales_id, warehouse_id, sales_date, sales_category, sales_amount, sales_description, reg_date, mod_date)
VALUES (1, 1, '2024-09-01', '계약비용', 10000.00, '매출 설명1', NOW(), NOW()),
       (2, 2, '2024-09-02', '기타', 20000.00, '매출 설명2', NOW(), NOW());
