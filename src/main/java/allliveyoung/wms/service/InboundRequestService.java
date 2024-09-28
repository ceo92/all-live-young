package allliveyoung.allliveinbound.service;

import allliveyoung.allliveinbound.domain.InboundRequest;
import allliveyoung.allliveinbound.web.dto.InboundRequestUpdateDTO;

import java.util.List;

public interface InboundRequestService {
    List<InboundRequest> findInbounds();

    InboundRequest findInbound(Long id);

    void updateInbound(InboundRequestUpdateDTO inboundRequestUpdateDTO);

    void deleteInbound(Long id);

    void updateInboundStatus(Long id, String status);
}
