package allliveyoung.wms.service;

import allliveyoung.wms.domain.OutboundInspection;
import allliveyoung.wms.domain.Status;
import allliveyoung.wms.domain.dummy.Member;
import allliveyoung.wms.web.dto.OutboundInspectionDTO;
import allliveyoung.wms.web.dto.PageRequestDTO;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public class OutboundInspectionService {

  public List<OutboundInspection> findOutboundInspections(PageRequestDTO pageRequestDTO,
      @Param("Status") Status status) {
    return null;
  }
  
  public OutboundInspection findOutboundInspection(Long id) {
    return null;
  }
  
  public void updateOutboundInspection(OutboundInspectionDTO OutboundInspectionDTO) {
    
  }
}
