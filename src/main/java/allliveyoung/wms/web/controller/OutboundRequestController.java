package allliveyoung.wms.web.controller;

import allliveyoung.wms.domain.Member;
import allliveyoung.wms.domain.OutboundRequest;
import allliveyoung.wms.service.OutboundRequestService;
import allliveyoung.wms.web.dto.OutboundRequestDTO;
import allliveyoung.wms.web.dto.PageRequestDTO;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequiredArgsConstructor
public class OutboundRequestController {

  private final OutboundRequestService outboundRequestService;

  //사용자, 총관리자, 창고관리자
  @GetMapping("/outbound-requests")
  public String getOutboundRequests(Model model,
      @ModelAttribute OutboundRequestDTO outboundRequestDTO) {
    List<OutboundRequest> outboundRequestList = outboundRequestService.findOutboundRequests(outboundRequestDTO);
    model.addAttribute("outboundRequestList", outboundRequestList);
    log.info(outboundRequestList);
    return "mem-outboundrequest";
  }

  // 사용자
  @GetMapping("/outbound-request/save")
  public String getOutboundRequestsSaveForm() {
    return "mem-outboundrequest_insert";
  }

  @PostMapping("/outbound-request/save")
  public String postOutboundRequestsSaveForm(@Validated OutboundRequestDTO outboundRequestDTO,
      BindingResult bindingResult, RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
      redirectAttributes.addFlashAttribute("errors", bindingResult);
      return "mem-outboundrequest_insert";
    }
    outboundRequestService.saveOutboundRequest(outboundRequestDTO);
    redirectAttributes.addFlashAttribute("message","저장 성공");

    return "redirect:/mem-outboundrequest";
  }

  // 사용자, 총관리자, 창고관리자
  @GetMapping({"/outbound-requests/{id}", "/outbound-request/{id}/update"})
  public String getOutboundRequest(@PathVariable("id") Long id, Model model) {
    OutboundRequest outboundRequest = outboundRequestService.findOneOutboundRequest(id);
    model.addAttribute("outboundRequest", outboundRequest);
    return "mem-outboundrequest_detail";
  }

  @PostMapping("/outbound-request/{id}/update")
  public String postOutboundRequestUpdateForm(@Validated OutboundRequestDTO outboundRequestDTO, BindingResult bindingResult,
      RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
      redirectAttributes.addFlashAttribute("errors", bindingResult);
      return "redirect:/outbound-request/{id}/update";
    }
    // 시큐리티적용하여 다른 메서드사용하도록
    outboundRequestService.updateOutboundRequestByCompany(outboundRequestDTO);
    redirectAttributes.addFlashAttribute("message", "수정 성공");
    return "redirect:/mem-outboundrequest/{id}";
  }

  // 사용자
  @PostMapping("/outbound-request/{id}/delete")
  public String postOutboundRequestDelete(@PathVariable("id") Long id,
      RedirectAttributes redirectAttributes) {
    // 시큐리티적용하여 사용자(회사)만 삭제가능하도록
    outboundRequestService.deleteOutboundRequest(id);
    redirectAttributes.addFlashAttribute("message", "삭제 성공");
    return "redirect:/outbound-requests";
  }
}
