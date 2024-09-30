package allliveyoung.wms.web.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseUpdateDTO {

    private Long id;

    private String category;

    @NotNull(message = "지출액수는 필수 입력 값입니다.")
    @Positive(message = "양수만 가능합니다.")
    @Max(value = 5000000, message = "5,000,000원 이하만 가능합니다.")
    private Integer amount;

    private String description;
}