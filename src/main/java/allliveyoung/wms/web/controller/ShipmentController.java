package allliveyoung.wms.web.controller;

import allliveyoung.wms.web.dto.ShipmentDTO;
import allliveyoung.wms.web.dto.PageRequestDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ShipmentController {

  @GetMapping("/shipments")
  public String getShipments(PageRequestDTO pageRequestDTO, Model model) {
    return null;
  }

  @GetMapping("/shipment/{id}")
  public String getShipment(@PathVariable("id") Long id, Model model) {
    return null;
  }

  @PostMapping("/shipment/{id}/update")
  public String postShipmentUpdateForm(@Validated ShipmentDTO ShipmentDTO,
      PageRequestDTO pageRequestDTO, BindingResult bindingResult,
      RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
      return "shipment/{id}/update";
    }
    return "redirect:/shipment/{id}";
  }

  @PostMapping("/shipment/{id}/delete")
  public String postShipmentDelete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
    return "redirect:/shipments";
  }
}
