package allliveyoung.wms.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountRequestDTO {
    private Integer year;
    private Integer month;
    private Integer count;
}
