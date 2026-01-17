package ImpostosIRPF.example.demo.service;


import ImpostosIRPF.example.demo.dto.RendimentoDTO;
import ImpostosIRPF.example.demo.entity.Rendimento;
import ImpostosIRPF.example.demo.entity.TipoRenda;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

        rendimento.setFaixaImposto(identificarFaixa(dto.getValor()));
        rendimento.setAliquotaEfetiva(calcularAliquotaEfetiva(imposto, dto.getValor()));

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

    private BigDecimal calcularAliquotaEfetiva(BigDecimal imposto, BigDecimal valorTotal){
        if(valorTotal.compareTo(BigDecimal.ZERO) == 0 ) return BigDecimal.ZERO;

        return imposto.divide(valorTotal, 4, RoundingMode.HALF_UP)
                .multiply(new BigDecimal("100"))
                .setScale(2, RoundingMode.HALF_UP);
    }

    private String identificarFaixa(BigDecimal valor){
        if (valor.doubleValue() <= 5000){
            return "isento";
        }else {
            return "Teto Máximo (27.5%)";
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
