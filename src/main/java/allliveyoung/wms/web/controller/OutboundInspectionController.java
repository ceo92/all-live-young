package allliveyoung.wms.web.controller;

import allliveyoung.wms.web.dto.OutboundInspectionDTO;
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
public class OutboundInspectionController {

  @GetMapping("/outbound-inspections")
  public String getOutboundInspections(PageRequestDTO pageRequestDTO, Model model) {
    return;
  }

  @GetMapping("/outbound-inspection/{id}")
  public String getOutboundInspection(@PathVariable("id") Long id, Model model) {
    return;
  }

  @PostMapping("/outbound-inspection/{id}/update")
  public String postOutboundInspectionUpdateForm(@Valid OutboundInspectionDTO outboundInspectionDTO,
      PageRequestDTO pageRequestDTO, BindingResult bindingResult,
      RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
      return "outbound-inspection/{id}/update";
    }
    return "redirect:/outbound-inspection/{id}";
  }

  @PostMapping("/outbound-inspection/{id}/delete")
  public String postOutboundInspectionDelete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
    return "redirect:/outbound-inspections";
  }
}
