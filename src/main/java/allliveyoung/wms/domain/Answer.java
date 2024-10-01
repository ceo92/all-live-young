package allliveyoung.wms.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Answer {
    private Long answerId;
    private Long inquiryId;
    private Long memberId;
    private String answerContent;
    private LocalDateTime answerWriteTime;
    private String memberName; // Added
}
