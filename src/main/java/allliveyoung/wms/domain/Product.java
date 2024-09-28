package allliveyoung.wms.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
  private Long id;
  private String productName;
  private String storeTemperature; //StoreTemperature
  private String productType; //ProductType

  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private LocalDateTime productLicenseDate;
  private Integer productLicenseNum;
  private Member member;

}
