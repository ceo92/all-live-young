package allliveyoung.wms.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerDTO {

    private Long answerId;

    private Long inquiryId;

    private Long memberId;

    @NotBlank
    private String answerContent;
}
