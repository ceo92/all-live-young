package allliveyoung.wms.service;

import allliveyoung.wms.domain.Member;
import allliveyoung.wms.domain.OutboundRequest;
import allliveyoung.wms.constant.RequestStatus;
import allliveyoung.wms.web.dto.OutboundRequestDTO;
import java.util.List;
import org.apache.coyote.Request;
import org.apache.ibatis.annotations.Param;

public interface OutboundRequestService {

  Long saveOutboundRequest(OutboundRequestDTO outboundRequestDTO);

  List<OutboundRequest> findOutboundRequests(@Param("status") RequestStatus status);

  OutboundRequest findOneOutboundRequest(Long id);

  void updateOutboundRequestByCompany(OutboundRequestDTO outboundRequestDTO);

  void updateOutboundRequestByManager(OutboundRequestDTO outboundRequestDTO);

  void deleteOutboundRequest(Long id);
}
