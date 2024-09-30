package allliveyoung.wms.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InboundRequestSaveDTO {
    private Long id;
    private Long memberId;
    private Long warehouseId;
    private List<allliveyoung.allliveinbound.web.dto.InboundProductSaveDTO> inboundProductSaveDTOList;
}
