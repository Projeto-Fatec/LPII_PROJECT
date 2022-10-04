package produtos;

import lombok.Getter;
import lombok.Setter;
import tipos.TipoVeiculo;

@Getter
@Setter
public class Veiculo extends Produto {
    private TipoVeiculo tipoVeiculo;
    private String modelo;
    private Integer ano;

    public Veiculo(
            Integer id, Double valor,
            Integer leilao, TipoVeiculo tipoVeiculo,
            String modelo, Integer ano
    ) {
        super(id, valor);
        this.modelo = modelo;
        this.tipoVeiculo = tipoVeiculo;
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "Tipo do veiculo ='" + tipoVeiculo.getTipo() + '\'' +
                ", modelo ='" + modelo + '\'' +
                ", ano =" + ano +
                '}';
    }

}
