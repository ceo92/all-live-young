package allliveyoung.wms.mapper;

import allliveyoung.wms.domain.PasswordResetToken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PasswordResetTokenMapper {
    void insertToken(PasswordResetToken token);
    PasswordResetToken selectTokenByToken(@Param("token") String token);
    void deleteTokenByMemberId(@Param("memberId") Long memberId);
}
