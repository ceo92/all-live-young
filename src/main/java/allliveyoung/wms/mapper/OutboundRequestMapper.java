package allliveyoung.wms.mapper;

import allliveyoung.wms.domain.OutboundRequest;
import allliveyoung.wms.web.dto.OutboundRequestDTO;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OutboundRequestMapper {

  Long save(OutboundRequestDTO outboundRequestDTO);

  List<OutboundRequest> findAll(OutboundRequestDTO outboundRequestDTO);

  Optional<OutboundRequest> findById(Long id);

  void memberUpdate(OutboundRequestDTO outboundRequestDTO);

  void managerUpdate(OutboundRequestDTO outboundRequestDTO);

  void delete(Long id);
}
