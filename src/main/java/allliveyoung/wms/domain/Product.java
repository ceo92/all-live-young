package allliveyoung.wms.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private Long memberId;  //호환성을 위해 우선 보류, 체크 후 클래스 객체 필드
    private String storeTemperature;
    private String type;
    @DateTimeFormat(pattern = "yyyy. MM. dd. a hh:mm")
    private LocalDateTime licenseDate;
    private int licenseNum;
}
