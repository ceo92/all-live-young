package allliveyoung.wms.service;

import allliveyoung.wms.web.dto.*;

import java.util.List;

public interface InboundRequestService {
    InboundPageResponseDTO<InboundRequestDTO> findInbounds(InboundPageRequestDTO inboundPageRequestDTO);

    List<InboundProductDTO> findInbound(Long id);

    Long saveInbound(InboundRequestSaveDTO inboundRequestSaveDTO);

    void updateInbound(Long id, InboundRequestUpdateDTO inboundRequestUpdateDTO);

    void deleteInbound(Long id);

    void updateInboundStatus(Long id, String status);

    List<WarehouseDTO> getWarehouseList();

    List<ProductDTO> getMatchedProductList(Long id);

    Integer getCount(String status);

    List<CountRequestDTO> findCountInbound(Integer year);

    List<CountStockDTO> findCountStock(Integer year);
}
