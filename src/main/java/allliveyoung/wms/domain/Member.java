package allliveyoung.wms.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    private Long memberId;
    private String name;
    private String password;
    private String phoneNumber;
    private String email;
    private Address address;
    private AccountStatus accountStatus;
    private LocalDateTime joinDate;
    private LocalDateTime lastLoginDate;
    private RoleType roleType;
    private String businessNumber;
    private Warehouse warehouse;
    private Boolean isAgree;
    private LocalDateTime agreeDate;
}