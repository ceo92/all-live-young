package allliveyoung.allliveinbound.web.dto;

import allliveyoung.allliveinbound.domain.Member;
import allliveyoung.allliveinbound.domain.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InboundRequestSaveDTO {
    private Long id;
    private String code;
    private Long memberId;
    private Long warehouseId;
}
