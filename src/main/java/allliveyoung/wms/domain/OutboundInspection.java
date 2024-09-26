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
public class OutboundInspection {
  private Long id;
  private String code;
  private OutboundRequest outboundRequest;
  private Boolean isFault;
  private LocalDateTime inspectionTime;
  private Member member;
  private String note;
}
