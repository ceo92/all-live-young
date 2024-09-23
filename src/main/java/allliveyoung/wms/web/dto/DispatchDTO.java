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
public class DispatchDTO {
  private Long id;
  private String code;
  private Status status;
  private LocalDateTime regDate;
  private LocalDateTime modDate;
  String note;
}
