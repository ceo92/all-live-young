package allliveyoung.wms.web.controller;

import allliveyoung.wms.domain.AccountStatus;
import allliveyoung.wms.domain.Member;
import allliveyoung.wms.domain.RoleType;
import allliveyoung.wms.service.MemberService;
import allliveyoung.wms.web.dto.MemberSearchCriteriaDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final MemberService memberService;
    private static final String LOGIN_MEMBER = "loginMember";

    Member admin = Member.builder().roleType(RoleType.ADMIN).build();

    @Autowired
    public AdminController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원 목록 조회 (단순 조회용)
    @GetMapping("/members")
    public String listMembers(@ModelAttribute MemberSearchCriteriaDTO searchCriteria, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
//        Member admin = (Member) session.getAttribute(LOGIN_MEMBER);
        if (admin == null || admin.getRoleType() != RoleType.ADMIN) {
            redirectAttributes.addFlashAttribute("errorMessage", "관리자 권한이 필요합니다.");
            return "redirect:/member/login";
        }

        List<Member> members = memberService.getMembersByCriteria(searchCriteria);
        model.addAttribute("members", members);
        model.addAttribute("searchCriteria", searchCriteria);
        model.addAttribute("showActionButtons", false); // 버튼 비활성화
        return "admin-member-list";
    }

    // 회원 상세 조회 (단순 조회용 - 버튼 없음)
    @GetMapping("/members/{memberId}")
    public String viewMemberDetails(@PathVariable Long memberId, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
//        Member admin = (Member) session.getAttribute(LOGIN_MEMBER);
        if (admin == null || admin.getRoleType() != RoleType.ADMIN) {
            redirectAttributes.addFlashAttribute("errorMessage", "관리자 권한이 필요합니다.");
            return "redirect:/member/login";
        }

        // 회원의 상세 정보를 가져오기
        Member member = memberService.getMemberDetails(memberId);
        if (member == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "해당 회원 정보를 찾을 수 없습니다.");
            return "redirect:/admin/members";
        }

        // 모델에 회원 정보를 추가하여 view에 전달
        model.addAttribute("member", member);
        model.addAttribute("showActionButtons", false); // 버튼 비활성화
        return "admin-member-details"; // 해당 회원의 상세 정보를 표시하는 HTML 페이지로 이동
    }

    // 회원 승인 요청 목록 조회
    @GetMapping("/approval-requests")
    public String listApprovalRequests(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
//        Member admin = (Member) session.getAttribute(LOGIN_MEMBER);
        if (admin == null || admin.getRoleType() != RoleType.ADMIN) {
            redirectAttributes.addFlashAttribute("errorMessage", "관리자 권한이 필요합니다.");
            return "redirect:/member/login";
        }

        MemberSearchCriteriaDTO searchCriteria = MemberSearchCriteriaDTO.builder()
                .accountStatus(AccountStatus.WAITING_APPROVAL)
                .build();
        List<Member> members = memberService.getMembersByCriteria(searchCriteria);
        model.addAttribute("members", members);
        model.addAttribute("showActionButtons", true); // 승인 버튼 표시
        return "admin-approval-list"; // 승인 요청 목록 HTML로 이동
    }

    // 회원 탈퇴 요청 목록 조회
    @GetMapping("/withdraw-requests")
    public String listWithdrawalRequests(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
//        Member admin = (Member) session.getAttribute(LOGIN_MEMBER);
        if (admin == null || admin.getRoleType() != RoleType.ADMIN) {
            redirectAttributes.addFlashAttribute("errorMessage", "관리자 권한이 필요합니다.");
            return "redirect:/member/login";
        }

        MemberSearchCriteriaDTO searchCriteria = MemberSearchCriteriaDTO.builder()
                .accountStatus(AccountStatus.WAITING_CANCEL)
                .build();
        List<Member> members = memberService.getMembersByCriteria(searchCriteria);
        model.addAttribute("members", members);
        model.addAttribute("showActionButtons", true); // 탈퇴 승인 버튼 표시
        return "admin-withdrawal-list"; // 탈퇴 요청 목록 HTML로 이동
    }

    // 회원 승인 처리
    @PostMapping("/members/{memberId}/approve")
    public String approveMember(@PathVariable Long memberId, RedirectAttributes redirectAttributes, HttpSession session) {
//        Member admin = (Member) session.getAttribute(LOGIN_MEMBER);
        if (admin == null || admin.getRoleType() != RoleType.ADMIN) {
            redirectAttributes.addFlashAttribute("errorMessage", "관리자 권한이 필요합니다.");
            return "redirect:/member/login";
        }

        memberService.changeMemberStatus(memberId, AccountStatus.ACTIVATION);
        redirectAttributes.addFlashAttribute("message", "회원이 승인되었습니다.");
        return "redirect:/admin/members";
    }

    // 회원 탈퇴 승인 처리
    @PostMapping("/members/{memberId}/approve-withdrawal")
    public String approveWithdrawal(@PathVariable Long memberId, RedirectAttributes redirectAttributes, HttpSession session) {
//        Member admin = (Member) session.getAttribute(LOGIN_MEMBER);
        if (admin == null || admin.getRoleType() != RoleType.ADMIN) {
            redirectAttributes.addFlashAttribute("errorMessage", "관리자 권한이 필요합니다.");
            return "redirect:/member/login";
        }

        memberService.changeMemberStatus(memberId, AccountStatus.INACTIVATION);
        redirectAttributes.addFlashAttribute("message", "회원의 탈퇴가 처리되었습니다.");
        return "redirect:/admin/members";
    }

    // 회원 반려 처리 (회원 삭제)
    @PostMapping("/members/{memberId}/reject")
    public String rejectMember(@PathVariable Long memberId, RedirectAttributes redirectAttributes, HttpSession session) {
//        Member admin = (Member) session.getAttribute(LOGIN_MEMBER);
        if (admin == null || admin.getRoleType() != RoleType.ADMIN) {
            redirectAttributes.addFlashAttribute("errorMessage", "관리자 권한이 필요합니다.");
            return "redirect:/member/login";
        }

        memberService.deleteMember(memberId); // 회원 정보 삭제
        redirectAttributes.addFlashAttribute("message", "회원이 반려되었습니다.");
        return "redirect:/admin/members";
    }
}
