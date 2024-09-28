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
    private Company company;
    private enum storageMethod{}
    private enum designatedType{}
    private LocalDateTime licenseDate;
    private int licenseNum;
}
