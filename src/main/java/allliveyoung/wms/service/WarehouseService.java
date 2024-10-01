package allliveyoung.wms.service;

import allliveyoung.wms.domain.Warehouse;
import allliveyoung.wms.mapper.WarehouseMapper;
import allliveyoung.wms.web.dto.WarehouseSaveDto;
import allliveyoung.wms.web.dto.WarehouseSearch;
import allliveyoung.wms.web.dto.WarehouseUpdateDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WarehouseService {
  private final WarehouseMapper warehouseMapper;


  @Transactional
  public Long saveWarehouse(WarehouseSaveDto warehouseSaveDto){
    Warehouse warehouse = Warehouse.builder().name(warehouseSaveDto.getName())
        .address(new Address(warehouseSaveDto.getRoadNameAddress()
            , warehouseSaveDto.getJibunAddress()
            , warehouseSaveDto.getDetailsAddress()
            , warehouseSaveDto.getZipcode())).build();
    warehouseMapper.save(warehouse);

    return warehouse.getId();
  }


  @Transactional
  public void updateWarehouse(Long id , WarehouseUpdateDto warehouseUpdateDto){
    Warehouse warehouse = findWarehouse(id);
    warehouse.changeWarehouse(warehouseUpdateDto.getName(),
        warehouseUpdateDto.getRoadNameAddress() ,
        warehouseUpdateDto.getJibunAddress() ,
        warehouseUpdateDto.getDetailsAddress(),
        warehouseUpdateDto.getZipcode());
    warehouseMapper.update(warehouse);
  }

  public Warehouse findWarehouse(Long id){
    return warehouseMapper.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 창고입니다."));
  }

  public List<Warehouse> findWarehouses(WarehouseSearch warehouseSearch){
    return warehouseMapper.findAll(warehouseSearch);
  }

}
