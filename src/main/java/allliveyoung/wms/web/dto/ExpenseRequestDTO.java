package allliveyoung.wms.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseRequestDTO {
    private String type;
    private String keyword;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    @Builder.Default
    private Integer page = 1;

    @Builder.Default
    private Integer size = 10;

    public Integer getOffset() {
        return (page - 1) * size;
    }
}
