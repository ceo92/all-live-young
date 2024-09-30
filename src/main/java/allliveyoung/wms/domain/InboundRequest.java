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
    private String status;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private int isFault;
    private String rejectionNote;
}
