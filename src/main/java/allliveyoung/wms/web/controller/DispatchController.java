package allliveyoung.wms.web.controller;

import allliveyoung.wms.web.dto.DispatchDTO;
import allliveyoung.wms.web.dto.dispatchDTO;
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
public class DispatchController {

  @GetMapping("/dispatches")
  public String getDispatches(PageRequestDTO pageRequestDTO, Model model) {
    return;
  }

  @GetMapping("/dispatch/{id}")
  public String getDispatch(@PathVariable("id") Long id, Model model) {
    return;
  }

  @PostMapping("/dispatch/{id}/update")
  public String postDispatchUpdateForm(@Validated DispatchDTO dispatchDTO,
      PageRequestDTO pageRequestDTO, BindingResult bindingResult,
      RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
      return "dispatch/{id}/update";
    }
    return "redirect:/dispatch/{id}";
  }

  @PostMapping("/dispatch/{id}/delete")
  public String postDispatchDelete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
    return "redirect:/dispatches";
  }
}
