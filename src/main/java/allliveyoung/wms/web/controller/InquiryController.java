package allliveyoung.wms.web.controller;

import allliveyoung.wms.domain.Answer;
import allliveyoung.wms.domain.Inquiry;
import allliveyoung.wms.domain.Member;
import allliveyoung.wms.domain.RoleType;
import allliveyoung.wms.service.InquiryService;
import allliveyoung.wms.web.dto.AnswerDTO;
import allliveyoung.wms.web.dto.InquiryDTO;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/inquiries")
public class InquiryController {

    private final InquiryService inquiryService;
    private static final String LOGIN_MEMBER = "loginMember";


    @Autowired
    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    // 문의 목록 조회 (회사 사용자 및 관리자)
    @GetMapping
    public String getInquiries(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Member member = (Member) session.getAttribute(LOGIN_MEMBER);
        if (member == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "로그인이 필요합니다.");
            return "redirect:/member/login";
        }

        if (member.getRoleType() == RoleType.COMPANY) {
            // 회사 회원은 본인의 문의만 조회
            List<Inquiry> inquiries = inquiryService.getInquiriesByMemberId(member.getMemberId());
            model.addAttribute("inquiries", inquiries);
            return "inquiry-list";
        } else if (member.getRoleType() == RoleType.ADMIN || member.getRoleType() == RoleType.MANAGER) {
            // 관리자와 창고관리자는 모든 문의 조회
            List<Inquiry> inquiries = inquiryService.getAllInquiries();
            model.addAttribute("inquiries", inquiries);
            return "inquiry-list";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "접근 권한이 없습니다.");
            return "redirect:/";
        }
    }

    // 문의 상세 조회
    @GetMapping("/{id}")
    public String getInquiry(@PathVariable("id") Long id, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Member member = (Member) session.getAttribute(LOGIN_MEMBER);
        if (member == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "로그인이 필요합니다.");
            return "redirect:/member/login";
        }

        Inquiry inquiry = inquiryService.getInquiryDetails(id);
        Answer answer = inquiryService.getAnswerByInquiryId(id);

        if (member.getRoleType() == RoleType.COMPANY) {
            // 회사 회원은 본인의 문의만 조회 가능
            if (!inquiry.getMemberId().equals(member.getMemberId())) {
                redirectAttributes.addFlashAttribute("errorMessage", "접근 권한이 없습니다.");
                return "redirect:/inquiries";
            }
        }

        model.addAttribute("inquiry", inquiry);
        model.addAttribute("answer", answer);
        return "inquiry-detail";
    }

    // 문의 작성 폼 표시
    @GetMapping("/save")
    public String showInquiryForm(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Member member = (Member) session.getAttribute(LOGIN_MEMBER);
        if (member == null || member.getRoleType() != RoleType.COMPANY) {
            redirectAttributes.addFlashAttribute("errorMessage", "문의 작성 권한이 없습니다.");
            return "redirect:/inquiries";
        }

        model.addAttribute("inquiryDTO", new InquiryDTO());
        return "inquiry-save";
    }

    // 문의 작성 처리
    @PostMapping("/save")
    public String saveInquiry(@Valid @ModelAttribute("inquiryDTO") InquiryDTO inquiryDTO,
                              BindingResult bindingResult, HttpSession session,
                              RedirectAttributes redirectAttributes) {
        Member member = (Member) session.getAttribute(LOGIN_MEMBER);
        if (member == null || member.getRoleType() != RoleType.COMPANY) {
            redirectAttributes.addFlashAttribute("errorMessage", "문의 작성 권한이 없습니다.");
            return "redirect:/inquiries";
        }

        if (bindingResult.hasErrors()) {
            return "inquiry-save";
        }

        inquiryDTO.setMemberId(member.getMemberId());
        inquiryService.createInquiry(inquiryDTO);
        redirectAttributes.addFlashAttribute("message", "문의가 등록되었습니다.");
        return "redirect:/inquiries";
    }

    // 문의 수정 폼 표시
    @GetMapping("/{id}/update")
    public String showInquiryUpdateForm(@PathVariable("id") Long id, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Member member = (Member) session.getAttribute(LOGIN_MEMBER);
        if (member == null || member.getRoleType() != RoleType.COMPANY) {
            redirectAttributes.addFlashAttribute("errorMessage", "문의 수정 권한이 없습니다.");
            return "redirect:/inquiries";
        }

        Inquiry inquiry = inquiryService.getInquiryDetails(id);
        if (!inquiry.getMemberId().equals(member.getMemberId())) {
            redirectAttributes.addFlashAttribute("errorMessage", "본인의 문의만 수정할 수 있습니다.");
            return "redirect:/inquiries";
        }

        InquiryDTO inquiryDTO = InquiryDTO.builder()
                .inquiryId(inquiry.getInquiryId())
                .memberId(inquiry.getMemberId())
                .inquiryTitle(inquiry.getInquiryTitle())
                .inquiryContent(inquiry.getInquiryContent())
                .build();
        model.addAttribute("inquiryDTO", inquiryDTO);
        return "inquiry-update";
    }

    // 문의 수정 처리
    @PostMapping("/{id}/update")
    public String updateInquiry(@Valid @ModelAttribute("inquiryDTO") InquiryDTO inquiryDTO,
                                BindingResult bindingResult, HttpSession session,
                                RedirectAttributes redirectAttributes) {
        Member member = (Member) session.getAttribute(LOGIN_MEMBER);
        if (member == null || member.getRoleType() != RoleType.COMPANY) {
            redirectAttributes.addFlashAttribute("errorMessage", "문의 수정 권한이 없습니다.");
            return "redirect:/inquiries";
        }

        if (bindingResult.hasErrors()) {
            return "inquiry-update";
        }

        inquiryDTO.setMemberId(member.getMemberId());
        inquiryService.updateInquiry(inquiryDTO);
        redirectAttributes.addFlashAttribute("message", "문의가 수정되었습니다.");
        return "redirect:/inquiries/" + inquiryDTO.getInquiryId();
    }

    // 문의 삭제 처리
    @PostMapping("/{id}/delete")
    public String deleteInquiry(@PathVariable("id") Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        Member member = (Member) session.getAttribute(LOGIN_MEMBER);
        if (member == null || member.getRoleType() != RoleType.COMPANY) {
            redirectAttributes.addFlashAttribute("errorMessage", "문의 삭제 권한이 없습니다.");
            return "redirect:/inquiries";
        }

        Inquiry inquiry = inquiryService.getInquiryDetails(id);
        if (!inquiry.getMemberId().equals(member.getMemberId())) {
            redirectAttributes.addFlashAttribute("errorMessage", "본인의 문의만 삭제할 수 있습니다.");
            return "redirect:/inquiries";
        }

        inquiryService.deleteInquiry(id, member.getMemberId());
        redirectAttributes.addFlashAttribute("message", "문의가 삭제되었습니다.");
        return "redirect:/inquiries";
    }

    // 미답변 문의 목록 조회 (창고 관리자)
    @GetMapping("/unanswered")
    public String getUnansweredInquiries(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Member member = (Member) session.getAttribute(LOGIN_MEMBER);
        if (member == null || member.getRoleType() != RoleType.MANAGER) {
            redirectAttributes.addFlashAttribute("errorMessage", "접근 권한이 없습니다.");
            return "redirect:/inquiries";
        }

        List<Inquiry> inquiries = inquiryService.getUnansweredInquiries();
        model.addAttribute("inquiries", inquiries);
        return "inquiry-unanswered-list";
    }

    // 답변 작성 폼 표시 (창고 관리자)
    @GetMapping("/{id}/answer")
    public String showAnswerForm(@PathVariable("id") Long id, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Member member = (Member) session.getAttribute(LOGIN_MEMBER);
        if (member == null || member.getRoleType() != RoleType.MANAGER) {
            redirectAttributes.addFlashAttribute("errorMessage", "답변 작성 권한이 없습니다.");
            return "redirect:/inquiries";
        }

        model.addAttribute("answerDTO", new AnswerDTO());
        model.addAttribute("inquiryId", id);
        return "answer-save";
    }

    // 답변 작성 처리 (창고 관리자)
    @PostMapping("/{id}/answer")
    public String saveAnswer(@Valid @ModelAttribute("answerDTO") AnswerDTO answerDTO,
                             BindingResult bindingResult, @PathVariable("id") Long id,
                             HttpSession session, RedirectAttributes redirectAttributes) {
        Member member = (Member) session.getAttribute(LOGIN_MEMBER);
        if (member == null || member.getRoleType() != RoleType.MANAGER) {
            redirectAttributes.addFlashAttribute("errorMessage", "답변 작성 권한이 없습니다.");
            return "redirect:/inquiries";
        }

        if (bindingResult.hasErrors()) {
            return "answer-save";
        }

        answerDTO.setInquiryId(id);
        answerDTO.setMemberId(member.getMemberId());
        inquiryService.createAnswer(answerDTO);
        redirectAttributes.addFlashAttribute("message", "답변이 등록되었습니다.");
        return "redirect:/inquiries/" + id;
    }

    // 답변 수정 폼 표시 (창고 관리자)
    @GetMapping("/{inquiryId}/answer/{answerId}/update")
    public String showAnswerUpdateForm(@PathVariable("inquiryId") Long inquiryId,
                                       @PathVariable("answerId") Long answerId, Model model,
                                       HttpSession session, RedirectAttributes redirectAttributes) {
        Member member = (Member) session.getAttribute(LOGIN_MEMBER);
        if (member == null || member.getRoleType() != RoleType.MANAGER) {
            redirectAttributes.addFlashAttribute("errorMessage", "답변 수정 권한이 없습니다.");
            return "redirect:/inquiries";
        }

        Answer answer = inquiryService.getAnswerByInquiryId(inquiryId);
        if (!answer.getMemberId().equals(member.getMemberId())) {
            redirectAttributes.addFlashAttribute("errorMessage", "본인의 답변만 수정할 수 있습니다.");
            return "redirect:/inquiries";
        }

        AnswerDTO answerDTO = AnswerDTO.builder()
                .answerId(answer.getAnswerId())
                .inquiryId(answer.getInquiryId())
                .memberId(answer.getMemberId())
                .answerContent(answer.getAnswerContent())
                .build();
        model.addAttribute("answerDTO", answerDTO);
        return "answer-update";
    }

    // 답변 수정 처리 (창고 관리자)
    @PostMapping("/{inquiryId}/answer/{answerId}/update")
    public String updateAnswer(@Valid @ModelAttribute("answerDTO") AnswerDTO answerDTO,
                               BindingResult bindingResult, @PathVariable("inquiryId") Long inquiryId,
                               HttpSession session, RedirectAttributes redirectAttributes) {
        Member member = (Member) session.getAttribute(LOGIN_MEMBER);
        if (member == null || member.getRoleType() != RoleType.MANAGER) {
            redirectAttributes.addFlashAttribute("errorMessage", "답변 수정 권한이 없습니다.");
            return "redirect:/inquiries";
        }

        if (bindingResult.hasErrors()) {
            return "answer-update";
        }

        answerDTO.setMemberId(member.getMemberId());
        inquiryService.updateAnswer(answerDTO);
        redirectAttributes.addFlashAttribute("message", "답변이 수정되었습니다.");
        return "redirect:/inquiries/" + inquiryId;
    }

    // 답변 삭제 처리 (창고 관리자)
    @PostMapping("/{inquiryId}/answer/{answerId}/delete")
    public String deleteAnswer(@PathVariable("inquiryId") Long inquiryId,
                               @PathVariable("answerId") Long answerId,
                               HttpSession session, RedirectAttributes redirectAttributes) {
        Member member = (Member) session.getAttribute(LOGIN_MEMBER);
        if (member == null || member.getRoleType() != RoleType.MANAGER) {
            redirectAttributes.addFlashAttribute("errorMessage", "답변 삭제 권한이 없습니다.");
            return "redirect:/inquiries";
        }

        Answer answer = inquiryService.getAnswerByInquiryId(inquiryId);
        if (!answer.getMemberId().equals(member.getMemberId())) {
            redirectAttributes.addFlashAttribute("errorMessage", "본인의 답변만 삭제할 수 있습니다.");
            return "redirect:/inquiries";
        }

        inquiryService.deleteAnswer(answerId, member.getMemberId());
        redirectAttributes.addFlashAttribute("message", "답변이 삭제되었습니다.");
        return "redirect:/inquiries/" + inquiryId;
    }
}
