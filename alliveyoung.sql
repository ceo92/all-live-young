CREATE TABLE `STOCK` (
	`stock_id`	BIGINT	NOT NULL auto_increment,
	`product_id`	BIGINT	NOT NULL,
	`warehouse_id`	BIGINT	NOT NULL,
	`palette_id`	BIGINT	NOT NULL,
	`stock_code`	VARCHAR(30)	NOT NULL,
	`quantity`	INT	NOT NULL,
	`reg_date`	DATETIME	NOT NULL,
	`mod_date`	DATETIME	NOT NULL
);

CREATE TABLE `EXPENSE` (
	`expense_id`	BIGINT	NOT NULL auto_increment,
	`warehouse_id`	BIGINT	NOT NULL,
	`expense_date`	DATE	NOT NULL,
	`expense_category`	ENUM('입고비용', '출고비용', '보관비용', '유지보수비', '운영 인건비', '기타')	NOT NULL,
	`expense_amount`	DECIMAL(10,2)	NOT NULL,
	`expense_description`	LONGTEXT	NULL,
	`reg_date`	DATETIME	NOT NULL,
	`mod_date`	DATETIME	NULL
);

CREATE TABLE `WAREHOUSE` (
	`warehouse_id`	BIGINT	NOT NULL auto_increment,
	`warehouse_manager_id`	BIGINT	NOT NULL,
	`warehouse_name`	VARCHAR(20)	NOT NULL,
	`warehouse_code`	VARCHAR(30)	NOT NULL,
	`warehouse_road_address`	VARCHAR(50)	NOT NULL,
	`warehouse_jibun_address`	VARCHAR(50)	NOT NULL,
	`warehouse_zipcode`	VARCHAR(30)	NOT NULL
);

CREATE TABLE `INBOUND_REQUEST` (
	`inbound_request_id`	BIGINT	NOT NULL auto_increment,
	`inbound_request_code`	VARCHAR(30)	NOT NULL,
	`member_id`	BIGINT	NOT NULL,
	`warehouse_id`	BIGINT	NOT NULL,
	`status`	ENUM('요청 대기중', '요청 반려', '검수중', '검수 완료', '검수 불합')	NOT NULL	DEFAULT '요청 대기중',
	`reg_date`	DATETIME	NOT NULL,
	`mod_date`	DATETIME	NULL
);

CREATE TABLE `INBOUND_REQUEST_PRODUCT` (
	`inbound_product_id`	BIGINT	NOT NULL auto_increment,
	`pallet_quantity`	INT	NOT NULL,
	`box_quantity`	INT	NOT NULL,
	`manufacture_number`	VARCHAR(20)	NOT NULL,
	`expiration_date`	DATETIME	NOT NULL,
	`inbound_request_id`	BIGINT	NOT NULL,
	`product_id`	BIGINT	NOT NULL
);

CREATE TABLE `PRODUCT` (
	`product_id`	BIGINT	NOT NULL auto_increment,
	`product_name`	VARCHAR(50)	NOT NULL,
	`company_id`	BIGINT	NOT NULL,
	`storage_method`	ENUM('상온', '냉동', '냉장')	NOT NULL,
	`designated_type`	ENUM('폭발물', '생물학적제제', '항정및마약')	NOT NULL,
	`product_license_date`	DATETIME	NOT NULL,
	`product_license_num`	INT	NOT NULL
);

CREATE TABLE `INBOUND_INSPECTION` (
	`inbound_inspection_id`	BIGINT	NOT NULL auto_increment,
	`member_id`	BIGINT	NOT NULL,
	`is_fault`	TINYINT(1)	NOT NULL,
	`rejection_note`	VARCHAR(100)	NOT NULL	DEFAULT '',
	`inbound_inspection_code`	VARCHAR(30)	NOT NULL,
	`inbound_inspection_date`	DATETIME	NOT NULL
);

