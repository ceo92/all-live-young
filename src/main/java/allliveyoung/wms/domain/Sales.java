package allliveyoung.wms.domain;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sales {
    private Long id;

    private Warehouse warehouse;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate salesDate;

    private String category;

    @Nullable
    private Member member;

    @NumberFormat(pattern = "#,###원")
    private Integer amount;

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime regDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime modDate;
}
