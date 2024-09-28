package allliveyoung.wms.service;

import allliveyoung.wms.domain.AccountStatus;
import allliveyoung.wms.domain.Member;
import allliveyoung.wms.web.dto.*;

import java.util.List;

public interface MemberService {

    // 회원가입
    void registerMember(MemberSaveDTO memberSaveDTO);

    // 로그인
    Member login(LoginDTO loginDTO);

    // 아이디 찾기
    String findEmailByNameAndPhone(String name, String phoneNumber);

    // 비밀번호 재설정 요청 (이메일 발송)
    void requestPasswordReset(PasswordResetRequestDTO passwordResetRequestDTO) throws Exception;

    // 비밀번호 재설정 처리
    void resetPassword(PasswordResetDTO passwordResetDTO);

    // 마이페이지 조회
    Member getMemberDetails(Long memberId);

    // 마이페이지 수정
    void updateMember(MemberUpdateDTO memberUpdateDTO);

    // 회원 탈퇴 신청
    void requestWithdrawal(Long memberId);

    // 회원 목록 조회 (관리자용)
    List<Member> getMembersByCriteria(MemberSearchCriteriaDTO searchCriteria);

    // 회원 상태 변경 (승인/탈퇴)
    void changeMemberStatus(Long memberId, AccountStatus accountStatus);

    // 개인정보 동의 처리
    void agreeToPrivacyPolicy(Long memberId);// 이메일로 회원 조회
    Member getMemberByEmail(String email);

    // 이메일로 회원의 전화번호 업데이트
    void updateMemberPhoneNumber(String email, String phoneNumber);

    // 회원 목록 조회 (관리자용) - 기존 정의 확인 필요
    List<Member> getMembersByCriteria(String roleType, String accountStatus, String keyword);

}
