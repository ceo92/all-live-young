package allliveyoung.allliveinbound.web.dto;

import allliveyoung.wms.domain.Member;
import allliveyoung.wms.domain.Warehouse;
import lombok.*;

import java.time.LocalDateTime;

@Getter
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
