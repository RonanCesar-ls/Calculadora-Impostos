package ImpostosIRPF.example.demo.entity;

public class Imposto {

    private double valorBase;
    private double aliquota;

    public Imposto(){}

    public Imposto(double valorBase, double aliquota) {
        this.valorBase = valorBase;
        this.aliquota = aliquota;
    }

    public double calcularImposto(){
        return (valorBase * aliquota) /100;
    }

    public double calcularValorTotal(){
        return valorBase + calcularImposto();
    }

    public double getValorBase() {
        return valorBase;
    }

    public void setValorBase(double valorBase) {
        this.valorBase = valorBase;
    }

    public double getAliquota() {
        return aliquota;
    }

    public void setAliquota(double aliquota) {
        this.aliquota = aliquota;
    }
}
