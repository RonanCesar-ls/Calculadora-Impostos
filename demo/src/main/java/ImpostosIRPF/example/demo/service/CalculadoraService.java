package ImpostosIRPF.example.demo.service;

import ImpostosIRPF.example.demo.dto.RendimentoDTO;
import ImpostosIRPF.example.demo.entity.Rendimento;
import ImpostosIRPF.example.demo.entity.TipoRenda;
import ImpostosIRPF.example.demo.strategy.CalcularImpostoStrategy; // Confirme se o nome do arquivo é Calcular ou Calculo
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculadoraService {

    private final CalcularImpostoStrategy estrategiaCalculo;

    public CalculadoraService(@Qualifier("regra2026") CalcularImpostoStrategy estrategiaCalculo) {
        this.estrategiaCalculo = estrategiaCalculo;
    }

    public Rendimento processarRendimento(RendimentoDTO dto) {

        Rendimento rendimento = new Rendimento();
        rendimento.setDescricao(dto.getDescricao());


        BigDecimal valorBruto = new BigDecimal(dto.getValor().toString());
        rendimento.setValor(valorBruto);


        TipoRenda tipo = TipoRenda.valueOf(dto.getTipoRenda().toUpperCase());
        rendimento.setTipo(tipo);

        BigDecimal impostoCalculado;

        if (tipo == TipoRenda.FII) {
            impostoCalculado = BigDecimal.ZERO;
        } else {

            System.out.println("Calculando " + tipo + " usando estratégia.");
            impostoCalculado = estrategiaCalculo.calcular(rendimento);
        }

        rendimento.setImpostoDevido(impostoCalculado);

        return rendimento;
    }
}