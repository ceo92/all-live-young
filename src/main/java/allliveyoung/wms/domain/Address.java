package allliveyoung.wms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    private String roadNameAddress;
    private String jibunAddress;
    private String zipCode;
    private String detailsAddress;
}
