package allliveyoung.wms.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Warehouse {
    private Long warehouseId;
    private Long warehouseManagerId;
    private String warehouseName;
    private String warehouseCode;
    private String warehouseRoadAddress;
    private String warehouseJibunAddress;
    private String warehouseZipcode;
    private String warehouseDetailsAddress;
}
