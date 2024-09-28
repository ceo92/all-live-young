package allliveyoung.allliveinbound.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InboundInspection {
    private Long id;
    private Company company;
    private Boolean isFault;
    private String rejectionNote;
    private String code;
    private LocalDateTime regDate;
}
