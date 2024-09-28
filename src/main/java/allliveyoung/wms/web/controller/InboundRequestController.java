package allliveyoung.allliveinbound.web.controller;

import allliveyoung.allliveinbound.service.InboundRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/inbound-requests")
@Log4j2
@RequiredArgsConstructor
public class InboundRequestController {

    private final InboundRequestService inboundRequestService;
}
