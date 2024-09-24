package allliveyoung.wms.mapper;

import allliveyoung.wms.domain.OutboundRequest;
import allliveyoung.wms.domain.Status;
import allliveyoung.wms.domain.dummy.Member;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;

public class OutboundRequestMapper {

  public Long save(OutboundRequest outboundRequest) {
    return null;
  }

  public List<OutboundRequest> findAll(PageRequest pageRequest, @Param("Status") Status status,
      @Param("id") Member member) {

    return null;
  }

  public Optional<OutboundRequest> findById(Long id) {
    return null;
  }

  public void update(OutboundRequest outboundRequest) {

  }

  public void delete(Long id) {}
}
