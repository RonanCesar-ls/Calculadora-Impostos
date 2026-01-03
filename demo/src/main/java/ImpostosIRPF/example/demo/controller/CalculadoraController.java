package ImpostosIRPF.example.demo.controller;


import ImpostosIRPF.example.demo.dto.RendimentoDTO;
import ImpostosIRPF.example.demo.entity.Rendimento;
import ImpostosIRPF.example.demo.service.CalculadoraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/imposto")
public class CalculadoraController {

    private final CalculadoraService calculadoraService;

    public CalculadoraController(CalculadoraService calculadoraService){
        this.calculadoraService = calculadoraService;

    }

    @PostMapping("/calcular")
    public ResponseEntity<Rendimento> calcularImposto(@RequestBody RendimentoDTO rendimentoDTO){

        Rendimento rendimentoCalculado = calculadoraService.processarRendimento(rendimentoDTO);

        return ResponseEntity.ok(rendimentoCalculado);
    }




}
