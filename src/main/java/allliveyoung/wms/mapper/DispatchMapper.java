package allliveyoung.wms.mapper;

import allliveyoung.wms.domain.Dispatch;
import allliveyoung.wms.domain.Status;
import allliveyoung.wms.web.dto.PageRequestDTO;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Param;

public class DispatchMapper {

  public List<Dispatch> findAll(PageRequestDTO pageRequestDTO, @Param("Status") Status status) {
    return null;
  }

  public Optional<Dispatch> findById(Long id) {
    return null;
  }

  public void update(Dispatch dispatch) {

  }
}
