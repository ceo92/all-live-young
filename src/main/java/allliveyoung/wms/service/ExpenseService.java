package allliveyoung.wms.service;

import allliveyoung.wms.domain.Expense;
import allliveyoung.wms.domain.Warehouse;
import allliveyoung.wms.mapper.ExpenseMapper;
import allliveyoung.wms.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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
    public ExpenseResponseDTO findExpenses(ExpenseRequestDTO expenseRequestDTO, Long warehouse_id) {
        return ExpenseResponseDTO.builder()
                .expenseRequestDTO(expenseRequestDTO)
                .expenses(expenseMapper.findAll(expenseRequestDTO, warehouse_id))
                .total(expenseMapper.count(expenseRequestDTO, warehouse_id))
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

    /**
     * 지출 등록
     *
     * @param expenseSaveDTO
     * @return
     */
    @Transactional
    public Long saveExpense(ExpenseSaveDTO expenseSaveDTO, Warehouse warehouse) {
        return expenseMapper.save(Expense.builder()
                .warehouse(warehouse)
                .expenseDate(expenseSaveDTO.getExpenseDate())
                .category(expenseSaveDTO.getCategory())
                .amount(expenseSaveDTO.getAmount())
                .description(expenseSaveDTO.getDescription())
                .regDate(LocalDateTime.now())
                .build());
    }

    /**
     * 지출 수정
     *
     * @param expenseUpdateDTO
     */
    @Transactional
    public void updateExpense(ExpenseUpdateDTO expenseUpdateDTO) {
        Expense expense = findExpense(expenseUpdateDTO.getId());
        expenseMapper.update(Expense.builder()
                .id(expense.getId())
                .warehouse(expense.getWarehouse())
                .expenseDate(expense.getExpenseDate())
                .category(expenseUpdateDTO.getCategory())
                .amount(expenseUpdateDTO.getAmount())
                .description(expenseUpdateDTO.getDescription())
                .regDate(expense.getRegDate())
                .modDate(LocalDateTime.now())
                .build());
    }

    /**
     * 지출 삭제
     *
     * @param id
     */
    @Transactional
    public void deleteExpense(Long id) {
        expenseMapper.delete(id);
    }

    @Transactional(readOnly = true)
    public List<SumExpensesDTO> findSumExpenses(Integer year) {
        return expenseMapper.findSumExpenses(year);
    }

    @Transactional(readOnly = true)
    public List<SumExpensesCategoryDTO> findSumExpensesCategory(Integer year) {
        return expenseMapper.findSumExpensesCategory(year);
    }

    @Transactional(readOnly = true)
    public List<NetProfitDTO> findNetProfit() {
        return expenseMapper.findNetProfit();
    }
}
