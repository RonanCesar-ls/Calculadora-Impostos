package ImpostosIRPF.example.demo.strategy;

import ImpostosIRPF.example.demo.entity.Rendimento;

import java.math.BigDecimal;

public interface CalcularImpostoStrategy {
    BigDecimal calcular (Rendimento rendimento);
    String getDescricaoRegra();


}
