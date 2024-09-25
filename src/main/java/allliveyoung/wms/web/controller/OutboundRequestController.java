package allliveyoung.wms.web.controller;

import allliveyoung.wms.domain.OutboundRequest;
import allliveyoung.wms.web.dto.OutboundRequestDTO;
import allliveyoung.wms.web.dto.PageRequestDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OutboundRequestController {

  @GetMapping("/outbound-requests")
  public String getOutboundRequests(PageRequestDTO pageRequestDTO, Model model) {
    return;
  }

  @GetMapping("/outbound-request/save")
  public String getOutboundRequestsSaveForm() {
    return;
  }

  @PostMapping("/outbound-request/save")
  public String getOutboundRequestsSaveForm(@Validated OutboundRequestDTO outboundRequestDTO,
      BindingResult bindingResult, RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
      return "redirect:/outbound-request/save";
    }

    return "redirect:/outbound-request/{id}";
  }

  @GetMapping("/outbound-request/{id}")
  public String getOutboundRequest(@PathVariable("id") Long id, Model model,
      PageRequestDTO pageRequestDTO) {
    return ;
  }

  @PostMapping("/outbound-reqeust/{id}/update")
  public String postOutboundRequestUpdateForm(@Validated OutboundRequestDTO outboundRequestDTO, BindingResult bindingResult,
      PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
      return "redirect:/outbound-request/{id}/update";
    }
    return "redirect:/outbound-request/{id}";
  }

  @PostMapping("/outbound-request/{id}/delete")
  public String postOutboundRequestDelete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
    return "redirect:/outbound-requests";
  }
}
