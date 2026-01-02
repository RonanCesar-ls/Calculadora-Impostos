package ImpostosIRPF.example.demo.strategy;


import ImpostosIRPF.example.demo.entity.Rendimento;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("regra2026")
public class CalculadoraIRPF2026 implements  CalcularImpostoStrategy{

        private static final BigDecimal FAIXA_ISENCAO = new BigDecimal("5000.00");
        private static final BigDecimal FAIXA_TRANSICAO = new BigDecimal("7350.00");
        private static final BigDecimal TETO_SUPER_RICO = new BigDecimal("50000.00");

        @Override
        public BigDecimal calcular (Rendimento rendimento){
            BigDecimal valorRenda = rendimento.getValor();

            if (valorRenda.compareTo(FAIXA_ISENCAO) <= 0){
                return BigDecimal.ZERO;
            }

            if (valorRenda.compareTo(FAIXA_TRANSICAO) <= 0) {
                return calcularFaixaTransicao(valorRenda);
            }

            return calcularRegraGeral(valorRenda);

        }

        private BigDecimal calcularFaixaTransicao(BigDecimal renda){

            BigDecimal impostoCheio = calcularTabelaPadrao(renda);
            return impostoCheio.multiply(new BigDecimal("0.50"));

        }

        private BigDecimal calcularRegraGeral (BigDecimal renda){
            BigDecimal imposto = calcularTabelaPadrao(renda);

            if (renda.compareTo(TETO_SUPER_RICO) > 0){
                BigDecimal minimoEfetivo = renda.multiply(new BigDecimal("0.10"));
                if(imposto.compareTo(minimoEfetivo) < 0){
                    return minimoEfetivo;
                }
            }
            return imposto;
        }

        private BigDecimal calcularTabelaPadrao(BigDecimal renda){
            return renda.multiply(new BigDecimal("0.275")).subtract(new BigDecimal("896.00"));
        }

        @Override
    public String getDescricaoRegra(){
            return "Reforma 2026  - Isenção R$ 5.000,00 + Taxação Super-Ricos";
        }



}
