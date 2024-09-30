package allliveyoung.allliveinbound.web.dto;

import allliveyoung.allliveinbound.domain.Member;
import allliveyoung.allliveinbound.domain.Warehouse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InboundRequestSaveDTO {
    private Long id;
    private Long memberId;
    private Long warehouseId;
    /*private List<String> palletQuantity = new ArrayList<>();
    private List<String> boxQuantity = new ArrayList<>();
    private List<String> manufactureNum = new ArrayList<>();
    private List<LocalDateTime> expDate = new ArrayList<>();*/
    private List<InboundProductSaveDTO> inboundProductSaveDTOList;
}
