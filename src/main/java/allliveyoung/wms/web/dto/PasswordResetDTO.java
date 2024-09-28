package allliveyoung.wms.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordResetDTO {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String newPassword;

// 토큰 검증
    private String resetToken;
}
