package example.Simple.Calculator.controller;

import example.Simple.Calculator.model.Expression;
import example.Simple.Calculator.service.CalculatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CalculatorController {

    private final CalculatorService calculatorService;

    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("expression", new Expression());
        return "index";
    }

    @PostMapping("/")
    public String calculate(@ModelAttribute("expression") Expression expression, Model model) {
        log.info("String received = {}", expression.getContent());
        try {
            Expression result = new Expression(calculatorService.calculate(expression.getContent()));
            model.addAttribute("result", result);
        } catch (UnsupportedOperationException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "index";
        }
        return "index";
    }

}
