package allliveyoung.wms.web.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InboundProductSaveDTO {
    private Long id;
    private int palletQuantity;
    private int boxQuantity;
    private String manufactureNum;
    private String expDate;
    private Long inboundRequestId;
    private Long productId;
}
