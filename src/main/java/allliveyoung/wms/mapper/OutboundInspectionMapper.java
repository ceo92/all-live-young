package allliveyoung.wms.mapper;

import allliveyoung.wms.domain.OutboundInspection;
import allliveyoung.wms.domain.Status;
import allliveyoung.wms.web.dto.PageRequestDTO;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Param;

public class OutboundInspectionMapper {

  public List<OutboundInspection> findAll(PageRequestDTO page, @Param("Status") Status status) {
    return null;
  }

  public Optional<OutboundInspection> findById(Long id) {
    return null;
  }

  public void update(OutboundInspection outboundInspection) {}
}
