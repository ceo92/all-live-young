package allliveyoung.allliveinbound.mapper;

import allliveyoung.allliveinbound.domain.*;
import allliveyoung.allliveinbound.web.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.*;

@Mapper
public interface InboundRequestMapper {
    List<InboundRequest> findAll(InboundPageRequestDTO inboundPageRequestDTO, Member member);

    List<InboundRequestProduct> findById(@Param("id") Long id);

    Long save(InboundRequestSaveDTO inboundRequestSaveDTO);

    void saveProducts(List<InboundProductSaveDTO> inboundProductSaveDTOList);

    void update(Long id);

    void updateProducts(List<InboundProductUpdateDTO> inboundProductUpdateDTOList);
    //void updateProducts(InboundProductUpdateDTO inboundProductUpdateDTO);

    void delete(Long id);

    void updateStatus(InboundStatusUpdateDTO inboundStatusUpdateDTO);

    int getCount(InboundPageRequestDTO inboundPageRequestDTO, Member member);

    List<Warehouse> getWarehouseList();

    List<Product> getMatchedProductList(Long id);

    Integer countByStatus(String status);

    List<CountRequestDTO> findCountInbound(Integer year);

    List<CountStockDTO> findCountStock(Integer year);
}
