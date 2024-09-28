package allliveyoung.allliveinbound.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InboundProductUpdateDTO {
    private Long id;
    private String inboundRequestId;
    private int palletQuantity;
    private int boxQuantity;
    private String manufactureNum;
    private String expDate;
}
