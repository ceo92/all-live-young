package package allliveyoung.wms.mapper;

import allliveyoung.wms.domain.InboundRequest;
import allliveyoung.wms.web.dto.InboundProductSaveDTO;
import allliveyoung.wms.web.dto.InboundProductUpdateDTO;
import allliveyoung.wms.web.dto.InboundRequestSaveDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.*;

@Mapper
public interface InboundRequestMapper {
    List<InboundRequest> findAll();

    Optional<InboundRequest> findById(Long id);

    void save(InboundRequestSaveDTO inboundRequestSaveDTO);

    void saveProducts(List<InboundProductSaveDTO> inboundProductSaveDTOList);

    void update(Long id);

    void updateProducts(List<InboundProductUpdateDTO> inboundProductUpdateDTOList);

    void delete(Long id);

    void updateStatus(Map<String, Object> map);
}
