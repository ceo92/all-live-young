package allliveyoung.allliveinbound.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private Long memberId;
    private String storeTemperature;
    private String type;
    private LocalDateTime licenseDate;
    private int licenseNum;
}
