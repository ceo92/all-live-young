package allliveyoung.allliveinbound.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InboundRequest {
    private Long id;
    private String code;
    private Member member;
    private Warehouse warehouse;
    private enum status {
    }
    private String note;
}
