package allliveyoung.wms.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Announcement {
    private Long announcementId;
    private Long managerId;
    private LocalDateTime writeDate;
    private String content;
    private Boolean highlight;
}
