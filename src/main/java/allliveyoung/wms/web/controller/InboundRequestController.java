package allliveyoung.allliveinbound.web.controller;

import allliveyoung.allliveinbound.domain.Member;
import allliveyoung.allliveinbound.service.InboundRequestService;
import allliveyoung.allliveinbound.web.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/inbound-requests")
@Log4j2
@RequiredArgsConstructor
public class InboundRequestController {

    private final InboundRequestService inboundRequestService;

    @GetMapping
    public String getInboundRequests(InboundPageRequestDTO inboundPageRequestDTO, Model model, Member member) {
        model.addAttribute("responseDTO", inboundRequestService.findInbounds(inboundPageRequestDTO, member));

        return "inbound-list";
    }

    @GetMapping("/{id}")
    public String getInboundRequest(@PathVariable Long id, Model model) {
        log.info(id);
        inboundRequestService.findInbound(id).forEach(log::info);
        model.addAttribute("responseDTO", inboundRequestService.findInbound(id));

        return "inbound-detail";
    }

    @GetMapping("/save")
    public String getInboundRequestSaveForm(Model model) {//todo 시큐리티 적용
        log.info("getInboundRequestSaveForm..........");

        model.addAttribute("warehouseDTO", inboundRequestService.getWarehouseList());
        model.addAttribute("matchedProductDTO", inboundRequestService.getMatchedProductList(1L));
        model.addAttribute("memberId", 1);
        return "inbound-register";
    }

    @GetMapping("/{id}/update")
    public String getInboundRequestUpdateForm(@PathVariable(value = "id") Long id, Model model) {
        log.info("getInboundRequestUpdateForm..........");
        model.addAttribute("responseDTO", inboundRequestService.findInbound(id));

        return "inbound-modify";
    }

    @PostMapping("/save")
    public String postInboundRequestSaveForm(@RequestBody InboundRequestSaveDTO inboundRequestSaveDTO,
                                             BindingResult bindingResult,
                                             RedirectAttributes redirectAttributes,
                                             Model model) {

        Long savedId = inboundRequestService.saveInbound(inboundRequestSaveDTO);

        redirectAttributes.addAttribute("id", savedId);

        return "redirect:/inbound-requests/{id}";
    }

    @PostMapping( "/{id}/delete")
    public String postInboundRequestDelete(@PathVariable(value = "id") Long id, InboundPageRequestDTO inboundPageRequestDTO, RedirectAttributes redirectAttributes) {
        log.info("delete..........");
        inboundRequestService.deleteInbound(id);

        redirectAttributes.addAttribute("page", 1);
        redirectAttributes.addAttribute("size", inboundPageRequestDTO.getSize());

        return "redirect:/inbound-requests";
    }

    @PostMapping("/{id}/update")
    public String postInboundRequestUpdateForm(@PathVariable Long id,
                                               @RequestBody InboundRequestUpdateDTO inboundRequestUpdateDTO,
                                               InboundPageRequestDTO inboundPageRequestDTO,
                                               BindingResult bindingResult,
                                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.info("has error..........");
            redirectAttributes.addFlashAttribute("error",bindingResult.getAllErrors());
        }

        inboundRequestService.updateInbound(id, inboundRequestUpdateDTO);
        redirectAttributes.addAttribute("id", id);

        return "redirect:/inbound-requests/{id}";
    }

    @PostMapping("/{id}/update-status") //todo rejectionNote도 같이 넘겨줘야 함
    public String PostRequestUpdateStatus(@ModelAttribute InboundStatusUpdateDTO inboundStatusUpdateDTO, RedirectAttributes redirectAttributes) {
        log.info("update status..........");
        inboundRequestService.updateInboundStatus(inboundStatusUpdateDTO);

        return "redirect:/inbound-requests/{id}";
    }
}
