package allliveyoung.wms.web.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesSaveDTO {

    @NotNull(message = "매출일은 필수 입력 값입니다.")
    private LocalDate salesDate;

    private String category;

    private String memberName;

    @NotNull(message = "매출액수는 필수 입력 값입니다.")
    @Positive(message = "양수만 가능합니다.")
    @Max(value = 5000000, message = "5,000,000원 이하만 가능합니다.")
    private Integer amount;

    private String description;
}
