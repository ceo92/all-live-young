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
  private String roadNameAddress;
  private String detailsAddress;
  private AccountStatus accountStatus;
  private LocalDateTime joinDate;
  private LocalDateTime lastLoginDate;
  private RoleType roleType;
  private String businessNumber;
  private Long warehouseId;
  private Boolean isAgree;
  private LocalDateTime agreeDate;
}