package model.imovel;

import model.Endereco;
import model.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Imovel extends Produto {

    private Endereco endereco;
    private Float areaTotal;

}
