package ImpostosIRPF.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Rendimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private String faixaImposto;
    private BigDecimal aliquotaEfetiva;

    @Enumerated(EnumType.STRING)
    private TipoRenda tipo; // Enum CLT, DIVIDENDO,FII, SWING_TRADE

    private BigDecimal valorBruto;
    private LocalDate dataRecebimento;

    private BigDecimal impostoDevido;



}
