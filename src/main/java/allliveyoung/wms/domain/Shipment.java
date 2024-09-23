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
public class Shipment {
  private Long id;
  private Long waybillNumber;
  private Outbound outbound;
  private Status status;
  private LocalDateTime departureDate;
  private LocalDateTime arrivalDate;
  private String note;
}