CREATE TABLE `Company` (
	`company_id`	BIGINT	NOT NULL auto_increment,
	`company_name`	VARCHAR(30)	NOT NULL,
	`business_reg_no`	INT	NOT NULL,
	`company_addr`	VARCHAR(100)	NULL,
	`company_addr_detail`	VARCHAR(30)	NULL
);

CREATE TABLE `OUTBOUND_INSPECTION` (
	`outbound_inspection_id`	BIGINT	NOT NULL auto_increment,
	`outbound_inspection_code`	VARCHAR(30)	NOT NULL,
	`is_fault`	TINYINT(1)	NULL,
	`outbound_inspection_date`	DATETIME	NULL,
	`member_id`	BIGINT	NOT NULL	DEFAULT 0,
	`outbound_inspection_note`	VARCHAR(100)	NOT NULL	DEFAULT ''
);

CREATE TABLE `DISPATCH` (
	`dispatch_id`	BIGINT	NOT NULL auto_increment,
	`dispatch_code`	VARCHAR(30)	NOT NULL,
	`dispatch_status`	ENUM('승인대기중', '승인' )	NOT NULL	DEFAULT '승인대기중',
	`member_id`	BIGINT	NOT NULL	DEFAULT 0,
	`reg_date`	DATETIME	NOT NULL,
	`dispatch_date`	DATETIME	NULL,
	`dispatch_note`	VARCHAR(100)	NOT NULL	DEFAULT ''
);

CREATE TABLE `SHIPMENT` (
	`waybill_id`	BIGINT	NOT NULL auto_increment,
	`waybill_number`	BIGINT	NOT NULL,
	`outbound_id`	BIGINT	NOT NULL,
	`delivery_status`	ENUM('배송대기중', '배송중', '배송완료')	NOT NULL	DEFAULT '배송대기중',
	`reg_date`	DATETIME	NOT NULL,
	`departure_date`	DATETIME	NULL,
	`arrival_date`	DATETIME	NULL,
	`waybill_note`	VARCHAR(100)	NOT NULL	DEFAULT ''
);

CREATE TABLE `OUTBOUND` (
	`outbound_id`	BIGINT	NOT NULL auto_increment,
	`outbound_code`	VARCHAR(30)	NOT NULL,
	`dispatch_id`	BIGINT	NOT NULL,
	`member_id`	BIGINT	NOT NULL,
	`outbound_date`	DATETIME	NOT NULL,
	`outbound_note`	VARCHAR(100)	NOT NULL	DEFAULT ''
);

CREATE TABLE `MEMBER` (
	`member_id`	BIGINT	NOT NULL auto_increment,
	`name`	VARCHAR(10)	NOT NULL,
	`password`	VARCHAR(200)	NOT NULL,
	`phone_number`	VARCHAR(20)	NOT NULL,
	`email`	VARCHAR(30)	NOT NULL,
	`road_name_address`	VARCHAR(50)	NOT NULL,
	`details_address`	VARCHAR(30)	NOT NULL,
	`account_status`	ENUM('승인대기', '활성', '탈퇴대기', '비활성')	NOT NULL	DEFAULT '승인대기',
	`join_date`	DATETIME	NOT NULL,
	`last_login_date`	DATETIME	NOT NULL,
	`role_type`	ENUM('총관리자', '창고관리자', '사업자', '배송기사')	NOT NULL
);

CREATE TABLE `DELIVERY` (
	`member_id`	BIGINT	NOT NULL auto_increment,
	`warehouse_id`	BIGINT	NOT NULL,
	`car_number`	VARCHAR(10)	NOT NULL,
	`delivery_code`	VARCHAR(20)	NOT NULL,
	`driver_status`	ENUM('운행 종료', '운행 중')	NOT NULL	DEFAULT '운행 종료'
);

CREATE TABLE `WAREHOUSE_MANAGER` (
	`member_id`	BIGINT	NOT NULL,
	`warehouse_id`	BIGINT	NOT NULL
);

