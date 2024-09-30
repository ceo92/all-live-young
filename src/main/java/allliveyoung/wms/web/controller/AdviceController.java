package allliveyoung.wms.web.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class AdviceController {

    @ModelAttribute
    public void getUserInfo(@AuthenticationPrincipal UserDetailsDTO auth, Model model) {
        if (auth != null) {
            model.addAttribute("authName", auth.getMember().getName());
        } else {
            model.addAttribute("authName", "Guest");
        }
    }
}
