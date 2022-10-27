package model;

import enumeration.ImovelTipo;

public class Imovel extends Produto {

    private ImovelTipo tipo;

    public Imovel(String codigo, String descricao, Double valor, ImovelTipo tipo) {
        super(codigo, descricao, valor);

        this.tipo = tipo;
    }

    public ImovelTipo getTipo() {
        return tipo;
    }

    public void setTipo(ImovelTipo tipo) {
        this.tipo = tipo;
    }
}