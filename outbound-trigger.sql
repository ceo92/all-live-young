DELIMITER $$
-- 출고요청 코드 생성 트리거
CREATE TRIGGER outbound_request_insert
before INSERT ON outbound_request
FOR EACH ROW
BEGIN
   SET NEW.outbound_request_code = CONCAT('obreq-', DATE_FORMAT(NOW(), '%Y%m%d%H%i%s'), '-', SUBSTRING(UUID(), 1, 4));
   SET NEW.reg_date = now();
END $$

DELIMITER ;

DELIMITER $$
-- 출고요청 수정날짜 업데이트 트리거
CREATE TRIGGER after_outbound_request_updated
after UPDATE ON outbound_request
FOR EACH ROW
BEGIN
   insert outbound_request (mod_date) values (now());
END $$

DELIMITER ;

DELIMITER $$
-- 출고요청 이상유무 -> 상태변경 트리거
CREATE TRIGGER outbound_request_isfault_updated
after UPDATE ON outbound_request
FOR EACH ROW
BEGIN
	IF NEW.is_fault = 1 THEN 
		insert outbound_request (outbound_request_status) values('승인');
    ELSEIF NEW.is_fault = 0 THEN
		insert outbound_request (outbound_request_status) values('반려');
	END IF;
END $$

DELIMITER ;