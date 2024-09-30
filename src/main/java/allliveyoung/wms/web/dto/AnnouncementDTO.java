package allliveyoung.wms.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnnouncementDTO {

    private Long announcementId;

    private Long managerId;

    private String title;

    private String content;

    private Boolean highlight;
}
