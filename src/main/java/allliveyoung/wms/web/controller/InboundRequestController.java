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

    ///inbound-requests
    //"/inbound-requests/{id}
    ///inbound-requests/{id}/update"
    ///inbound-requests/save
    ///inbound-requests/save
    ///inbound-requests/{id}/delete
    ///inbound-requests/{id}/update
    ///inbound-requests/{id}/update-status
    //url 경로 위에서부터 적어줘야함
    @GetMapping()
    public void getInboundRequests() {

    }

    @GetMapping("/{id}")
    public void getInboundRequest() {

    }

    @GetMapping("/save")
    public void getInboundRequestSaveForm() {

    }

    @PostMapping("/save")
    public void postInboundRequestSaveForm() {

    }

    @PostMapping( "/{id}/delete")
    public void postInboundRequestDelete() {

    }

    @PostMapping("/{id}/update")
    public void postInboundRequestUpdateForm() {

    }

    @PostMapping("/{id}/update-status")
    public void PostRequestUpdateStatus() {

    }


}
