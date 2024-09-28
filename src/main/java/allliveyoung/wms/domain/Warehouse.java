package allliveyoung.wms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Warehouse {
    private Long id;
    private String name;
    private String code;
    private Address warehouseAddress;
}
