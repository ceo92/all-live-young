package allliveyoung.wms.service;

public interface EmailService {
    void sendPasswordResetEmail(String toEmail, String resetLink) throws Exception;
}
