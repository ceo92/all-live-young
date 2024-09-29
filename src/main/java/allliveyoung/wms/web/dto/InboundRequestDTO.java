package allliveyoung.allliveinbound.web.dto;

import allliveyoung.allliveinbound.domain.Member;
import allliveyoung.allliveinbound.domain.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InboundRequestDTO {
    private Long id;
    private String code;
    private Member member;
    private Warehouse warehouse;
    private String status;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private String rejectionNote;
}
