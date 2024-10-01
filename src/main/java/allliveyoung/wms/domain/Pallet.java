package allliveyoung.wms.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Pallet {

  private Long id;
  private InboundRequestProduct inboundRequestProduct;
  private Section section;

}



