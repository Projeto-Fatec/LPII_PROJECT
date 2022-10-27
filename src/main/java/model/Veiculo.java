package model;

import enumeration.VeiculoTipo;

public class Veiculo extends Produto {

    private VeiculoTipo tipo;

    public Veiculo(String codigo, String descricao, Double valor, VeiculoTipo tipo) {
        super(codigo, descricao, valor);
        this.tipo = tipo;
    }

    public VeiculoTipo getTipo() {
        return tipo;
    }

    public void setTipo(VeiculoTipo tipo) {
        this.tipo = tipo;
    }
}
