package allliveyoung.wms.web.dto;

import allliveyoung.wms.domain.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberUpdateDTO {

    @NotNull
    private Long memberId;



    @NotBlank
    private String phoneNumber;

    private String roadNameAddress;
    private String detailsAddress;

    private String businessNumber;

    private Long warehouseId;

    public MemberUpdateDTO(Member member) {
        this.memberId = member.getMemberId();
        this.phoneNumber = member.getPhoneNumber();
        this.roadNameAddress = member.getRoadNameAddress();
        this.detailsAddress = member.getDetailsAddress();
        this.businessNumber = member.getBusinessNumber();
        this.warehouseId = member.getWarehouseId();
    }
}