CREATE TABLE `BUSINESS_MAN` (
	`business_man_id`	BIGINT	NOT NULL,
	`business_centificate`	BLOB	NOT NULL,
	`business_number`	VARCHAR(30)	NOT NULL,
	`company_id`	BIGINT	NOT NULL
);

CREATE TABLE `TERMS_OF_INFORMATION` (
	`member_id`	BIGINT	NOT NULL,
	`agree`	TINYINT(1)	NOT NULL	DEFAULT 0,
	`agree_date`	DATETIME	NOT NULL
);

CREATE TABLE `INQUIRY` (
	`inquriy_id`	BIGINT	NOT NULL auto_increment,
	`inquiry_title`	VARCHAR(20)	NOT NULL,
	`inquiry_content`	LONGTEXT	NOT NULL,
	`write_time`	DATETIME	NOT NULL,
	`answer`	TINYINT(1)	NOT NULL	DEFAULT 0,
	`member_id`	BIGINT	NOT NULL
);

CREATE TABLE `ANSWER` (
	`answer_id`	BIGINT	NOT NULL auto_increment,
	`inquriy_id`	BIGINT	NOT NULL,
	`answer_content`	LONGTEXT	NOT NULL,
	`answer_write_time`	DATETIME	NOT NULL,
	`member_id`	BIGINT	NOT NULL
);

CREATE TABLE `ANNOUNCEMENT` (
	`announcement_id`	BIGINT	NOT NULL auto_increment,
	`member_id`	BIGINT	NOT NULL,
	`write_date`	DATETIME	NOT NULL,
	`content`	VARCHAR(100)	NOT NULL,
	`highlight`	TINYINT(1)	NOT NULL	DEFAULT 0
);

CREATE TABLE `SALES` (
	`sales_id`	BIGINT	NOT NULL auto_increment,
	`warehouse_id`	BIGINT	NOT NULL,
	`sales_date`	DATE	NOT NULL,
	`sales_category`	ENUM('계약비용', '기타')	NOT NULL,
	`member_id`	BIGINT	NOT NULL,
	`sales_amount`	DECIMAL(10,2)	NOT NULL,
	`sales_description`	LONGTEXT	NULL,
	`reg_date`	DATETIME	NOT NULL,
	`mod_date`	DATETIME	NULL
);

CREATE TABLE `SECTION` (
	`section_id`	BIGINT	NOT NULL auto_increment,
	`warehouse_id`	BIGINT	NULL,
	`storage_method`	ENUM('상온', '냉동', '냉장')	NOT NULL,
	`designated_type`	ENUM('폭발물', '생물학적제제', '항정및마약')	NOT NULL,
	`section_capacity`	INT	NOT NULL,
	`block_number`	VARCHAR(20)	NOT NULL
);

CREATE TABLE `PALLET` (
	`pallet_id`	BIGINT	NOT NULL auto_increment,
	`inbound_product_id`	BIGINT	NOT NULL,
	`section_id`	BIGINT	NOT NULL
);

CREATE TABLE `OUTBOUND_REQUEST` (
	`outbound_request_code`	VARCHAR(30)	NOT NULL,
	`outbound_request_status`	ENUM('요청 대기중', '요청 반려', '검수중', '검수 완료', '검수 불합')	NOT NULL	DEFAULT '요청 대기중',
	`member_id`	BIGINT	NOT NULL,
	`stock_id`	BIGINT	NOT NULL,
	`order_quantity`	INT	NOT NULL,
	`consignee_name`	VARCHAR(20)	NOT NULL,
	`consignee_address`	VARCHAR(50)	NOT NULL,
	`consignee_phone`	VARCHAR(20)	NOT NULL,
	`reg_date`	DATETIME	NOT NULL,
	`mod_date`	DATETIME	NULL,
	`outbound_note`	VARCHAR(100)	NOT NULL	DEFAULT ''
);

ALTER TABLE `STOCK` ADD CONSTRAINT `PK_STOCK` PRIMARY KEY (
	`stock_id`
);

