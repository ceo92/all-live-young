package allliveyoung.wms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendPasswordResetEmail(String toEmail, String resetLink) throws Exception {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("비밀번호 재설정 안내");
            message.setText("안녕하세요,\n\n" +
                    "비밀번호 재설정을 요청하셨습니다. 아래 링크를 클릭하여 비밀번호를 재설정해주세요:\n" +
                    resetLink + "\n\n" +
                    "감사합니다.");
            mailSender.send(message);
        } catch (MailException e) {
            throw new Exception("이메일 전송에 실패했습니다.", e);
        }
    }
}
