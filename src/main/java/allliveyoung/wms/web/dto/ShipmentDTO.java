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
public class ShipmentDTO {
  private Long id;
  private Long waybillNumber;
  private String outboundId;
  private Status deliveryStatus;
  private LocalDateTime regDate;
  private LocalDateTime departureDate;
  private LocalDateTime arrivalDate;
  private String note;
}
