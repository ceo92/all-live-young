package allliveyoung.wms.service;

import allliveyoung.wms.domain.Member;
import allliveyoung.wms.domain.OutboundRequest;
import allliveyoung.wms.domain.Status;
import allliveyoung.wms.web.dto.OutboundRequestDTO;
import java.util.List;

public interface OutboundRequestService {

  public Long saveOutboundRequest(OutboundRequestDTO outboundRequestDTO);

  public List<OutboundRequest> findOutboundRequests(OutboundRequestDTO outboundRequestDTO, Status status, Member member);

  public OutboundRequest findOneOutboundRequest(Long id);

  public void updateOutboundRequest(OutboundRequestDTO outboundRequestDTO);

  public void deleteOutboundRequest(Long id);
}
