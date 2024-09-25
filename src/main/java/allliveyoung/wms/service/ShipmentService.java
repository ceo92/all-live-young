package allliveyoung.wms.service;

import allliveyoung.wms.domain.Shipment;
import allliveyoung.wms.domain.Status;
import allliveyoung.wms.web.dto.ShipmentDTO;
import allliveyoung.wms.web.dto.PageRequestDTO;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public class ShipmentService {

  public List<Shipment> findShipments(PageRequestDTO pageRequestDTO,
      @Param("Status") Status status) {
    return null;
  }
  
  public Shipment findShipment(Long id) {
    return null;
  }
  
  public void updateShipment(ShipmentDTO ShipmentDTO) {
    
  }
}
