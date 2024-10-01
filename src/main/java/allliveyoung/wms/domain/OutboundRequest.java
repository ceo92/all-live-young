package allliveyoung.wms.domain;

import allliveyoung.wms.constant.RequestStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OutboundRequest {
  private Long id;
  private String code;
  private RequestStatus status;
  private Member member;
  private Stock stock;
  private Integer quantity;
  private String recipientName;
  private String recipientAddress;
  private String recipientContact;
  private Boolean isFault;
  private String rejectionNote;
  private LocalDateTime regDate;
  private LocalDateTime modDate;
  private String note;
}
