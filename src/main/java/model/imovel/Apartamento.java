package model.imovel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Apartamento extends Imovel {

    private Float areaConstruida;
    private Integer bloco;
    private Integer numeroApartamento;

}