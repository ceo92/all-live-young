package allliveyoung.wms.service;

import allliveyoung.wms.domain.AccountStatus;
import allliveyoung.wms.domain.Member;
import allliveyoung.wms.domain.PasswordResetToken;
import allliveyoung.wms.domain.RoleType;
import allliveyoung.wms.exception.InvalidTokenException;
import allliveyoung.wms.exception.MemberNotFoundException;
import allliveyoung.wms.mapper.MemberMapper;
import allliveyoung.wms.mapper.PasswordResetTokenMapper;
import allliveyoung.wms.web.dto.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final ModelMapper modelMapper;
    private final EmailService emailService; // 인터페이스로 변경
    private final PasswordResetTokenMapper passwordResetTokenMapper;
//    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper, PasswordResetTokenMapper passwordResetTokenMapper,
                             ModelMapper modelMapper, EmailService emailService) { // 인터페이스로 변경
        this.memberMapper = memberMapper;
        this.passwordResetTokenMapper = passwordResetTokenMapper;
        this.modelMapper = modelMapper;
        this.emailService = emailService;
//        this.passwordEncoder = passwordEncoder;
    }

    // 회원가입
    @Override
    @Transactional
    public void registerMember(MemberSaveDTO memberSaveDTO) {
        Member member = modelMapper.map(memberSaveDTO, Member.class);
//        member.setPassword(passwordEncoder.encode(memberSaveDTO.getPassword()));
        member.setAccountStatus(AccountStatus.WAITING_APPROVAL); // 승인 대기 상태로 저장
        member.setJoinDate(LocalDateTime.now());
        member.setAgreeDate(LocalDateTime.now());
        member.setIsAgree(true);
        member.setLastLoginDate(LocalDateTime.now());

        memberMapper.insertMember(member);
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

    // 비밀번호 재설정 요청 (이메일 발송)
    @Override
    @Transactional
    public void requestPasswordReset(PasswordResetRequestDTO passwordResetRequestDTO) throws Exception {
        Member member = memberMapper.selectMemberByNamePhoneAndBusinessNumber(
                passwordResetRequestDTO.getName(),
                passwordResetRequestDTO.getPhoneNumber(),
                passwordResetRequestDTO.getBusinessNumber()
        );

        if (member != null) {
            // 토큰 생성 및 저장
            String resetToken = generateResetToken();
            PasswordResetToken token = PasswordResetToken.builder()
                    .token(resetToken)
                    .memberId(member.getMemberId())
                    .expirationTime(LocalDateTime.now().plusHours(1))  // 1시간 후 만료
                    .build();

            passwordResetTokenMapper.insertToken(token);

            // 비밀번호 재설정 링크 전송
            String resetLink = "https://yourdomain.com/member/reset-password?token=" + resetToken;
            emailService.sendPasswordResetEmail(member.getEmail(), resetLink);
        } else {
            throw new MemberNotFoundException("일치하는 회원 정보를 찾을 수 없습니다.");
        }
    }

    // 비밀번호 재설정 처리
    @Override
    @Transactional
    public void resetPassword(PasswordResetDTO passwordResetDTO) throws InvalidTokenException, MemberNotFoundException {
        // 토큰 검증
        PasswordResetToken token = passwordResetTokenMapper.selectTokenByToken(passwordResetDTO.getResetToken());

        if (token == null || token.getExpirationTime().isBefore(LocalDateTime.now())) {
            throw new InvalidTokenException("유효하지 않거나 만료된 토큰입니다.");
        }

        // 회원 정보 조회
        Member member = memberMapper.selectMemberById(token.getMemberId());
        if (member != null) {
            // 새 비밀번호 설정 (암호화 없이)
            member.setPassword(passwordResetDTO.getNewPassword());
            memberMapper.updateMember(member);

            // 사용된 토큰 삭제
            passwordResetTokenMapper.deleteTokenByMemberId(member.getMemberId());
        } else {
            throw new MemberNotFoundException("회원 정보를 찾을 수 없습니다.");
        }
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
            member.getAddress().setRoadNameAddress(memberUpdateDTO.getRoadNameAddress());
            member.getAddress().setDetailsAddress(memberUpdateDTO.getDetailsAddress());
            if (member.getRoleType() == RoleType.COMPANY) {
                member.setBusinessNumber(memberUpdateDTO.getBusinessNumber());
            } else if (member.getRoleType() == RoleType.MANAGER) {
                member.getWarehouse().setWarehouseId(memberUpdateDTO.getWarehouseId());
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
            member.setAccountStatus(AccountStatus.WAITING_CANCEL); // 탈퇴 대기 상태로 변경
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

    // 이메일로 회원 조회
    @Override
    public Member getMemberByEmail(String email) {
        return memberMapper.selectMemberByEmail(email);
    }

    // 이메일로 회원의 전화번호 업데이트
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
        return List.of();
    }

    // 토큰 생성 메서드
    private String generateResetToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    @Transactional
    public void deleteMember(Long memberId) {
        memberMapper.deleteMember(memberId);
    }

}
