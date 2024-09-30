package allliveyoung.wms.mapper;

import allliveyoung.wms.domain.Warehouse;
import allliveyoung.wms.web.dto.WarehouseSearch;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WarehouseMapper {

  void save(Warehouse warehouse);

  void update(Warehouse warehouse);

  Optional<Warehouse> findById(Long id);

  void delete(Long id);

  List<Warehouse> findAll(WarehouseSearch warehouseSearch);

}
