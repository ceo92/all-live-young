package allliveyoung.wms.mapper;

import allliveyoung.wms.domain.OutboundRequest;
import allliveyoung.wms.domain.Status;
import allliveyoung.wms.domain.Member;
import allliveyoung.wms.web.dto.OutboundRequestDTO;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OutboundRequestMapper {

  Long save(OutboundRequest outboundRequest);

  List<OutboundRequest> findAll(OutboundRequestDTO outboundRequestDTO, @Param("status") Status status,
      @Param("id") Member member);

  Optional<OutboundRequest> findById(Long id);

  void update(OutboundRequest outboundRequest);

  void delete(Long id);
}
