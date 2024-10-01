package allliveyoung.wms.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Section {
  private Long id;
  private String storeTemperature; //StoreTemperature
  private String productType; //ProductType
  private String sectionCode;

  private Warehouse warehouse;
  @NumberFormat(pattern = "#,###")
  private Integer sectionCapacity;

  //private String blockNumber , 이건 인덱스로 처리할거라 정의 ㄴㄴ

}
