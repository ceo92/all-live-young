package allliveyoung.wms.web.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindAccountDTO {

    @NotEmpty(message = "이름은 필수 입력 사항입니다.")
    private String name;

    @NotEmpty(message = "전화번호는 필수 입력 사항입니다.")
    private String phoneNumber;
}
