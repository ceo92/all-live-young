CREATE TABLE `MEMBER` (
	`member_id` BIGINT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(10) NOT NULL,
	`password` VARCHAR(200) NOT NULL,
	`phone_number` VARCHAR(20) NOT NULL,
	`email` VARCHAR(30) NOT NULL,
	`road_name_address` VARCHAR(50) NOT NULL,
	`details_address` VARCHAR(30) NOT NULL,
	`account_status` ENUM('승인대기', '활성', '탈퇴대기', '비활성') NOT NULL DEFAULT '승인대기',
	`join_date` DATETIME NOT NULL,
	`last_login_date` DATETIME NOT NULL,
	`role_type` ENUM('총관리자', '창고관리자', '사업자') NOT NULL,
	`business_number` VARCHAR(30) NULL,
	`warehouse_id` BIGINT NULL,
	`is_agree` TINYINT NOT NULL,
	`agree_date` DATETIME NOT NULL,
	PRIMARY KEY (`member_id`)
);

CREATE TABLE `WAREHOUSE` (
	`warehouse_id` BIGINT NOT NULL AUTO_INCREMENT,
	`member_id` BIGINT NOT NULL,
	`warehouse_name` VARCHAR(20) NOT NULL,
	`warehouse_code` VARCHAR(30) NOT NULL,
	`warehouse_road_address` VARCHAR(50) NOT NULL,
	`warehouse_jibun_address` VARCHAR(50) NOT NULL,
	`warehouse_zipcode` VARCHAR(30) NOT NULL,
	`warehouse_details_address` VARCHAR(20) NOT NULL,
	PRIMARY KEY (`warehouse_id`),
	FOREIGN KEY (`member_id`) REFERENCES `MEMBER`(`member_id`)
);

CREATE TABLE `PRODUCT` (
	`product_id` BIGINT NOT NULL AUTO_INCREMENT,
	`member_id` BIGINT NOT NULL,
	`product_name` VARCHAR(50) NOT NULL,
	`store_temperature` ENUM('상온', '냉동', '냉장') NOT NULL,
	`product_type` ENUM('폭발물', '생물학적제제', '항정및마약', '일반') NOT NULL,
	`product_license_date` DATETIME NOT NULL,
	`product_license_num` INT NOT NULL,
	PRIMARY KEY (`product_id`),
	FOREIGN KEY (`member_id`) REFERENCES `MEMBER`(`member_id`)
);

CREATE TABLE `STOCK` (
	`stock_id` BIGINT NOT NULL AUTO_INCREMENT,
	`product_id` BIGINT NOT NULL,
	`warehouse_id` BIGINT NOT NULL,
	`palette_id` BIGINT NOT NULL,
	`stock_code` VARCHAR(30) NOT NULL,
	`reg_date` DATETIME NOT NULL,
	`mod_date` DATETIME NOT NULL,
	PRIMARY KEY (`stock_id`),
	FOREIGN KEY (`product_id`) REFERENCES `PRODUCT`(`product_id`),
	FOREIGN KEY (`warehouse_id`) REFERENCES `WAREHOUSE`(`warehouse_id`)
);

CREATE TABLE `INBOUND_REQUEST` (
	`inbound_request_id` BIGINT NOT NULL AUTO_INCREMENT,
	`warehouse_id` BIGINT NOT NULL,
	`member_id` BIGINT NOT NULL,
	`inbound_request_code` VARCHAR(30) NOT NULL,
	`inbound_request_status` ENUM('처리대기중', '승인','반려') NOT NULL DEFAULT '처리대기중',
	`reg_date` DATETIME NOT NULL,
	`mod_date` DATETIME NULL,
	`is_fault` TINYINT(1) NULL,
	`rejection_note` LONGTEXT NULL,
	PRIMARY KEY (`inbound_request_id`),
	FOREIGN KEY (`warehouse_id`) REFERENCES `WAREHOUSE`(`warehouse_id`),
	FOREIGN KEY (`member_id`) REFERENCES `MEMBER`(`member_id`)
);

CREATE TABLE `INBOUND_REQUEST_PRODUCT` (
	`inbound_request_product_id` BIGINT NOT NULL AUTO_INCREMENT,
	`pallet_quantity` INT NOT NULL,
	`box_quantity` INT NOT NULL,
	`manufacture_number` VARCHAR(20) NOT NULL,
	`expiration_date` DATETIME NOT NULL,
	`inbound_request_id` BIGINT NOT NULL,
	`product_id` BIGINT NOT NULL,
	PRIMARY KEY (`inbound_request_product_id`),
	FOREIGN KEY (`inbound_request_id`) REFERENCES `INBOUND_REQUEST`(`inbound_request_id`),
	FOREIGN KEY (`product_id`) REFERENCES `PRODUCT`(`product_id`)
);

