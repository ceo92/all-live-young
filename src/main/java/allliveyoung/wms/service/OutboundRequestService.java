package allliveyoung.wms.service;

import allliveyoung.wms.domain.Member;
import allliveyoung.wms.domain.OutboundRequest;
import allliveyoung.wms.domain.Status;
import allliveyoung.wms.web.dto.OutboundRequestDTO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OutboundRequestService {

  Long saveOutboundRequest(OutboundRequestDTO outboundRequestDTO);

  List<OutboundRequest> findOutboundRequests(@Param("status") Status status);

  OutboundRequest findOneOutboundRequest(Long id);

  void updateOutboundRequestByCompany(OutboundRequestDTO outboundRequestDTO);

  void updateOutboundRequestByManager(OutboundRequestDTO outboundRequestDTO);

  void deleteOutboundRequest(Long id);
}
