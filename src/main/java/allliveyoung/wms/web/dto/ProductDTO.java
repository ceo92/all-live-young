package allliveyoung.allliveinbound.web.dto;

import allliveyoung.allliveinbound.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private Member member;
    private String name;
    private String storeTemperature;
    private String type;
    private String licenseDate;
    private String licenseNum;
}
