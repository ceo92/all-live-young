package allliveyoung.wms.mapper;

import allliveyoung.wms.domain.Shipment;
import allliveyoung.wms.domain.Status;
import allliveyoung.wms.domain.dummy.Stock;
import allliveyoung.wms.web.dto.PageRequestDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.query.Param;

public class ShipmentMapper {

  public List<Shipment> findAll(PageRequestDTO page, @Param("Status") Status status) {
    return null;
  }

  public Optional<Shipment> findById(Long id) {
    return null;
  }

  public void update(Shipment shipment) {

  }
}
