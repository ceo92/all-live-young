package allliveyoung.wms.web.dto;

import allliveyoung.wms.domain.Status;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutboundRequestDTO {
  private Long id;
  private String code;
  private Status status;
  private String orderMemberId;
  private String stockNumber;
  private int quantity;
  private String recipientName;
  private String recipientAddress;
  private String recipientContact;
  private String note;
  private LocalDateTime regDate;
  private LocalDateTime modDate;
}
