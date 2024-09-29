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
CREATE TRIGGER before_outbound_request_updated
BEFORE UPDATE ON outbound_request
FOR EACH ROW
BEGIN
    SET NEW.mod_date = NOW();
END $$

DELIMITER ;

DELIMITER $$
-- 출고요청 이상유무 -> 상태변경 트리거
CREATE TRIGGER before_outbound_request_isfault_updated
BEFORE UPDATE ON outbound_request
FOR EACH ROW
BEGIN
    IF NEW.is_fault = 1 THEN 
        SET NEW.outbound_request_status = '승인';
    ELSEIF NEW.is_fault = 0 THEN
        SET NEW.outbound_request_status = '반려';
    END IF;
END $$

DELIMITER ;