package allliveyoung.wms.mapper;

import allliveyoung.wms.domain.OutboundRequest;
import allliveyoung.wms.domain.Status;
import allliveyoung.wms.web.dto.OutboundRequestDTO;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OutboundRequestMapper {

  Long save(OutboundRequestDTO outboundRequestDTO);

  List<OutboundRequest> findAll(@Param("status") Status status);

  Optional<OutboundRequest> findById(Long id);

  void memberUpdate(OutboundRequestDTO outboundRequestDTO);

  void managerUpdate(OutboundRequestDTO outboundRequestDTO);

  void findMember(Long id);

  void findStock(Long id);

  void delete(Long id);
}
