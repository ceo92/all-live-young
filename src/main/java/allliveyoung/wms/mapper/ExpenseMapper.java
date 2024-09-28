package allliveyoung.wms.mapper;

import allliveyoung.wms.domain.Expense;
import allliveyoung.wms.web.dto.ExpenseRequestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExpenseMapper {
    List<Expense> findAll(@Param("requestDTO") ExpenseRequestDTO requestDTO);

    Integer count(@Param("requestDTO") ExpenseRequestDTO requestDTO);
}
