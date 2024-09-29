package allliveyoung.wms.web.dto;


import allliveyoung.wms.domain.InboundRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InboundProductDTO {
    private Long id;
    private int palletQuantity;
    private int boxQuantity;
    private String manufactureNum;
    private String expDate;
    private InboundRequest inboundRequest;
    private Product product;
    private Member member;
}
