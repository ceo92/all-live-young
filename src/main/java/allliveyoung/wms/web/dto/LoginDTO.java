package allliveyoung.wms.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDTO {

    @Email
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
