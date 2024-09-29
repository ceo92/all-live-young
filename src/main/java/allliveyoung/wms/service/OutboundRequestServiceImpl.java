package allliveyoung.wms.service;

import allliveyoung.wms.domain.OutboundRequest;
import allliveyoung.wms.mapper.OutboundRequestMapper;
import allliveyoung.wms.web.dto.OutboundRequestDTO;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OutboundRequestServiceImpl implements OutboundRequestService {

  private final OutboundRequestMapper outboundRequestMapper;
  private final ModelMapper modelMapper;

  @Override
  public Long saveOutboundRequest(OutboundRequestDTO outboundRequestDTO) {
    Long id = outboundRequestMapper.save(outboundRequestDTO);
    return id;
  }

  @Override
  public List<OutboundRequest> findOutboundRequests(OutboundRequestDTO outboundRequestDTO) {
    List<OutboundRequest> list = outboundRequestMapper.findAll(outboundRequestDTO);
    return list;
  }

  @Override
  public OutboundRequest findOneOutboundRequest(Long id) {
    Optional<OutboundRequest> outboundRequest= outboundRequestMapper.findById(id);
    OutboundRequest result = outboundRequest.orElseThrow();
    return result;
  }

  @Override
  public void updateOutboundRequestByCompany(OutboundRequestDTO outboundRequestDTO) {
    outboundRequestMapper.memberUpdate(outboundRequestDTO);
  }

  @Override
  public void updateOutboundRequestByManager(OutboundRequestDTO outboundRequestDTO) {
    outboundRequestMapper.managerUpdate(outboundRequestDTO);
  }

  @Override
  public void deleteOutboundRequest(Long id) {
    outboundRequestMapper.delete(id);
  }
}
