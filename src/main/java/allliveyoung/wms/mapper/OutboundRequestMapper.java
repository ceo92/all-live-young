package allliveyoung.wms.mapper;

import allliveyoung.wms.domain.OutboundRequest;
import allliveyoung.wms.web.dto.OutboundRequestDTO;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OutboundRequestMapper {

  Long save(OutboundRequest outboundRequest);

  List<OutboundRequest> findAll(OutboundRequestDTO outboundRequestDTO);

  Optional<OutboundRequest> findById(Long id);

  void memberUpdate(OutboundRequest outboundRequest);

  void managerUpdate(OutboundRequest outboundRequest);

  void delete(Long id);
}
