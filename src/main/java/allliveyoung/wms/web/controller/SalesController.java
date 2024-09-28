package allliveyoung.wms.web.controller;

import allliveyoung.wms.service.SalesService;
import allliveyoung.wms.web.dto.SalesRequestDTO;
import allliveyoung.wms.web.dto.SalesSaveDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{id}")
    public String getSale(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("sales", salesService.findSales(id));
        return "/finance/sales-details";
    }

    @GetMapping("/save")
    public String getSalesSaveForm(Model model) {
        model.addAttribute("salesSaveDTO", new SalesSaveDTO());
        model.addAttribute("members", memberService.getCompanies());
        return "/finance/sales-form";
    }
}
