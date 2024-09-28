package allliveyoung.allliveinbound.web.dto;

import allliveyoung.allliveinbound.domain.InboundRequest;
import allliveyoung.allliveinbound.domain.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InboundInspectionDTO {
    private InboundRequest inboundRequest;
    private Company company;
    private Boolean isFault;
    private String rejectNote;
    private String code;
}
