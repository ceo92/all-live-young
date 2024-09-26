package allliveyoung.wms.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Outbound {
  private Long id;
  private String code;
  private OutboundRequest outboundRequest;
  private OutboundInspection outboundInspection;
  private Member member;
  private LocalDateTime outboundTime;
}
