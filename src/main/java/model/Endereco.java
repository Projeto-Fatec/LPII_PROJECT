package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private Integer cep;
    private Integer numero;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;

}