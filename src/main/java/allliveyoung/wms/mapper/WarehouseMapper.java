package allliveyoung.wms.mapper;

import allliveyoung.wms.domain.Warehouse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WarehouseMapper {

    void insertWarehouse(Warehouse warehouse);

    void updateWarehouse(Warehouse warehouse);

    void deleteWarehouse(@Param("warehouseId") Long warehouseId);

    Warehouse selectWarehouseById(@Param("warehouseId") Long warehouseId);

    List<Warehouse> selectAllWarehouses();
}
