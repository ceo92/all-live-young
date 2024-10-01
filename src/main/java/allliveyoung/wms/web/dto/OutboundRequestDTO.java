package allliveyoung.wms.web.dto;

import allliveyoung.wms.constant.RequestStatus;
import allliveyoung.wms.domain.Member;
import allliveyoung.wms.constant.RequestStatus;
import allliveyoung.wms.domain.Stock;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
  private RequestStatus status;
  private Member member;
  private Stock stock;
  private int quantity;
  private String recipientName;
  private String recipientAddress;
  private String recipientContact;
  private Boolean isFault;
  private String rejectionNote;
  private LocalDateTime regDate;
  private LocalDateTime modDate;
  private String note;
}
