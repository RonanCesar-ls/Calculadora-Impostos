package ImpostosIRPF.example.demo.service;


import ImpostosIRPF.example.demo.dto.RendimentoDTO;
import ImpostosIRPF.example.demo.entity.Rendimento;
import ImpostosIRPF.example.demo.entity.TipoRenda;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculadoraService {

    public Rendimento processarRendimento (RendimentoDTO dto){
        Rendimento rendimento = new Rendimento();
        rendimento.setDescricao(dto.getDescricao());
        rendimento.setValorBruto(dto.getValor());

        TipoRenda tipo = TipoRenda.valueOf(dto.getTipoRenda().toUpperCase());
        rendimento.setTipo(tipo);

        BigDecimal imposto = calcularImpostoPorTipo(tipo, dto.getValor());
        rendimento.setImpostoDevido(imposto);

        return rendimento;
    }

    private BigDecimal calcularImpostoPorTipo(TipoRenda tipo, BigDecimal valor){

        switch (tipo) {
            case CLT:
                return calcularTabelaProgressiva(valor);

            case DIVIDENDO:
                return calcularDividendo2026(valor);

            case FII:
                return BigDecimal.ZERO;

            default:
                return BigDecimal.ZERO;
        }
    }

    private BigDecimal calcularDividendo2026(BigDecimal valor){
         BigDecimal teto = new BigDecimal("50000.00");

         if (valor.compareTo(teto) <= 0){
             return BigDecimal.ZERO;
         }

         return valor.subtract(teto).multiply(new BigDecimal("0.10"));
    }

    private BigDecimal calcularTabelaProgressiva(BigDecimal valor){
        if(valor.doubleValue() > 5000) {
            return valor.multiply(new BigDecimal("0.275"));

        }
        return BigDecimal.ZERO;
    }

    ;


}