ALTER TABLE `EXPENSE` ADD CONSTRAINT `PK_EXPENSE` PRIMARY KEY (
	`expense_id`
);

ALTER TABLE `WAREHOUSE` ADD CONSTRAINT `PK_WAREHOUSE` PRIMARY KEY (
	`warehouse_id`
);

ALTER TABLE `INBOUND_REQUEST` ADD CONSTRAINT `PK_INBOUND_REQUEST` PRIMARY KEY (
	`inbound_request_id`
);

ALTER TABLE `INBOUND_REQUEST_PRODUCT` ADD CONSTRAINT `PK_INBOUND_REQUEST_PRODUCT` PRIMARY KEY (
	`inbound_product_id`
);

ALTER TABLE `PRODUCT` ADD CONSTRAINT `PK_PRODUCT` PRIMARY KEY (
	`product_id`
);

ALTER TABLE `INBOUND_INSPECTION` ADD CONSTRAINT `PK_INBOUND_INSPECTION` PRIMARY KEY (
	`inbound_inspection_id`
);

ALTER TABLE `Company` ADD CONSTRAINT `PK_COMPANY` PRIMARY KEY (
	`company_id`
);

ALTER TABLE `OUTBOUND_INSPECTION` ADD CONSTRAINT `PK_OUTBOUND_INSPECTION` PRIMARY KEY (
	`outbound_inspection_id`
);

ALTER TABLE `DISPATCH` ADD CONSTRAINT `PK_DISPATCH` PRIMARY KEY (
	`dispatch_id`
);

ALTER TABLE `SHIPMENT` ADD CONSTRAINT `PK_SHIPMENT` PRIMARY KEY (
	`waybill_id`
);

ALTER TABLE `OUTBOUND` ADD CONSTRAINT `PK_OUTBOUND` PRIMARY KEY (
	`outbound_id`
);

ALTER TABLE `MEMBER` ADD CONSTRAINT `PK_MEMBER` PRIMARY KEY (
	`member_id`
);

ALTER TABLE `DELIVERY` ADD CONSTRAINT `PK_DELIVERY` PRIMARY KEY (
	`member_id`
);

ALTER TABLE `WAREHOUSE_MANAGER` ADD CONSTRAINT `PK_WAREHOUSE_MANAGER` PRIMARY KEY (
	`member_id`
);

ALTER TABLE `BUSINESS_MAN` ADD CONSTRAINT `PK_BUSINESS_MAN` PRIMARY KEY (
	`business_man_id`
);

ALTER TABLE `INQUIRY` ADD CONSTRAINT `PK_INQUIRY` PRIMARY KEY (
	`inquriy_id`
);

ALTER TABLE `ANSWER` ADD CONSTRAINT `PK_ANSWER` PRIMARY KEY (
	`answer_id`
);

ALTER TABLE `ANNOUNCEMENT` ADD CONSTRAINT `PK_ANNOUNCEMENT` PRIMARY KEY (
	`announcement_id`
);

ALTER TABLE `SALES` ADD CONSTRAINT `PK_SALES` PRIMARY KEY (
	`sales_id`
);

ALTER TABLE `SECTION` ADD CONSTRAINT `PK_SECTION` PRIMARY KEY (
	`section_id`
);

ALTER TABLE `PALLET` ADD CONSTRAINT `PK_PALLET` PRIMARY KEY (
	`pallet_id`
);

ALTER TABLE `STOCK` ADD CONSTRAINT `FK_PRODUCT_TO_STOCK_1` FOREIGN KEY (
	`product_id`
)
REFERENCES `PRODUCT` (
	`product_id`
);

ALTER TABLE `STOCK` ADD CONSTRAINT `FK_WAREHOUSE_TO_STOCK_1` FOREIGN KEY (
	`warehouse_id`
)
REFERENCES `WAREHOUSE` (
	`warehouse_id`
);

ALTER TABLE `STOCK` ADD CONSTRAINT `FK_PALLET_TO_STOCK_1` FOREIGN KEY (
	`palette_id`
)
REFERENCES `PALLET` (
	`pallet_id`
);

