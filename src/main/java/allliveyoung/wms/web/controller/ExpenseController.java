package allliveyoung.wms.web.controller;

import allliveyoung.wms.service.ExpenseService;
import allliveyoung.wms.web.dto.ExpenseRequestDTO;
import allliveyoung.wms.web.dto.ExpenseSaveDTO;
import allliveyoung.wms.web.dto.ExpenseUpdateDTO;
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
@RequestMapping("/expenses")
@Slf4j
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping
    public String getExpenses(ExpenseRequestDTO expenseRequestDTO, Model model) {
        model.addAttribute("expenseList", expenseService.findExpenses(expenseRequestDTO));
        return "finance/expense-list";
    }

    @GetMapping("/{id}")
    public String getExpense(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("expense", expenseService.findExpense(id));
        return "/finance/expense-details";
    }

    @GetMapping("/save")
    public String getExpenseSaveForm(Model model) {
        model.addAttribute("expenseSaveDTO", new ExpenseSaveDTO());
        return "/finance/expense-form";
    }

    @PostMapping("/save")
    public String postExpenseSaveForm(@AuthenticationPrincipal UserDetailsDTO user,
                                      @ModelAttribute @Validated ExpenseSaveDTO expenseSaveDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            printErrorLog(bindingResult);
            return "/finance/expense-form";
        }
        expenseService.saveExpense(expenseSaveDTO, user.getMember().getWarehouse());
        log.info("지출 등록 완료 | 등록자: {}", user.getMember().getName());
        return "redirect:/expenses";
    }

    @GetMapping("/{id}/update")
    public String getExpenseUpdateForm(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("expenseUpdateDTO", expenseService.findExpense(id));
        return "/finance/expense-update";
    }

    @PostMapping("/{id}/update")
    public String postExpenseUpdateForm(@PathVariable(value = "id") Long id,
                                        @ModelAttribute @Validated ExpenseUpdateDTO expenseUpdateDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            printErrorLog(bindingResult);
            return "/finance/expense-update";
        }
        expenseService.updateExpense(expenseUpdateDTO);
        log.info("{}번 지출 내역 수정 완료", id);
        return "redirect:/expenses";
    }

    private static void printErrorLog(BindingResult result) {
        log.info("{}", "*".repeat(20));
        for (FieldError fieldError : result.getFieldErrors()) {
            log.error("{}: {}", fieldError.getField(), fieldError.getDefaultMessage());
        }
        log.info("{}", "*".repeat(20));
    }
}
