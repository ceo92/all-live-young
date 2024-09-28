package allliveyoung.wms.service;

import allliveyoung.wms.domain.Expense;
import allliveyoung.wms.mapper.ExpenseMapper;
import allliveyoung.wms.web.dto.ExpenseRequestDTO;
import allliveyoung.wms.web.dto.ExpenseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseMapper expenseMapper;

    /**
     * 지출 전체 항목 조회
     *
     * @param expenseRequestDTO
     * @return
     */
    @Transactional(readOnly = true)
    public ExpenseResponseDTO findExpenses(ExpenseRequestDTO expenseRequestDTO) {
        return ExpenseResponseDTO.builder()
                .expenseRequestDTO(expenseRequestDTO)
                .expenses(expenseMapper.findAll(expenseRequestDTO))
                .total(expenseMapper.count(expenseRequestDTO))
                .build();
    }

    /**
     * 지출 단일 항목 조회
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public Expense findExpense(Long id) {
        return expenseMapper.findById(id).orElseThrow(() -> new IllegalArgumentException("잘못된 id 값입니다."));
    }
}
