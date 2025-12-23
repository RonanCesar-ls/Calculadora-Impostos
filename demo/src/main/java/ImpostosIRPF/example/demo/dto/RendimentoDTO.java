package ImpostosIRPF.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RendimentoDTO {

    @NotBlank (message = "A descrição e obrigatória ")
    private String descricao;

    @NotNull
    private String tipoRenda; // "CLT, DIVIDENDO" mais ????

    @Positive(message = "O valor deve ser positivo")
    private BigDecimal valor;
}
