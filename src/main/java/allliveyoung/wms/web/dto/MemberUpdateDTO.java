package allliveyoung.wms.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberUpdateDTO {

    @NotNull
    private Long memberId;

    @NotBlank
    private String phoneNumber;

    private String roadNameAddress;
    private String detailsAddress;

    private String businessNumber;

    private Long warehouseId;
}
