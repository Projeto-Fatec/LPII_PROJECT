package produtos;

import lombok.Getter;
import lombok.Setter;
import tipos.TipoImovel;

@Getter
@Setter

public class Imovel extends Produto {

    private TipoImovel tipoImovel;
    private String endereco;
    private Integer cep;

    public Imovel(
            Integer id, Double valor,
            Integer leilao, String endereco,
            TipoImovel tipoImovel, Integer cep
    ) {

        super(id, valor);

        this.endereco = endereco;
        this.tipoImovel = tipoImovel;
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "Imovel{" +
                "Id ='" + id + '\'' +
                ", Valor ='" + valor + '\'' +
                ", Tipo ='" + tipoImovel.getTipo() + '\'' +
                ", Endereço ='" + endereco + '\'' +
                ", cep =" + cep +
                '}';
    }
}
