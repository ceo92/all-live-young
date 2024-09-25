package allliveyoung.wms.service;

import allliveyoung.wms.domain.OutboundRequest;
import allliveyoung.wms.domain.Status;
import allliveyoung.wms.domain.dummy.Member;
import allliveyoung.wms.web.dto.OutboundRequestDTO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OutboundRequestService {

  public Long saveOutboundRequest(OutboundRequestDTO outboundRequestDTO);

  public List<OutboundRequest> findOutboundRequests(@Param("Status") Status status, Member member);

  public OutboundRequest findOneOutboundRequest(Long id);

  public void updateOutboundRequest(OutboundRequestDTO outboundRequestDTO);

  public void deleteOutboundRequest(Long id);
}
