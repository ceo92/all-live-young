package allliveyoung.wms.service;

import allliveyoung.wms.domain.AccountStatus;
import allliveyoung.wms.domain.Member;
import allliveyoung.wms.domain.RoleType;
import allliveyoung.wms.exception.InvalidTokenException;
import allliveyoung.wms.exception.MemberNotFoundException;
import allliveyoung.wms.mapper.MemberMapper;
import allliveyoung.wms.web.dto.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailServiceImpl emailServiceImpl;
//    private final EmailService emailService;

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper, ModelMapper modelMapper,
//                             ,EmailService emailService
                             EmailServiceImpl emailServiceImpl) {
        this.memberMapper = memberMapper;
        this.modelMapper = modelMapper;
        this.passwordEncoder = new BCryptPasswordEncoder();
//        this.emailService = emailService;
        this.emailServiceImpl = emailServiceImpl;
    }

    // 회원가입
    @Override
    @Transactional
    public void registerMember(MemberSaveDTO memberSaveDTO) {
        Member member = modelMapper.map(memberSaveDTO, Member.class);
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        member.setAccountStatus(AccountStatus.WAITING_APPROVAL); // 승인 대기 상태로 저장
        member.setJoinDate(LocalDateTime.now());
        member.setAgreeDate(LocalDateTime.now());
        member.setIsAgree(true);

        memberMapper.insertMember(member);
    }

    // 로그인
    @Override
    public Member login(LoginDTO loginDTO) {
        Member member = memberMapper.selectMemberByEmail(loginDTO.getEmail());
        if (member != null && passwordEncoder.matches(loginDTO.getPassword(), member.getPassword())) {
            // 로그인 성공 시 마지막 로그인 날짜 업데이트
            member.setLastLoginDate(LocalDateTime.now());
            memberMapper.updateMember(member);
            return member;
        }
        return null;
    }

    // 아이디 찾기
    @Override
    public String findEmailByNameAndPhone(String name, String phoneNumber) {
        Member member = memberMapper.selectMemberByNameAndPhone(name, phoneNumber);
        if (member != null) {
            return member.getEmail();
        }
        return null;
    }

