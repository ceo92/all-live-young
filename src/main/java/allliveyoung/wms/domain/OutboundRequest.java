package allliveyoung.wms.domain;

import java.time.LocalDateTime;
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
  private Integer quantity;
  private String recipientName;
  private Address address;
  private String recipientContact;
  private Boolean isFault;
  private String rejectionNote;
  private LocalDateTime regDate;
  private LocalDateTime modDate;
  private String note;
}
