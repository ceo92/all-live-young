package allliveyoung.wms.service;

public interface EmailService {
    void sendPasswordResetEmail(String toEmail, String resetLink) throws Exception;
    // 다른 이메일 관련 메서드를 추가할 수 있습니다.
}
