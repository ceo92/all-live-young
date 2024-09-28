package allliveyoung.wms.web.dto;

import allliveyoung.wms.domain.Expense;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseResponseDTO {
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
    private List<Expense> expenses;

    @Builder
    public ExpenseResponseDTO(ExpenseRequestDTO expenseRequestDTO, List<Expense> expenses, Integer total) {
        this.page = expenseRequestDTO.getPage();
        this.size = expenseRequestDTO.getSize();
        this.type = expenseRequestDTO.getType();
        this.keyword = expenseRequestDTO.getKeyword();
        this.dateFrom = expenseRequestDTO.getDateFrom();
        this.dateTo = expenseRequestDTO.getDateTo();
        this.expenses = expenses;
        this.total = total;
        this.last = (int) Math.ceil(total / (double) size);
        this.start = (page - 1) / 10 * 10 + 1;
        this.end = (last == 0) ? 1 : Math.min(start + 9, last);
    }
}
