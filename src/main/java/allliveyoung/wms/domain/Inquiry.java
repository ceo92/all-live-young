package allliveyoung.wms.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inquiry {
    private Long inquiryId;
    private Long memberId;
    private String inquiryTitle;
    private String inquiryContent;
    private LocalDateTime writeTime;
    private Boolean answered;
}
