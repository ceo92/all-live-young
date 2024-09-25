package allliveyoung.wms.service;

import allliveyoung.wms.domain.Dispatch;
import allliveyoung.wms.domain.Status;
import allliveyoung.wms.web.dto.DispatchDTO;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;


public interface DispatchService{

  public List<Dispatch> findDispatches(@Param("Status") Status status);

  
  public Dispatch findDispatch(Long id);
  
  public void updateDispatch(DispatchDTO DispatchDTO);
}
