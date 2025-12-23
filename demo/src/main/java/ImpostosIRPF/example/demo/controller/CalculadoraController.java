package ImpostosIRPF.example.demo.controller;


import ImpostosIRPF.example.demo.dto.RendimentoDTO;
import ImpostosIRPF.example.demo.entity.Rendimento;
import ImpostosIRPF.example.demo.service.CalculadoraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calculadora")
public class CalculadoraController {

    @Autowired
    private CalculadoraService service;

    @PostMapping("/simular")
    public ResponseEntity<Rendimento> calcularImposto(@Valid @RequestBody RendimentoDTO dados){

        Rendimento resultado = service.processarRendimento (dados);
        return ResponseEntity.ok(resultado);
    }


}