ALTER TABLE `EXPENSE` ADD CONSTRAINT `FK_WAREHOUSE_TO_EXPENSE_1` FOREIGN KEY (
	`warehouse_id`
)
REFERENCES `WAREHOUSE` (
	`warehouse_id`
);

ALTER TABLE `INBOUND_REQUEST` ADD CONSTRAINT `FK_MEMBER_TO_INBOUND_REQUEST_1` FOREIGN KEY (
	`member_id`
)
REFERENCES `MEMBER` (
	`member_id`
);

ALTER TABLE `INBOUND_REQUEST` ADD CONSTRAINT `FK_WAREHOUSE_TO_INBOUND_REQUEST_1` FOREIGN KEY (
	`warehouse_id`
)
REFERENCES `WAREHOUSE` (
	`warehouse_id`
);

ALTER TABLE `INBOUND_REQUEST_PRODUCT` ADD CONSTRAINT `FK_INBOUND_REQUEST_TO_INBOUND_REQUEST_PRODUCT_1` FOREIGN KEY (
	`inbound_request_id`
)
REFERENCES `INBOUND_REQUEST` (
	`inbound_request_id`
);

ALTER TABLE `INBOUND_REQUEST_PRODUCT` ADD CONSTRAINT `FK_PRODUCT_TO_INBOUND_REQUEST_PRODUCT_1` FOREIGN KEY (
	`product_id`
)
REFERENCES `PRODUCT` (
	`product_id`
);

ALTER TABLE `PRODUCT` ADD CONSTRAINT `FK_Company_TO_PRODUCT_1` FOREIGN KEY (
	`company_id`
)
REFERENCES `Company` (
	`company_id`
);

ALTER TABLE `INBOUND_INSPECTION` ADD CONSTRAINT `FK_INBOUND_REQUEST_TO_INBOUND_INSPECTION_1` FOREIGN KEY (
	`inbound_inspection_id`
)
REFERENCES `INBOUND_REQUEST` (
	`inbound_request_id`
);

ALTER TABLE `INBOUND_INSPECTION` ADD CONSTRAINT `FK_MEMBER_TO_INBOUND_INSPECTION_1` FOREIGN KEY (
	`member_id`
)
REFERENCES `MEMBER` (
	`member_id`
);

ALTER TABLE `OUTBOUND_INSPECTION` ADD CONSTRAINT `FK_MEMBER_TO_OUTBOUND_INSPECTION_1` FOREIGN KEY (
	`member_id`
)
REFERENCES `MEMBER` (
	`member_id`
);

ALTER TABLE `DISPATCH` ADD CONSTRAINT `FK_MEMBER_TO_DISPATCH_1` FOREIGN KEY (
	`member_id`
)
REFERENCES `MEMBER` (
	`member_id`
);

ALTER TABLE `SHIPMENT` ADD CONSTRAINT `FK_OUTBOUND_TO_SHIPMENT_1` FOREIGN KEY (
	`outbound_id`
)
REFERENCES `OUTBOUND` (
	`outbound_id`
);

ALTER TABLE `OUTBOUND` ADD CONSTRAINT `FK_DISPATCH_TO_OUTBOUND_1` FOREIGN KEY (
	`dispatch_id`
)
REFERENCES `DISPATCH` (
	`dispatch_id`
);

ALTER TABLE `OUTBOUND` ADD CONSTRAINT `FK_MEMBER_TO_OUTBOUND_1` FOREIGN KEY (
	`member_id`
)
REFERENCES `MEMBER` (
	`member_id`
);

ALTER TABLE `DELIVERY` ADD CONSTRAINT `FK_MEMBER_TO_DELIVERY_1` FOREIGN KEY (
	`member_id`
)
REFERENCES `MEMBER` (
	`member_id`
);

ALTER TABLE `DELIVERY` ADD CONSTRAINT `FK_WAREHOUSE_TO_DELIVERY_1` FOREIGN KEY (
	`warehouse_id`
)
REFERENCES `WAREHOUSE` (
	`warehouse_id`
);

