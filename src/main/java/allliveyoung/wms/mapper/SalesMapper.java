package allliveyoung.wms.mapper;

import allliveyoung.wms.domain.Sales;
import allliveyoung.wms.web.dto.SalesRequestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface SalesMapper {
    List<Sales> findAll(@Param("requestDTO") SalesRequestDTO requestDTO);

    Optional<Sales> findById(@Param("id") Long id);

    Integer count(@Param("requestDTO") SalesRequestDTO requestDTO);

}
