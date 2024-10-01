package allliveyoung.wms.domain;

import allliveyoung.wms.domain.Member;
import allliveyoung.wms.domain.Warehouse;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy. MM. dd. a hh:mm")
    private LocalDateTime regDate;
    @DateTimeFormat(pattern = "yyyy. MM. dd. a hh:mm")
    private LocalDateTime modDate;
    private int isFault;
    private String rejectionNote;
}
