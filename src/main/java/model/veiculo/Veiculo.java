package model.veiculo;

import model.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Veiculo extends Produto {

    private String condicao;
    private String marca;
    private String modelo;
    private String cor;

}