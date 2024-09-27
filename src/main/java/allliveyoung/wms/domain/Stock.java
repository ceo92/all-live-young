package allliveyoung.wms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Stock {
  private Long id;
  private String code;
  private Integer quantity;

  public Stock(Long id) {
    this.id = id;
  }
}
