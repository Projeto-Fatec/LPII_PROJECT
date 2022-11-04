package model;

import enumeration.ImovelTipo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Imovel extends Produto {

    private ImovelTipo tipo;

    public Imovel(String nome, String descricao, Double valorInicial, String imovelTipo){
        super(nome, descricao, valorInicial);
        this.tipo = ImovelTipo.valueOf(imovelTipo);
    }
}