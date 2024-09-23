package allliveyoung.wms.web.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutboundDTO {
  private Long id;
  private String code;
  private String outboundRequestId;
  private String dispatchId;
  private String memberId;
  private LocalDateTime outboundDate;
}
