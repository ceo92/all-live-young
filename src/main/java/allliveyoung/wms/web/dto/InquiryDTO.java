package allliveyoung.wms.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InquiryDTO {

    private Long inquiryId;

    private Long memberId;

    @NotBlank
    private String inquiryTitle;

    @NotBlank
    private String inquiryContent;
}
