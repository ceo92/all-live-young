package allliveyoung.wms.domain;

import allliveyoung.wms.domain.dummy.Address;
import allliveyoung.wms.domain.dummy.Member;
import allliveyoung.wms.domain.dummy.Stock;
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
public class OutboundRequest {
  private Long id;
  private String code;
  private Status status;
  private Member member;
  private Stock stock;
  private int quantity;
  private String recipientName;
  private Address address;
  private String recipientContact;
  private String note;
}
