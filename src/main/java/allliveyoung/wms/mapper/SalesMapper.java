package allliveyoung.wms.mapper;

import allliveyoung.wms.domain.Sales;
import allliveyoung.wms.web.dto.SalesRequestDTO;
import allliveyoung.wms.web.dto.SumSalesDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface SalesMapper {
    List<Sales> findAll(@Param("requestDTO") SalesRequestDTO requestDTO, @Param("member") Member member);

    Optional<Sales> findById(@Param("id") Long id);

    Long save(@Param("sales") Sales sales);

    void update(@Param("sales") Sales sales);

    void delete(@Param("id") Long id);

    Integer count(@Param("requestDTO") SalesRequestDTO requestDTO, @Param("member") Member member);

    List<SumSalesDTO> findSumSales(Integer year);
}
