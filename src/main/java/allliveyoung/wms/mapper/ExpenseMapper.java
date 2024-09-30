package allliveyoung.wms.mapper;

import allliveyoung.wms.domain.Expense;
import allliveyoung.wms.web.dto.ExpenseRequestDTO;
import allliveyoung.wms.web.dto.NetProfitDTO;
import allliveyoung.wms.web.dto.SumExpensesCategoryDTO;
import allliveyoung.wms.web.dto.SumExpensesDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ExpenseMapper {
    List<Expense> findAll(@Param("requestDTO") ExpenseRequestDTO requestDTO, @Param("warehouse_id") Long warehouse_id);

    Optional<Expense> findById(@Param("id") Long id);

    Long save(@Param("expense") Expense expense);

    void update(@Param("expense") Expense expense);

    void delete(@Param("id") Long id);

    Integer count(@Param("requestDTO") ExpenseRequestDTO requestDTO, @Param("warehouse_id") Long warehouse_id);

    List<SumExpensesDTO> findSumExpenses(Integer year);

    List<SumExpensesCategoryDTO> findSumExpensesCategory(Integer year);

    List<NetProfitDTO> findNetProfit();
}
