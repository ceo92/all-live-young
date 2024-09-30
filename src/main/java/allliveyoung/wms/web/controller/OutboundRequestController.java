package allliveyoung.wms.web.controller;

import allliveyoung.wms.domain.OutboundRequest;
import allliveyoung.wms.domain.Status;
import allliveyoung.wms.service.OutboundRequestService;
import allliveyoung.wms.web.dto.OutboundRequestDTO;
import java.nio.file.Path;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequiredArgsConstructor
public class OutboundRequestController {

  private final OutboundRequestService outboundRequestService;

  @GetMapping("/dashboard")
  public String getDashboard() {
    // 필요한 데이터를 모델에 추가할 수 있습니다.
    return "mem-dashboard";
  }

  @GetMapping("/charts")
  public String getCharts() {
    return "charts";
  }
  //사용자, 총관리자, 창고관리자가 출고요청 리스트를 조회하는 페이지
  @GetMapping("/outbound-requests")
  public String getOutboundRequests(Model model, @RequestParam(value = "status", required = false) Status status) {

    List<OutboundRequest> outboundRequestList = outboundRequestService.findOutboundRequests(status);
    model.addAttribute("outboundRequestList", outboundRequestList);
    model.addAttribute("status", status);
    return "mem-outboundrequest";
  }

  // 사용자가 출고요청을 등록하는 페이지
  @GetMapping("/outbound-request/save")
  public String getOutboundRequestsSaveForm(Model model) {
    model.addAttribute("outboundRequestDTO", new OutboundRequestDTO());
    return "mem-outboundrequest_insert";
  }


  // 사용자의 출고요청 입력 동적처리
  @PostMapping("/outbound-request/save")
  public String postOutboundRequestsSaveForm(@Validated @ModelAttribute OutboundRequestDTO outboundRequestDTO,
      BindingResult bindingResult, RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
      redirectAttributes.addFlashAttribute("errors", bindingResult);
      return "mem-outboundrequest_insert";
    }

    outboundRequestService.saveOutboundRequest(outboundRequestDTO);
    redirectAttributes.addFlashAttribute("message","저장 성공");

    return "redirect:/outbound-requests";
  }

  // 사용자, 총관리자, 창고관리자가 출고요청 조회 후 수정하는 페이지
  @GetMapping({"/outbound-requests/{id}", "/outbound-request/{id}/update"})
  public String getOutboundRequest(@PathVariable("id") Long id, Model model) {
    OutboundRequest outboundRequest = outboundRequestService.findOneOutboundRequest(id);
    model.addAttribute("outboundRequest", outboundRequest);
    return "mem-outboundrequest_detail";
  }

  // 출고요청 수정입력 동적처리
  @PostMapping("/outbound-request/{id}/update")
  public String postOutboundRequestUpdateForm(@Validated OutboundRequestDTO outboundRequestDTO, BindingResult bindingResult,
      RedirectAttributes redirectAttributes, @PathVariable("id") Long id) {
    if (bindingResult.hasErrors()) {
      redirectAttributes.addFlashAttribute("errors", bindingResult);
      return "redirect:/outbound-request/" + id + "/update";
    }
    // 시큐리티적용하여 다른 메서드사용하도록
    outboundRequestService.updateOutboundRequestByCompany(outboundRequestDTO);
    redirectAttributes.addFlashAttribute("message", "수정 성공");
    return "redirect:/outbound-requests/" + id;
  }

  // 사용자의 출고요청 삭제 동적처리
  @PostMapping("/outbound-request/{id}/delete")
  public String postOutboundRequestDelete(@PathVariable("id") Long id,
      RedirectAttributes redirectAttributes) {
    // 시큐리티적용하여 사용자(회사)만 삭제가능하도록
    outboundRequestService.deleteOutboundRequest(id);
    redirectAttributes.addFlashAttribute("message", "삭제 성공");
    return "redirect:/outbound-requests";
  }
}
