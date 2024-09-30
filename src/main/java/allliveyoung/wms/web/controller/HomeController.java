package allliveyoung.wms.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ExpenseService expenseService;
    private final InboundRequestService inboundRequestService;
    private final OutboundRequestService outboundRequestService;
    private final AnnouncementService announcementService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("inboundWaiting", inboundRequestService.getCount("처리대기중"));
        model.addAttribute("inboundComplete", inboundRequestService.getCount("승인"));
        model.addAttribute("outboundWaiting", outboundRequestService.getCount("처리대기중"));
        model.addAttribute("outboundComplete", outboundRequestService.getCount("승인"));
        model.addAttribute("countInbound", inboundRequestService.findCountInbound(LocalDate.now().getYear()));
        model.addAttribute("countOutbound", outboundRequestService.findCountOutbound(LocalDate.now().getYear()));
        model.addAttribute("countStock", inboundRequestService.findCountStock(LocalDate.now().getYear()));
        model.addAttribute("sumExpense", expenseService.findSumExpenses(LocalDate.now().getYear()));
        model.addAttribute("announcements", announcementService.findAnnouncementByDate());
        return "dashboard";
    }
}
