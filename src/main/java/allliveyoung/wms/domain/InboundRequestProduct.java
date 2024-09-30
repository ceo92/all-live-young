

import allliveyoung.wms.domain.Product;
import lombok.*;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InboundRequestProduct {
    private Long id;
    private int palletQuantity;
    private int boxQuantity;
    private String manufactureNum;
    private LocalDateTime expDate;
    private allliveyoung.allliveinbound.domain.InboundRequest inboundRequest;
    private Product product;
}