//     비밀번호 재설정 요청 (이메일 발송)
    @Override
    @Transactional
    public void requestPasswordReset(PasswordResetRequestDTO passwordResetRequestDTO) throws Exception {
        Member member = memberMapper.selectMemberByNamePhoneAndBusinessNumber(
                passwordResetRequestDTO.getName(),
                passwordResetRequestDTO.getPhoneNumber(),
                passwordResetRequestDTO.getBusinessNumber()
        );
        if (member != null) {
            String resetToken = generateResetToken(); // 토큰 생성 메서드
            // 토큰을 저장하거나 캐시에 저장하는 로직 추가 필요
            String resetLink = "https://yourdomain.com/reset-password?token=" + resetToken;
            emailServiceImpl.sendPasswordResetEmail(member.getEmail(), resetLink);
        } else {
            throw new MemberNotFoundException("일치하는 회원 정보를 찾을 수 없습니다.");
        }
    }

    @Override
    @Transactional
    public void resetPassword(PasswordResetDTO passwordResetDTO) {
        // 토큰 검증 로직 (예시)
        boolean isTokenValid = validateResetToken(passwordResetDTO.getEmail(), passwordResetDTO.getResetToken());
        if (!isTokenValid) {
            throw new InvalidTokenException("유효하지 않거나 만료된 토큰입니다.");
        }

        Member member = memberMapper.selectMemberByEmail(passwordResetDTO.getEmail());
        if (member != null) {
            member.setPassword(passwordEncoder.encode(passwordResetDTO.getNewPassword()));
            memberMapper.updateMember(member);
        } else {
            throw new MemberNotFoundException("회원 정보를 찾을 수 없습니다.");
        }
    }


    private boolean validateResetToken(String email, String token) {

        return true;
    }

    // 마이페이지 조회
    @Override
    public Member getMemberDetails(Long memberId) {
        Member member = memberMapper.selectMemberById(memberId);
        if (member == null) {
            throw new MemberNotFoundException("회원 정보를 찾을 수 없습니다.");
        }
        return member;
    }

    // 마이페이지 수정
    @Override
    @Transactional
    public void updateMember(MemberUpdateDTO memberUpdateDTO) {
        Member member = memberMapper.selectMemberById(memberUpdateDTO.getMemberId());
        if (member != null) {
            // 필요한 필드 업데이트
            member.setPhoneNumber(memberUpdateDTO.getPhoneNumber());
            member.setRoadNameAddress(memberUpdateDTO.getRoadNameAddress());
            member.setDetailsAddress(memberUpdateDTO.getDetailsAddress());
            // 회원 유형별 특화 정보 업데이트 (예: businessNumber)
            if (member.getRoleType() == RoleType.COMPANY) {
                member.setBusinessNumber(memberUpdateDTO.getBusinessNumber());
            } else if (member.getRoleType() == RoleType.WAREHOUSE_MANAGER) {
                member.setWarehouseId(memberUpdateDTO.getWarehouseId());
            }
            memberMapper.updateMember(member);
        } else {
            throw new MemberNotFoundException("회원 정보를 찾을 수 없습니다.");
        }
    }

    // 회원 탈퇴 신청
    @Override
    @Transactional
    public void requestWithdrawal(Long memberId) {
        Member member = memberMapper.selectMemberById(memberId);
        if (member != null) {
            member.setAccountStatus(AccountStatus.WAITING_CANCEL); // 탈퇴 대기로 상태 변경
            memberMapper.updateMember(member);
        } else {
            throw new MemberNotFoundException("회원 정보를 찾을 수 없습니다.");
        }
    }

    // 회원 목록 조회 (관리자용)
    @Override
    public List<Member> getMembersByCriteria(MemberSearchCriteriaDTO searchCriteria) {
        return memberMapper.selectMembersByCriteria(
                searchCriteria.getRoleType(),
                searchCriteria.getAccountStatus(),
                searchCriteria.getKeyword()
        );
    }

    // 회원 상태 변경 (승인/탈퇴)
    @Override
    @Transactional
    public void changeMemberStatus(Long memberId, AccountStatus accountStatus) {
        Member member = memberMapper.selectMemberById(memberId);
        if (member != null) {
            member.setAccountStatus(accountStatus);
            memberMapper.updateMember(member);
        } else {
            throw new MemberNotFoundException("회원 정보를 찾을 수 없습니다.");
        }
    }

    // 개인정보 동의 처리
    @Override
    @Transactional
    public void agreeToPrivacyPolicy(Long memberId) {
        Member member = memberMapper.selectMemberById(memberId);
        if (member != null) {
            member.setIsAgree(true);
            member.setAgreeDate(LocalDateTime.now());
            memberMapper.updateMember(member);
        } else {
            throw new MemberNotFoundException("회원 정보를 찾을 수 없습니다.");
        }
    }

    // 비밀번호 재설정 토큰 생성 메서드
    private String generateResetToken() {

        return java.util.UUID.randomUUID().toString();
    }

    @Override
    public Member getMemberByEmail(String email) {
        return memberMapper.selectMemberByEmail(email);
    }

    @Override
    @Transactional
    public void updateMemberPhoneNumber(String email, String phoneNumber) {
        Member member = memberMapper.selectMemberByEmail(email);
        if (member != null) {
            member.setPhoneNumber(phoneNumber);
            memberMapper.updateMember(member);
        } else {
            throw new MemberNotFoundException("회원 정보를 찾을 수 없습니다.");
        }
    }

    @Override
    public List<Member> getMembersByCriteria(String roleType, String accountStatus, String keyword) {
        RoleType rt = roleType != null ? RoleType.valueOf(roleType) : null;
        AccountStatus as = accountStatus != null ? AccountStatus.valueOf(accountStatus) : null;
        return memberMapper.selectMembersByCriteria(rt, as, keyword);
    }
}
