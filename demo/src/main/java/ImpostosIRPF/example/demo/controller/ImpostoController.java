package ImpostosIRPF.example.demo.controller;

import ImpostosIRPF.example.demo.entity.Imposto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ImpostoController {

    @GetMapping("/")
    public String exibirFormulario (Model model){

        model.addAttribute("imposto", new Imposto());
        return "home";
    }

    @PostMapping("/calcular")
    public String calcularImposto(@ModelAttribute Imposto imposto, Model model){

        double valorFinal = imposto.calcularImposto();
        double total = imposto.calcularValorTotal();

        model.addAttribute("resultadoImposto", valorFinal);
        model.addAttribute("resultadoTotal", total);

        return "home";
    }


}
