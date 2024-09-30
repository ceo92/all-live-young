package allliveyoung.wms.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockUpdateDto {
  private String stockCode;
  private String productName;
  private String name;
  private String manufactureNumber;
  private Integer palletQuantity;
  private Integer boxQuantity;

}
