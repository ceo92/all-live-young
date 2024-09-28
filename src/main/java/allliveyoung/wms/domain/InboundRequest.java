package allliveyoung.allliveinbound.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InboundRequest {
    private Long id;
    private String code;
    private List<InboundRequestProduct> inboundRequestProducts;
    private Member member;
    private Warehouse warehouse;
    private enum status {
    }
}
