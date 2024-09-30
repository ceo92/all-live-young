package allliveyoung.wms.web.controller;

import allliveyoung.wms.domain.Member;
import allliveyoung.wms.domain.RoleType;
import allliveyoung.wms.exception.InvalidTokenException;
import allliveyoung.wms.exception.MemberNotFoundException;
import allliveyoung.wms.service.MemberService;
import allliveyoung.wms.web.dto.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private static final String LOGIN_MEMBER = "loginMember";

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 로그인 폼 표시
    @GetMapping("/login")
    public String showLoginForm(@RequestParam(value = "error", required = false) String error, Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        model.addAttribute("error", error);
        return "login2";
    }

    // 회원가입 유형 선택 페이지
    @GetMapping("/register-select")
    public String showRegisterSelect() {
        return "register-select";
    }

    // 회원가입 폼 표시
    @GetMapping("/register")
    public String showRegisterForm(@RequestParam("roleType") RoleType roleType, Model model) {
        MemberSaveDTO memberSaveDTO = new MemberSaveDTO();
        memberSaveDTO.setRoleType(roleType);
        model.addAttribute("memberSaveDTO", memberSaveDTO);
        model.addAttribute("roleType", roleType); // roleType 변수를 모델에 추가
        return "register";
    }

    // 회원가입 처리
    @PostMapping("/register")
    public String registerMember(@Valid @ModelAttribute("memberSaveDTO") MemberSaveDTO memberSaveDTO,
                                 BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("roleType", memberSaveDTO.getRoleType());
            return "register-success";
        }

        memberService.registerMember(memberSaveDTO);

        return "redirect:/member/register-success";
    }

    // 회원가입 완료 페이지 표시
    @GetMapping("/register/success")
    public String registerSuccess() {
        return "register-success";
    }

    // 아이디 찾기 폼 표시
    @GetMapping("/find-email")
    public String showFindEmailForm(Model model) {
        model.addAttribute("findAccountDTO", new FindAccountDTO());
        return "find-email";
    }

    // 아이디 찾기 처리
    @PostMapping("/find-email")
    public String processFindEmail(@Valid @ModelAttribute("findAccountDTO") FindAccountDTO findAccountDTO,
                                   BindingResult bindingResult,
                                   Model model) {
        if (bindingResult.hasErrors()) {
            return "find-email";
        }

        String email = memberService.findEmailByNameAndPhone(findAccountDTO.getName(), findAccountDTO.getPhoneNumber());

        if (email != null) {
            model.addAttribute("email", email);
            return "email-found"; // 이메일을 표시하는 뷰
        } else {
            model.addAttribute("errorMessage", "해당 정보로 등록된 이메일이 없습니다.");
            return "find-email";
        }
    }

    // 마이페이지 조회
    @GetMapping("/profile")
    public String getMemberDetails(Model model, HttpSession session) {
//        Member member = (Member) session.getAttribute(LOGIN_MEMBER);
//        if (member == null) {
//            return "redirect";
//        }

        Member memberDetails = memberService.getMemberDetails(1L);
        model.addAttribute("member", memberDetails);
        return "profile";
    }

    // 마이페이지 수정 폼 표시
    @GetMapping("/profile/edit")
    public String showEditProfileForm(Model model, HttpSession session) {
        Member member = (Member) session.getAttribute(LOGIN_MEMBER);
        if (member == null) {
            return "redirect:/member/login";
        }

        Member memberDetails = memberService.getMemberDetails(member.getMemberId());
        MemberUpdateDTO memberUpdateDTO = new MemberUpdateDTO(memberDetails);
        model.addAttribute("memberUpdateDTO", memberUpdateDTO);
        return "edit-profile";
    }

    // 마이페이지 수정 처리
    @PostMapping("/profile/edit")
    public String updateMember(@Valid @ModelAttribute("memberUpdateDTO") MemberUpdateDTO memberUpdateDTO,
                               BindingResult bindingResult,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "edit-profile";
        }

        Member member = (Member) session.getAttribute(LOGIN_MEMBER);
        if (member == null || !member.getMemberId().equals(memberUpdateDTO.getMemberId())) {
            return "redirect:/member/login";
        }

        memberService.updateMember(memberUpdateDTO);
        redirectAttributes.addFlashAttribute("message", "회원 정보가 수정되었습니다.");
        return "redirect:/member/profile";
    }

    // 회원 탈퇴 확인 페이지
    @GetMapping("/withdraw")
    public String showWithdrawConfirm(HttpSession session, RedirectAttributes redirectAttributes) {
        Member member = (Member) session.getAttribute(LOGIN_MEMBER);
        if (member == null) {
            return "redirect:/member/login";
        }
        return "withdraw-confirm";
    }

    // 회원 탈퇴 신청
    @PostMapping("/withdraw-confirm")
    public String requestWithdrawal(HttpSession session, RedirectAttributes redirectAttributes) {
        Member member = (Member) session.getAttribute(LOGIN_MEMBER);
        if (member == null) {
            return "redirect:/member/profile";
        }

        memberService.requestWithdrawal(member.getMemberId());
        session.invalidate();
        redirectAttributes.addFlashAttribute("message", "회원 탈퇴 신청이 완료되었습니다.");
        return "redirect:/";
    }

    // 비밀번호 재설정 요청 폼 표시
    @GetMapping("/forgot-password")
    public String showForgotPasswordForm(Model model) {
        model.addAttribute("passwordResetRequestDTO", new PasswordResetRequestDTO());
        return "forgot-password";
    }

    // 비밀번호 재설정 요청 처리
    @PostMapping("/forgot-password")
    public String requestPasswordReset(@Valid @ModelAttribute("passwordResetRequestDTO") PasswordResetRequestDTO passwordResetRequestDTO,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "forgot-password";
        }

        try {
            memberService.requestPasswordReset(passwordResetRequestDTO);
            redirectAttributes.addFlashAttribute("message", "비밀번호 재설정 이메일이 발송되었습니다.");
            return "redirect:/member/login";
        } catch (MemberNotFoundException e) {
            bindingResult.reject("notFound", e.getMessage());
            return "forgot-password";
        } catch (Exception e) {
            bindingResult.reject("error", "이메일 발송 중 오류가 발생했습니다.");
            return "forgot-password";
        }
    }

    // 비밀번호 재설정 페이지로 이동
    @GetMapping("/reset-password")
    public String showResetPasswordPage(@RequestParam("token") String token, Model model) {
        model.addAttribute("resetToken", token);
        model.addAttribute("passwordResetDTO", new PasswordResetDTO());
        return "reset-password";
    }

    // 비밀번호 재설정 처리
    @PostMapping("/reset-password")
    public String resetPassword(@Valid @ModelAttribute("passwordResetDTO") PasswordResetDTO passwordResetDTO,
                                BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "reset-password";
        }

        try {
            memberService.resetPassword(passwordResetDTO);
            redirectAttributes.addFlashAttribute("message", "비밀번호가 성공적으로 재설정되었습니다.");
            return "redirect:/member/login";
        } catch (InvalidTokenException e) {
            bindingResult.rejectValue("resetToken", "invalid", e.getMessage());
            return "reset-password";
        } catch (MemberNotFoundException e) {
            bindingResult.reject("notFound", e.getMessage());
            return "reset-password";
        }
    }
}
