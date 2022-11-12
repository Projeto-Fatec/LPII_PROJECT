package model.imovel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EdificioComercial extends Imovel {

    private Float areaConstruida;
    private Integer qtdeAndares;
    private Integer qtdeSalas;

}