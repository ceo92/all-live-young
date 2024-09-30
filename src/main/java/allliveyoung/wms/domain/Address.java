package allliveyoung.wms.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @NotBlank(message = "도로명 주소는 필수 입력 값입니다.")
    @Size(max = 50, message = "도로명 주소는 최대 50자까지 가능합니다.")
    private String roadNameAddress;

    @NotBlank(message = "지번 주소는 필수 입력 값입니다.")
    @Size(max = 50, message = "지번 주소는 최대 50자까지 가능합니다.")
    private String jibunAddress;

    @NotBlank(message = "우편번호는 필수 입력 값입니다.")
    @Size(max = 30, message = "우편번호는 최대 30자까지 가능합니다.")
    private String zipcode;

    @NotBlank(message = "상세 주소는 필수 입력 값입니다.")
    @Size(max = 50, message = "상세 주소는 최대 50자까지 가능합니다.")
    private String detailsAddress;
}
