package allliveyoung.allliveinbound.web.dto;

import allliveyoung.allliveinbound.domain.InboundRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InboundInspectionUpdateDTO {
    private InboundRequest inboundRequest;
    private Boolean isFault;
    private String rejectionNote;
}