ALTER TABLE `WAREHOUSE_MANAGER` ADD CONSTRAINT `FK_MEMBER_TO_WAREHOUSE_MANAGER_1` FOREIGN KEY (
	`member_id`
)
REFERENCES `MEMBER` (
	`member_id`
);

ALTER TABLE `WAREHOUSE_MANAGER` ADD CONSTRAINT `FK_WAREHOUSE_TO_WAREHOUSE_MANAGER_1` FOREIGN KEY (
	`warehouse_id`
)
REFERENCES `WAREHOUSE` (
	`warehouse_id`
);

ALTER TABLE `BUSINESS_MAN` ADD CONSTRAINT `FK_MEMBER_TO_BUSINESS_MAN_1` FOREIGN KEY (
	`business_man_id`
)
REFERENCES `MEMBER` (
	`member_id`
);

ALTER TABLE `BUSINESS_MAN` ADD CONSTRAINT `FK_Company_TO_BUSINESS_MAN_1` FOREIGN KEY (
	`company_id`
)
REFERENCES `Company` (
	`company_id`
);

ALTER TABLE `TERMS_OF_INFORMATION` ADD CONSTRAINT `FK_MEMBER_TO_TERMS_OF_INFORMATION_1` FOREIGN KEY (
	`member_id`
)
REFERENCES `MEMBER` (
	`member_id`
);

ALTER TABLE `INQUIRY` ADD CONSTRAINT `FK_MEMBER_TO_INQUIRY_1` FOREIGN KEY (
	`member_id`
)
REFERENCES `MEMBER` (
	`member_id`
);

ALTER TABLE `ANSWER` ADD CONSTRAINT `FK_INQUIRY_TO_ANSWER_1` FOREIGN KEY (
	`inquriy_id`
)
REFERENCES `INQUIRY` (
	`inquriy_id`
);

ALTER TABLE `ANSWER` ADD CONSTRAINT `FK_MEMBER_TO_ANSWER_1` FOREIGN KEY (
	`member_id`
)
REFERENCES `MEMBER` (
	`member_id`
);

ALTER TABLE `ANNOUNCEMENT` ADD CONSTRAINT `FK_MEMBER_TO_ANNOUNCEMENT_1` FOREIGN KEY (
	`member_id`
)
REFERENCES `MEMBER` (
	`member_id`
);

ALTER TABLE `SALES` ADD CONSTRAINT `FK_WAREHOUSE_TO_SALES_1` FOREIGN KEY (
	`warehouse_id`
)
REFERENCES `WAREHOUSE` (
	`warehouse_id`
);

ALTER TABLE `SALES` ADD CONSTRAINT `FK_MEMBER_TO_SALES_1` FOREIGN KEY (
	`member_id`
)
REFERENCES `MEMBER` (
	`member_id`
);

ALTER TABLE `PALLET` ADD CONSTRAINT `FK_INBOUND_REQUEST_PRODUCT_TO_PALLET_1` FOREIGN KEY (
	`inbound_product_id`
)
REFERENCES `INBOUND_REQUEST_PRODUCT` (
	`inbound_product_id`
);

ALTER TABLE `PALLET` ADD CONSTRAINT `FK_SECTION_TO_PALLET_1` FOREIGN KEY (
	`section_id`
)
REFERENCES `SECTION` (
	`section_id`
);

ALTER TABLE `OUTBOUND_REQUEST` ADD CONSTRAINT `FK_MEMBER_TO_OUTBOUND_REQUEST_1` FOREIGN KEY (
	`member_id`
)
REFERENCES `MEMBER` (
	`member_id`
);

ALTER TABLE `OUTBOUND_REQUEST` ADD CONSTRAINT `FK_STOCK_TO_OUTBOUND_REQUEST_1` FOREIGN KEY (
	`stock_id`
)
REFERENCES `STOCK` (
	`stock_id`
);

