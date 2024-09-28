package allliveyoung.wms.web.controller;

import allliveyoung.wms.service.SalesService;
import allliveyoung.wms.web.dto.SalesRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sales")
@Slf4j
public class SalesController {

    private final SalesService salesService;
    private final MemberService memberService;

    @GetMapping
    public String getSales(SalesRequestDTO salesRequestDTO, Model model) {
        model.addAttribute("salesList", salesService.findSales(salesRequestDTO));
        return "/finance/sales-list";
    }
}
