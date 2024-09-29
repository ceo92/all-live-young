package allliveyoung.allliveinbound.service;

import allliveyoung.allliveinbound.domain.InboundRequest;
import allliveyoung.allliveinbound.domain.InboundRequestProduct;
import allliveyoung.allliveinbound.domain.Warehouse;
import allliveyoung.allliveinbound.web.dto.*;

import java.util.List;

public interface InboundRequestService {
    List<InboundRequestDTO> findInbounds();

    List<InboundProductDTO> findInbound(Long id);

    Long saveInbound(InboundRequestSaveDTO inboundRequestSaveDTO, List<InboundProductSaveDTO> inboundProductSaveDTOList);

    void updateInbound(InboundRequestUpdateDTO inboundRequestUpdateDTO, List<InboundProductUpdateDTO> inboundRequestProducts);

    void deleteInbound(Long id);

    void updateInboundStatus(Long id, String status);
}
