package allliveyoung.wms.service;

import allliveyoung.wms.domain.Member;
import allliveyoung.wms.domain.OutboundRequest;
import allliveyoung.wms.domain.Status;
import allliveyoung.wms.web.dto.OutboundRequestDTO;
import java.util.List;

public interface OutboundRequestService {

  Long saveOutboundRequest(OutboundRequestDTO outboundRequestDTO);

  List<OutboundRequest> findOutboundRequests(OutboundRequestDTO outboundRequestDTO);

  OutboundRequest findOneOutboundRequest(Long id);

  void updateOutboundRequestByCompany(OutboundRequestDTO outboundRequestDTO);
  void updateOutboundRequestByManager(OutboundRequestDTO outboundRequestDTO);

  void deleteOutboundRequest(Long id);
}
