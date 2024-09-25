package allliveyoung.wms.service;

import allliveyoung.wms.domain.OutboundRequest;
import allliveyoung.wms.domain.Status;
import allliveyoung.wms.domain.dummy.Member;
import allliveyoung.wms.mapper.OutboundRequestMapper;
import allliveyoung.wms.web.dto.OutboundRequestDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class OutboundRequestServiceImpl implements OutboundRequestService {

  private final OutboundRequestMapper outboundRequestMapper;
  private final ModelMapper modelMapper;

  @Override
  public Long saveOutboundRequest(OutboundRequestDTO outboundRequestDTO) {
    log.info("saveOutboundRequest");
    OutboundRequest outboundRequest = modelMapper.map(outboundRequestDTO, OutboundRequest.class);
    outboundRequestMapper.save(outboundRequest);
    return 0;
  }

  @Override
  public List<OutboundRequest> findOutboundRequests(Status status, Member member) {
    return List.of();
  }

  @Override
  public OutboundRequest findOneOutboundRequest(Long id) {
    return null;
  }

  @Override
  public void updateOutboundRequest(OutboundRequestDTO outboundRequestDTO) {

  }

  @Override
  public void deleteOutboundRequest(Long id) {

  }
}
