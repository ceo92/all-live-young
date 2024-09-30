package allliveyoung.allliveinbound.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseDTO {

    private Long id;
    private String name;
    private String code;
    private String roadAddress;
    private String jibunAddress;
    private String zipCode;
    private String detailsAddress;
}
