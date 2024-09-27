package allliveyoung.wms.web.dto;

import allliveyoung.wms.domain.Status;
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
  private Status status;
  @NotNull(message="필수 입력 값입니다.")
  private Long memberId;
  @NotNull(message="필수 입력 값입니다.")
  private Long stockId;
  @NotNull(message="필수 입력 값입니다.")
  @Positive(message = "양수만 가능합니다.")
  private int quantity;
  @NotNull(message="필수 입력 값입니다.")
  private String recipientName;
  @NotNull(message="필수 입력 값입니다.")
  private String recipientAddress;
  @NotNull(message="필수 입력 값입니다.")
  private String recipientContact;
  private Boolean isFault;
  private String rejectionNote;
  private LocalDateTime regDate;
  private LocalDateTime modDate;
  private String note;
}
