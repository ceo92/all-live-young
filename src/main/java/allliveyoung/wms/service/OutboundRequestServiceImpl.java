package allliveyoung.wms.service;

import allliveyoung.wms.domain.Member;
import allliveyoung.wms.domain.OutboundRequest;
import allliveyoung.wms.domain.Status;
import allliveyoung.wms.mapper.OutboundRequestMapper;
import allliveyoung.wms.web.dto.OutboundRequestDTO;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OutboundRequestServiceImpl implements OutboundRequestService {

  private final OutboundRequestMapper outboundRequestMapper;
  private final ModelMapper modelMapper;

  @Override
  public Long saveOutboundRequest(OutboundRequestDTO outboundRequestDTO) {
    OutboundRequest outboundRequest = modelMapper.map(outboundRequestDTO, OutboundRequest.class);
    outboundRequestMapper.save(outboundRequest);
    return outboundRequest.getId();
  }

  @Override
  public List<OutboundRequest> findOutboundRequests(OutboundRequestDTO outboundRequestDTO,
      Status status, Member member) {
    List<OutboundRequest> list = outboundRequestMapper.findAll(outboundRequestDTO, status, member);
    return list;
  }

  @Override
  public OutboundRequest findOneOutboundRequest(Long id) {
    Optional<OutboundRequest> outboundRequest= outboundRequestMapper.findById(id);
    OutboundRequest result = outboundRequest.orElseThrow();
    return result;
  }

  @Override
  public void updateOutboundRequest(OutboundRequestDTO outboundRequestDTO) {
    outboundRequestMapper.update(modelMapper.map(outboundRequestDTO, OutboundRequest.class));
  }

  @Override
  public void deleteOutboundRequest(Long id) {
    outboundRequestMapper.delete(id);
  }
}
