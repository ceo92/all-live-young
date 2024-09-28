package allliveyoung.allliveinbound.web.controller;

import allliveyoung.allliveinbound.service.InboundInspectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inbound-inspections")
@Log4j2
@RequiredArgsConstructor
public class InboundInspectionController {

    private final InboundInspectionService inboundInspectionService;


}
