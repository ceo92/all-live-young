package allliveyoung.allliveinbound.domain;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private Long id;
    private String name;
    private String password;
    private String phoneNumber;
    private String email;
    private String roadNameAddress;
    private String detailsAddress;
    private String accountStatus;
    private String joinDate;
    private String lastLoginDate;
    private String roleType;
    private String businessNumber;
    private Long warehouseId;
    private int isAgree;
    private String agreeDate;
}
