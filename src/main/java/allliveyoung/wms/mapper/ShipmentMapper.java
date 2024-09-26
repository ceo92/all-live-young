package allliveyoung.wms.mapper;

import allliveyoung.wms.domain.Shipment;
import allliveyoung.wms.domain.Status;
import allliveyoung.wms.web.dto.PageRequestDTO;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

@Mapper
public interface ShipmentMapper {

  public List<Shipment> findAll(PageRequestDTO page, @Param("Status") Status status);

  public Optional<Shipment> findById(Long id);

  public void update(Shipment shipment);
}
