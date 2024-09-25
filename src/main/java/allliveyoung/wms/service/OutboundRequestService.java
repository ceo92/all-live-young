package allliveyoung.wms.service;

import allliveyoung.wms.domain.OutboundRequest;
import allliveyoung.wms.domain.Status;
import allliveyoung.wms.domain.dummy.Member;
import allliveyoung.wms.web.dto.OutboundRequestDTO;
import allliveyoung.wms.web.dto.PageRequestDTO;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public class OutboundRequestService {

  public Long saveOutboundRequest(OutboundRequestDTO outboundRequestDTO) {
    return null;
  }

  public List<OutboundRequest> findOutboundRequests(PageRequestDTO pageRequestDTO,
      @Param("Status") Status status, Member member) {
    return null;
  }

  public OutboundRequest findOneOutboundRequest(Long id) {
    return null;
  }

  public void updateOutboundRequest(OutboundRequestDTO outboundRequestDTO) {

  }

  public void deleteOutboundRequest(Long id) {

  }
}
