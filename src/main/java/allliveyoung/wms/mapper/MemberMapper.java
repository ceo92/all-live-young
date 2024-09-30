package allliveyoung.wms.mapper;

import allliveyoung.wms.domain.AccountStatus;
import allliveyoung.wms.domain.Member;
import allliveyoung.wms.domain.RoleType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    void insertMember(Member member);

    void updateMember(Member member);

    void deleteMember(Long memberId);

    Member selectMemberById(Long memberId);

    Member selectMemberByEmail(String email);

    Member selectMemberByNameAndPhone(@Param("name") String name, @Param("phoneNumber") String phoneNumber);

    Member selectMemberByNamePhoneAndBusinessNumber(@Param("name") String name,
                                                    @Param("phoneNumber") String phoneNumber,
                                                    @Param("businessNumber") String businessNumber);

    List<Member> selectMembersByCriteria(@Param("roleType") RoleType roleType,
                                         @Param("accountStatus") AccountStatus accountStatus,
                                         @Param("keyword") String keyword);


    Optional<Member> findByEmail(@Param("username") String username);
}
