package allliveyoung.wms.mapper;


import allliveyoung.wms.domain.InboundRequest;
import allliveyoung.wms.domain.InboundRequestProduct;
import allliveyoung.wms.web.dto.InboundPageRequestDTO;
import allliveyoung.wms.web.dto.InboundProductSaveDTO;
import allliveyoung.wms.web.dto.InboundProductUpdateDTO;
import allliveyoung.wms.web.dto.InboundRequestSaveDTO;
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
