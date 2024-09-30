package allliveyoung.wms.web.dto;

import allliveyoung.wms.domain.RoleType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberSaveDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @NotBlank
    private String phoneNumber;

    @Email
    @NotBlank
    private String email;

    private String roadNameAddress;
    private String detailsAddress;

    @NotNull
    private RoleType roleType;

    private String businessNumber;

    private Boolean isAgree;
}
