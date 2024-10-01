package allliveyoung.wms.web.controller;

import allliveyoung.wms.domain.Announcement;
import allliveyoung.wms.domain.Member;
import allliveyoung.wms.domain.RoleType;
import allliveyoung.wms.service.AnnouncementService;
import allliveyoung.wms.web.dto.AnnouncementDTO;
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
@RequestMapping("/announcements")
public class AnnouncementController {

    private final AnnouncementService announcementService;
    private static final String LOGIN_MEMBER = "loginMember";

    @Autowired
    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    // 공지사항 목록 조회
    @GetMapping
    public String getAnnouncements(Model model) {
        List<Announcement> announcements = announcementService.getAllAnnouncements();
        model.addAttribute("announcements", announcements);
        return "announcement-list";
    }

    // 공지사항 상세 조회
    @GetMapping("/{id}")
    public String getAnnouncement(@PathVariable("id") Long id, Model model) {
        Announcement announcement = announcementService.getAnnouncementDetails(id);
        if (announcement == null) {
            return "error/404"; // 공지사항이 없을 경우 처리
        }
        model.addAttribute("announcement", announcement);
        return "announcement-detail";
    }

    // 권한 검증 메서드
    private boolean isAuthorized(Member member) {
        return member != null && (member.getRoleType() == RoleType.ADMIN || member.getRoleType() == RoleType.MANAGER);
    }

    // 공지사항 작성 폼 표시
    @GetMapping("/save")
    public String showSaveForm(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Member member = (Member) session.getAttribute(LOGIN_MEMBER);
        if (!isAuthorized(member)) {
            redirectAttributes.addFlashAttribute("errorMessage", "공지사항 작성 권한이 없습니다.");
            return "redirect:/announcements";
        }

        model.addAttribute("announcementDTO", new AnnouncementDTO());
        return "announcement-save";
    }

    // 공지사항 작성 처리
    @PostMapping("/save")
    public String saveAnnouncement(@Valid @ModelAttribute("announcementDTO") AnnouncementDTO announcementDTO,
                                   BindingResult bindingResult, HttpSession session,
                                   RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "announcement-save";
        }

        Member member = (Member) session.getAttribute(LOGIN_MEMBER);
        if (!isAuthorized(member)) {
            redirectAttributes.addFlashAttribute("errorMessage", "공지사항 작성 권한이 없습니다.");
            return "redirect:/announcements";
        }

        announcementDTO.setManagerId(member.getMemberId());

        announcementService.createAnnouncement(announcementDTO);
        redirectAttributes.addFlashAttribute("message", "공지사항이 등록되었습니다.");
        return "redirect:/announcements";
    }

    // 공지사항 수정 폼 표시
    @GetMapping("/{id}/update")
    public String showUpdateForm(@PathVariable("id") Long id, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Member member = (Member) session.getAttribute(LOGIN_MEMBER);
        if (!isAuthorized(member)) {
            redirectAttributes.addFlashAttribute("errorMessage", "공지사항 수정 권한이 없습니다.");
            return "redirect:/announcements";
        }

        Announcement announcement = announcementService.getAnnouncementDetails(id);
        if (announcement == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "존재하지 않는 공지사항입니다.");
            return "redirect:/announcements";
        }

        AnnouncementDTO announcementDTO = AnnouncementDTO.builder()
                .announcementId(announcement.getAnnouncementId())
                .managerId(announcement.getManagerId())
                .title(announcement.getTitle())
                .content(announcement.getContent())
                .highlight(announcement.getHighlight())
                .build();
        model.addAttribute("announcementDTO", announcementDTO);
        return "announcement-update";
    }

    // 공지사항 수정 처리
    @PostMapping("/{id}/update")
    public String updateAnnouncement(@Valid @ModelAttribute("announcementDTO") AnnouncementDTO announcementDTO,
                                     BindingResult bindingResult, HttpSession session,
                                     RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "announcement-update";
        }

        Member member = (Member) session.getAttribute(LOGIN_MEMBER);
        if (!isAuthorized(member)) {
            redirectAttributes.addFlashAttribute("errorMessage", "공지사항 수정 권한이 없습니다.");
            return "redirect:/announcements";
        }

        announcementDTO.setManagerId(member.getMemberId());

        announcementService.updateAnnouncement(announcementDTO);
        redirectAttributes.addFlashAttribute("message", "공지사항이 수정되었습니다.");
        return "redirect:/announcements/" + announcementDTO.getAnnouncementId();
    }

    // 공지사항 삭제 처리
    @PostMapping("/{id}/delete")
    public String deleteAnnouncement(@PathVariable("id") Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        Member member = (Member) session.getAttribute(LOGIN_MEMBER);
        if (!isAuthorized(member)) {
            redirectAttributes.addFlashAttribute("errorMessage", "공지사항 삭제 권한이 없습니다.");
            return "redirect:/announcements";
        }

        announcementService.deleteAnnouncement(id, member.getMemberId());
        redirectAttributes.addFlashAttribute("message", "공지사항이 삭제되었습니다.");
        return "redirect:/announcements";
    }
}
