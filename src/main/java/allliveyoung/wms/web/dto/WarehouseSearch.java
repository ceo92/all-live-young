package allliveyoung.wms.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class WarehouseSearch {
  private String warehouseName;
  private String warehouseRoadAddress;
}
