package allliveyoung.allliveinbound.service;

import allliveyoung.allliveinbound.domain.InboundRequest;
import allliveyoung.allliveinbound.web.dto.InboundRequestDTO;
import allliveyoung.allliveinbound.web.dto.InboundRequestSaveDTO;
import allliveyoung.allliveinbound.web.dto.InboundRequestUpdateDTO;

import java.util.List;

public interface InboundRequestService {
    List<InboundRequestDTO> findInbounds();

    InboundRequestDTO findInbound(Long id);

    Long saveInbound(InboundRequestSaveDTO inboundRequestSaveDTO);

    void updateInbound(InboundRequestUpdateDTO inboundRequestUpdateDTO);

    void deleteInbound(Long id);

    void updateInboundStatus(Long id, String status);
}
