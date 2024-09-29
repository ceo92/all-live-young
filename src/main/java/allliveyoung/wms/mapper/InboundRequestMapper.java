package allliveyoung.allliveinbound.mapper;

import allliveyoung.allliveinbound.domain.InboundRequest;
import allliveyoung.allliveinbound.domain.InboundRequestProduct;
import allliveyoung.allliveinbound.domain.Warehouse;
import allliveyoung.allliveinbound.web.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.*;

@Mapper
public interface InboundRequestMapper {
    List<InboundRequest> findAll(InboundPageRequestDTO inboundPageRequestDTO);

    List<InboundRequestProduct> findById(@Param("id") Long id);

    void save(InboundRequestSaveDTO inboundRequestSaveDTO);

    void saveProducts(List<InboundProductSaveDTO> inboundProductSaveDTOList);

    void update(Long id);

    void updateProducts(List<InboundProductUpdateDTO> inboundProductUpdateDTOList);
    //void updateProducts(InboundProductUpdateDTO inboundProductUpdateDTO);

    void delete(Long id);

    void updateStatus(Map<String, Object> map);

    int getCount(InboundPageRequestDTO inboundPageRequestDTO);
}
