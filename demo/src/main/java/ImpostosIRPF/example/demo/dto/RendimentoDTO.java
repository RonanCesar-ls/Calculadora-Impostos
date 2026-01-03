package ImpostosIRPF.example.demo.dto;


import java.math.BigDecimal;

public class RendimentoDTO {

    private String descricao;
    private BigDecimal valor;
    private String tipoRenda;

    public RendimentoDTO(){}

    public RendimentoDTO(String descricao, BigDecimal valor, String tipoRenda) {
        this.descricao = descricao;
        this.valor = valor;
        this.tipoRenda = tipoRenda;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getTipoRenda() {
        return tipoRenda;
    }

    public void setTipoRenda(String tipoRenda) {
        this.tipoRenda = tipoRenda;
    }
}

