package allliveyoung.wms.mapper;

import allliveyoung.wms.domain.Member;
import allliveyoung.wms.domain.Product;
import allliveyoung.wms.domain.Warehouse;
import allliveyoung.wms.web.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.*;

@Mapper
public interface InboundRequestMapper {
    List<allliveyoung.allliveinbound.web.dto.InboundRequest> findAll(InboundPageRequestDTO inboundPageRequestDTO, Member member);

    List<InboundRequestProduct> findById(@Param("id") Long id);

    Long save(InboundRequestSaveDTO inboundRequestSaveDTO);

    void saveProducts(List<InboundProductSaveDTO> inboundProductSaveDTOList);

    void update(Long id);

    void updateProducts(List<InboundProductUpdateDTO> inboundProductUpdateDTOList);
    //void updateProducts(InboundProductUpdateDTO inboundProductUpdateDTO);

    void delete(Long id);

    void updateStatus(Map<String, Object> map);

    int getCount(InboundPageRequestDTO inboundPageRequestDTO, Member member);

    List<Warehouse> getWarehouseList();

    List<Product> getMatchedProductList(Long id);

    Integer countByStatus(String status);

    List<CountRequestDTO> findCountInbound(Integer year);

    List<CountStockDTO> findCountStock(Integer year);
}