CREATE TABLE `OUTBOUND_REQUEST` (
	`outbound_request_id` BIGINT NOT NULL AUTO_INCREMENT,
	`outbound_request_code` VARCHAR(30) NOT NULL,
	`outbound_request_status` ENUM('처리대기중', '승인', '반려') NOT NULL DEFAULT '처리대기중',
	`member_id` BIGINT NOT NULL,
	`stock_id` BIGINT NOT NULL,
	`order_quantity` INT NOT NULL,
	`recipient_name` VARCHAR(20) NOT NULL,
	`recipient_address` VARCHAR(50) NOT NULL,
	`recipient_contact` VARCHAR(20) NOT NULL,
	`is_fault` TINYINT(1) NULL,
	`rejection_note` LONGTEXT NULL,
	`reg_date` DATETIME NOT NULL,
	`mod_date` DATETIME NULL,
	`outbound_request_note` VARCHAR(100) NULL,
	PRIMARY KEY (`outbound_request_id`),
	FOREIGN KEY (`member_id`) REFERENCES `MEMBER`(`member_id`),
	FOREIGN KEY (`stock_id`) REFERENCES `STOCK`(`stock_id`)
);

CREATE TABLE `PALLET` (
	`pallet_id` BIGINT NOT NULL AUTO_INCREMENT,
	`inbound_request_product_id` BIGINT NOT NULL,
	`section_id` BIGINT NOT NULL,
	PRIMARY KEY (`pallet_id`),
	FOREIGN KEY (`inbound_request_product_id`) REFERENCES `INBOUND_REQUEST_PRODUCT`(`inbound_request_product_id`)
);

CREATE TABLE `EXPENSE` (
	`expense_id` BIGINT NOT NULL AUTO_INCREMENT,
	`warehouse_id` BIGINT NOT NULL,
	`expense_date` DATE NOT NULL,
	`expense_category` ENUM('입고비용', '출고비용', '보관비용', '유지보수비', '운영 인건비', '기타') NOT NULL,
	`expense_amount` DECIMAL(10,2) NOT NULL,
	`expense_description` LONGTEXT NULL,
	`reg_date` DATETIME NOT NULL,
	`mod_date` DATETIME NULL,
	PRIMARY KEY (`expense_id`),
	FOREIGN KEY (`warehouse_id`) REFERENCES `WAREHOUSE`(`warehouse_id`)
);

CREATE TABLE `SALES` (
	`sales_id` BIGINT NOT NULL AUTO_INCREMENT,
	`warehouse_id` BIGINT NOT NULL,
	`sales_date` DATE NOT NULL,
	`sales_category` ENUM('계약비용', '기타') NOT NULL,
	`sales_amount` DECIMAL(10,2) NOT NULL,
	`sales_description` LONGTEXT NULL,
	`reg_date` DATETIME NOT NULL,
	`mod_date` DATETIME NULL,
	PRIMARY KEY (`sales_id`),
	FOREIGN KEY (`warehouse_id`) REFERENCES `WAREHOUSE`(`warehouse_id`)
);

CREATE TABLE `INQUIRY` (
	`inquriy_id` BIGINT NOT NULL AUTO_INCREMENT,
	`member_id` BIGINT NOT NULL,
	`inquiry_title` VARCHAR(20) NOT NULL,
	`inquiry_content` LONGTEXT NOT NULL,
	`write_time` DATETIME NOT NULL,
	`answer` TINYINT(1) NOT NULL DEFAULT 0,
	PRIMARY KEY (`inquriy_id`),
	FOREIGN KEY (`member_id`) REFERENCES `MEMBER`(`member_id`)
);

CREATE TABLE `ANSWER` (
	`answer_id` BIGINT NOT NULL AUTO_INCREMENT,
	`inquriy_id` BIGINT NOT NULL,
	`member_id` BIGINT NOT NULL,
	`answer_content` LONGTEXT NOT NULL,
	`answer_write_time` DATETIME NOT NULL,
	PRIMARY KEY (`answer_id`),
	FOREIGN KEY (`inquriy_id`) REFERENCES `INQUIRY`(`inquriy_id`),
	FOREIGN KEY (`member_id`) REFERENCES `MEMBER`(`member_id`)
);

CREATE TABLE `ANNOUNCEMENT` (
	`announcement_id` BIGINT NOT NULL AUTO_INCREMENT,
	`manager_id` BIGINT NOT NULL,
	`write_date` DATETIME NOT NULL,
	`content` VARCHAR(100) NOT NULL,
	`is_highlight` TINYINT(1) NOT NULL DEFAULT 0,
	PRIMARY KEY (`announcement_id`),
	FOREIGN KEY (`manager_id`) REFERENCES `MEMBER`(`member_id`)
);

CREATE TABLE `SECTION` (
    `section_id` BIGINT NOT NULL AUTO_INCREMENT,
    `section_code` VARCHAR(10) NOT NULL,
    `warehouse_id` BIGINT NOT NULL,
    `store_temperature` ENUM('상온', '냉동', '냉장') NOT NULL,
    `product_type` ENUM('폭발물', '생물학적제제', '항정및마약', '일반') NOT NULL,
    `section_capacity` INT NOT NULL,
    `block_number` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`section_id`),
    FOREIGN KEY (`warehouse_id`) REFERENCES `WAREHOUSE`(`warehouse_id`)
);
