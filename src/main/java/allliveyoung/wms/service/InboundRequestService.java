package allliveyoung.wms.service;



import allliveyoung.wms.web.dto.*;

import java.util.List;

public interface InboundRequestService {
    InboundPageResponseDTO<InboundRequestDTO> findInbounds(InboundPageRequestDTO inboundPageRequestDTO);

    List<InboundProductDTO> findInbound(Long id);

    Long saveInbound(InboundRequestSaveDTO inboundRequestSaveDTO);

    void updateInbound(InboundRequestUpdateDTO inboundRequestUpdateDTO, List<InboundProductUpdateDTO> inboundRequestProducts);

    void deleteInbound(Long id);

    void updateInboundStatus(Long id, String status);

    List<WarehouseDTO> getWarehouseList();

    List<ProductDTO> getMatchedProductList(Long id);
}
