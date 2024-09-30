package allliveyoung.wms.web.dto;

import allliveyoung.wms.domain.AccountStatus;
import allliveyoung.wms.domain.RoleType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberSearchCriteriaDTO {

    private RoleType roleType;

    private AccountStatus accountStatus;

    private String keyword;
}
