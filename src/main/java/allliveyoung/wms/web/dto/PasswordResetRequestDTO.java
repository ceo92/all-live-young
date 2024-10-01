package allliveyoung.wms.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordResetRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String businessNumber;
}
