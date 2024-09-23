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
public class OutboundInspectionDTO {
  private Long id;
  private String code;
  private String outboundRequestId;
  private Boolean isFault;
  private LocalDateTime inspectionTime;
  private String memberId;
  private String note;
}
