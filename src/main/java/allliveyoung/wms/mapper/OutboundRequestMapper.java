package allliveyoung.wms.mapper;

import allliveyoung.wms.domain.OutboundRequest;
import allliveyoung.wms.domain.Status;
import allliveyoung.wms.domain.dummy.Member;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;

public interface OutboundRequestMapper {

  public Long save(OutboundRequest outboundRequest);

  public List<OutboundRequest> findAll(PageRequest pageRequest, @Param("status") Status status,
      @Param("id") Member member);

  public Optional<OutboundRequest> findById(Long id);

  public void update(OutboundRequest outboundRequest);

  public void delete(Long id);
}
