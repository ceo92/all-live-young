package allliveyoung.wms.mapper;

import allliveyoung.wms.domain.OutboundInspection;
import allliveyoung.wms.domain.Status;
import allliveyoung.wms.web.dto.PageRequestDTO;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OutboundInspectionMapper {

  public List<OutboundInspection> findAll(PageRequestDTO page, @Param("Status") Status status);

  public Optional<OutboundInspection> findById(Long id);

  public void update(OutboundInspection outboundInspection);
}
