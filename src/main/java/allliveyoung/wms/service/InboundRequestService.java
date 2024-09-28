package allliveyoung.allliveinbound.service;

import allliveyoung.allliveinbound.domain.InboundRequest;
import allliveyoung.allliveinbound.domain.InboundRequestProduct;
import allliveyoung.allliveinbound.domain.Warehouse;
import allliveyoung.allliveinbound.web.dto.InboundRequestDTO;
import allliveyoung.allliveinbound.web.dto.InboundRequestSaveDTO;
import allliveyoung.allliveinbound.web.dto.InboundRequestUpdateDTO;

import java.util.List;

public interface InboundRequestService {
    List<InboundRequestDTO> findInbounds();

    InboundRequestDTO findInbound(Long id);

    Long saveInbound(InboundRequestSaveDTO inboundRequestSaveDTO, List<InboundRequestProduct> inboundRequestProducts);

    void updateInbound(InboundRequestUpdateDTO inboundRequestUpdateDTO, List<InboundRequestProduct> inboundRequestProducts);

    void deleteInbound(Long id);

    void updateInboundStatus(Long id, String status);
}
