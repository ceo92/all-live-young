package allliveyoung.wms.mapper;

import allliveyoung.wms.domain.Expense;
import allliveyoung.wms.web.dto.ExpenseRequestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ExpenseMapper {
    List<Expense> findAll(@Param("requestDTO") ExpenseRequestDTO requestDTO);

    Long save(@Param("expense") Expense expense);

    void update(@Param("expense") Expense expense);

    Optional<Expense> findById(@Param("id") Long id);

    void delete(@Param("id") Long id);

    Integer count(@Param("requestDTO") ExpenseRequestDTO requestDTO);
}
