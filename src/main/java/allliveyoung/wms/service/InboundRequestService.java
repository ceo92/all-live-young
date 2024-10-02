package allliveyoung.allliveinbound.service;

import allliveyoung.allliveinbound.domain.InboundRequest;
import allliveyoung.allliveinbound.domain.InboundRequestProduct;
import allliveyoung.allliveinbound.domain.Member;
import allliveyoung.allliveinbound.domain.Warehouse;
import allliveyoung.allliveinbound.web.dto.*;

import java.util.List;

public interface InboundRequestService {
    InboundPageResponseDTO<InboundRequestDTO> findInbounds(InboundPageRequestDTO inboundPageRequestDTO, Member member);

    List<InboundProductDTO> findInbound(Long id);

    Long saveInbound(InboundRequestSaveDTO inboundRequestSaveDTO);

    void updateInbound(Long id, InboundRequestUpdateDTO inboundRequestUpdateDTO);

    void deleteInbound(Long id);

    void updateInboundStatus(InboundStatusUpdateDTO inboundStatusUpdateDTO);

    List<WarehouseDTO> getWarehouseList();

    List<ProductDTO> getMatchedProductList(Long id);

    Integer getCount(String status);

    List<CountRequestDTO> findCountInbound(Integer year);

    List<CountStockDTO> findCountStock(Integer year);
}
