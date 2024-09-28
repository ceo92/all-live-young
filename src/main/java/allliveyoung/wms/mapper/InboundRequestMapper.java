package allliveyoung.allliveinbound.mapper;

import allliveyoung.allliveinbound.domain.InboundRequest;
import allliveyoung.allliveinbound.domain.InboundRequestProduct;
import allliveyoung.allliveinbound.domain.Warehouse;
import allliveyoung.allliveinbound.web.dto.InboundRequestDTO;
import allliveyoung.allliveinbound.web.dto.InboundRequestSaveDTO;
import allliveyoung.allliveinbound.web.dto.InboundRequestUpdateDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface InboundRequestMapper {
    List<InboundRequest> findAll();

    Optional<InboundRequest> findById(Long id);

    Long save(InboundRequestSaveDTO inboundRequestSaveDTO, List<InboundRequestProduct> inboundRequestProducts);

    void update(InboundRequestUpdateDTO inboundRequestUpdateDTO, List<InboundRequestProduct> inboundRequestProducts);

    void delete(Long id);

    void updateStatus(Long id, String status);
}
