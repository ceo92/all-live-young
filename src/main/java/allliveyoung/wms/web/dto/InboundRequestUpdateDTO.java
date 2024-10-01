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
public class InboundRequestUpdateDTO {
    private Long id;
    private List<InboundProductUpdateDTO> inboundProductUpdateDTOList;
}
