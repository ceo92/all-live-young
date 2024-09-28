package allliveyoung.wms.service;

import allliveyoung.wms.web.dto.*;

import java.util.List;

public interface InboundRequestService {
    List<InboundRequestDTO> findInbounds();

    InboundRequestDTO findInbound(Long id);

    Long saveInbound(InboundRequestSaveDTO inboundRequestSaveDTO, List<InboundProductSaveDTO> inboundProductSaveDTOList);

    void updateInbound(InboundRequestUpdateDTO inboundRequestUpdateDTO, List<InboundProductUpdateDTO> inboundRequestProducts);

    void deleteInbound(Long id);

    void updateInboundStatus(Long id, String status);
}
