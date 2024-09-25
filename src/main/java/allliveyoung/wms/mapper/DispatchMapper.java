package allliveyoung.wms.mapper;

import allliveyoung.wms.domain.Dispatch;
import allliveyoung.wms.domain.Status;
import allliveyoung.wms.web.dto.PageRequestDTO;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Param;

public interface DispatchMapper {

  public List<Dispatch> findAll(PageRequestDTO pageRequestDTO, @Param("Status") Status status);

  public Optional<Dispatch> findById(Long id);

  public void update(Dispatch dispatch);
}
