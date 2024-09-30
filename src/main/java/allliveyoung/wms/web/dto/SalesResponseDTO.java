package allliveyoung.wms.web.dto;

import allliveyoung.wms.domain.Sales;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesResponseDTO {

    private Integer page;
    private Integer size;
    private String type;
    private String keyword;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Integer total;
    private Integer last;
    private Integer start;
    private Integer end;
    private List<Sales> sales;

    @Builder
    public SalesResponseDTO(SalesRequestDTO salesRequestDTO, List<Sales> sales, Integer total) {
        this.page = salesRequestDTO.getPage();
        this.size = salesRequestDTO.getSize();
        this.type = salesRequestDTO.getType();
        this.keyword = salesRequestDTO.getKeyword();
        this.dateFrom = salesRequestDTO.getDateFrom();
        this.dateTo = salesRequestDTO.getDateTo();
        this.sales = sales;
        this.total = total;
        this.last = (int) Math.ceil(total / (double) size);
        this.start = (page - 1) / 10 * 10 + 1;
        this.end = (last == 0) ? 1 : Math.min(start + 9, last);
    }
}
