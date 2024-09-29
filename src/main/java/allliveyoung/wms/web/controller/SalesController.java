package allliveyoung.wms.web.controller;

import allliveyoung.wms.service.SalesService;
import allliveyoung.wms.web.dto.SalesRequestDTO;
import allliveyoung.wms.web.dto.SalesSaveDTO;
import allliveyoung.wms.web.dto.SalesUpdateDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/save")
    public String postSalesSaveForm(@AuthenticationPrincipal UserDetailsDTO user, Model model,
                                    @ModelAttribute @Validated SalesSaveDTO salesSaveDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("members", memberService.getCompanies());
            printErrorLog(bindingResult);
            return "/finance/sales-form";
        }
        salesService.saveSale(salesSaveDTO, user.getMember().getWarehouse());
        log.info("매출 등록 완료 | 등록자: {}", user.getMember().getName());
        return "redirect:/sales";
    }

    @GetMapping("/{id}/update")
    public String getSalesUpdateForm(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("salesUpdateDTO", salesService.findSales(id));
        model.addAttribute("existSales", salesService.findSales(id));
        return "/finance/sales-update";
    }

    @PostMapping("/{id}/update")
    public String postSalesUpdateForm(@PathVariable(value = "id") Long id, Model model,
                                      @ModelAttribute @Validated SalesUpdateDTO salesUpdateDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("salesUpdateDTO", salesUpdateDTO);
            model.addAttribute("existSales", salesService.findSales(id));
            printErrorLog(bindingResult);
            return "/finance/sales-update";
        }
        salesService.updateSale(salesUpdateDTO);
        log.info("{}번 매출 내역 수정 완료", id);
        return "redirect:/sales";
    }

    @PostMapping("/{id}/delete")
    public String postSalesDelete(@PathVariable(value = "id") Long id) {
        salesService.deleteSale(id);
        log.info("{}번 매출 내역 삭제 완료", id);
        return "redirect:/sales";
    }

    private static void printErrorLog(BindingResult result) {
        log.info("{}", "*".repeat(20));
        for (FieldError fieldError : result.getFieldErrors()) {
            log.error("{}: {}", fieldError.getField(), fieldError.getDefaultMessage());
        }
        log.info("{}", "*".repeat(20));
    }
}
