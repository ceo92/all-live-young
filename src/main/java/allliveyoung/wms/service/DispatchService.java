package allliveyoung.wms.service;

import allliveyoung.wms.domain.Dispatch;
import allliveyoung.wms.domain.Status;
import allliveyoung.wms.web.dto.DispatchDTO;
import allliveyoung.wms.web.dto.PageRequestDTO;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public class DispatchService {

  public List<Dispatch> findDispatches(PageRequestDTO pageRequestDTO,
      @Param("Status") Status status) {
    return null;
  }
  
  public Dispatch findDispatch(Long id) {
    return null;
  }
  
  public void updateDispatch(DispatchDTO DispatchDTO) {
    
  }
}
