package allliveyoung.wms.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordResetToken {
    private Long id;
    private String token;
    private Long memberId;
    private LocalDateTime expirationTime;
}
